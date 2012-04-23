package fr.univ.orleans.ter.lec.repository.mediation;

import java.util.HashMap;
import java.util.List;

import android.util.Log;
import fr.univ.orleans.ter.lec.persistence.SQLiteHelper;
import fr.univ.orleans.ter.lec.persistence.sql.Table;
import fr.univ.orleans.ter.lec.persistence.sql.relation.ManyToMany;
import fr.univ.orleans.ter.lec.persistence.sql.relation.OneToMany;
import fr.univ.orleans.ter.lec.persistence.sql.relation.SQLRelation;
import fr.univ.orleans.ter.lec.persistence.sql.relation.roles.ChildRole;
import fr.univ.orleans.ter.lec.persistence.sql.relation.roles.IntermediateRole;
import fr.univ.orleans.ter.lec.persistence.sql.relation.roles.ParentRole;
import fr.univ.orleans.ter.lec.persistence.sql.relation.roles.PartnerRole;
import fr.univ.orleans.ter.lec.repository.BasicLECRepository;
import fr.univ.orleans.ter.lec.repository.ChoicesRepository;
import fr.univ.orleans.ter.lec.repository.ExercisesRepository;
import fr.univ.orleans.ter.lec.repository.LComponentsRepository;
import fr.univ.orleans.ter.lec.repository.LanguagesLComponentsRepository;
import fr.univ.orleans.ter.lec.repository.LanguagesMethodsRepository;
import fr.univ.orleans.ter.lec.repository.LanguagesRepository;
import fr.univ.orleans.ter.lec.repository.LanguagesTagsRepository;
import fr.univ.orleans.ter.lec.repository.LevelsRepository;
import fr.univ.orleans.ter.lec.repository.MethodsRepository;
import fr.univ.orleans.ter.lec.repository.TagsRepository;

/**
 * 
 * RepositoryMediator - Persistence / data manipulation layer
 * 
 * Heavy-weight class that intermediates the relations between different
 * repository (and correspondent Model) classes.
 * 
 * @author AdrianSeredinschi
 * 
 */
public class RepositoryMediator {

	private HashMap<String, BasicLECRepository> repositories;
	private SQLiteHelper databaseHelper;

	public RepositoryMediator(SQLiteHelper databaseHelper) {
		super();
		this.databaseHelper = databaseHelper;
		this.repositories = new HashMap<String, BasicLECRepository>();

		this.init();
	}

	public BasicLECRepository getRepositoryByTableName(String tableName) {
		if (repositories.containsKey(tableName)) {
			return repositories.get(tableName);
		}

		Log.e("GlobalRepository",
				"Tried to retrieve a repository by name that wasn't found: "
						+ tableName);
		return null;
	}

	/*
	 * Create all individual Repositories, initialize them and finally link the
	 * Models.
	 */
	private void init() {
		this.createRepositories();
		this.linkModels();
	}

	private void createRepositories() {
		repositories.put("languages", new LanguagesRepository(
				this.databaseHelper));
		repositories.put("tags", new TagsRepository(this.databaseHelper));
		repositories.put("languages_tags", new LanguagesTagsRepository(
				this.databaseHelper));
		repositories.put("languages_methods", new LanguagesMethodsRepository(
				this.databaseHelper));
		repositories.put("methods", new MethodsRepository(this.databaseHelper));
		repositories.put("levels", new LevelsRepository(this.databaseHelper));
		repositories.put("exercises", new ExercisesRepository(
				this.databaseHelper));
		repositories.put("lcomponents", new LComponentsRepository(
				this.databaseHelper));
		repositories.put("languages_lcomponents",
				new LanguagesLComponentsRepository(this.databaseHelper));
		repositories.put("choices", new ChoicesRepository(this.databaseHelper));
	}

	/*
	 * This method ensures that relations between different Model objects are
	 * consistent.
	 */
	private void linkModels() {
		List<Table> tables = this.databaseHelper.getDbStructure().getTables();

		for (Table table : tables) {
			List<SQLRelation> relations = table.getRelations();
			for (SQLRelation sqlRelation : relations) {
				this.applyRelation(sqlRelation);
			}
		}
	}

	/*
	 * applyRelation(): applies an relation between two repositories,
	 * individually linking all the model objects that have a relation between
	 * them.
	 * 
	 *  Called from linkModels() exclusively.
	 *  Makes use of:
	 *  - applyOneToManyRelation()
	 *  and 
	 *  - applyManyToManyRelation
	 *  
	 *  depending on what type of SQLRelation it received as input.
	 */
	private void applyRelation(SQLRelation sqlRelation) {
		if (sqlRelation.getRelationType().equals(
				SQLRelation.RELTYPE_ONE_TO_MANY)) {
			this.applyOneToManyRelation((OneToMany) sqlRelation);

		} else if (sqlRelation.getRelationType().equals(
				SQLRelation.RELTYPE_MANY_TO_MANY)) {
			this.applyManyToManyRelation((ManyToMany) sqlRelation);
		}
	}

	private void applyOneToManyRelation(OneToMany sqlRelation) {
		Log.d(this.getClass().toString(),
				"Applying 1:N relation " + sqlRelation.getRelationName()
						+ " from " + sqlRelation.getChildTableName()
						+ " to parent: " + sqlRelation.getParentTableName());

		String relName = sqlRelation.getRelationName();

		BasicLECRepository parentRepo = this.repositories.get(sqlRelation
				.getParentTableName());
		BasicLECRepository childRepo = this.repositories.get(sqlRelation
				.getChildTableName());

		List<Object> childs = childRepo.getMembers();
		for (int i = 0; i < childs.size(); i++) {
			ChildRole cr = (ChildRole) childs.get(i);
			Long parentId = cr.getParentIdentity(relName);
			ParentRole pr = (ParentRole) parentRepo.getMemberById(parentId);

			if ( pr == null || cr == null){
				Log.w("1:N relation mapping", "Role not found, relation not applied.");
				continue;
			}
				
			
			// Effective linking..
			pr.addChild(relName, cr);
			cr.setParent(relName, pr);
		}

	}

	private void applyManyToManyRelation(ManyToMany sqlRelation) {
		Log.d(this.getClass().toString(),
				"Applying N:N relation " + sqlRelation.getRelationName()
						+ " right: " + sqlRelation.getRightPartnerName()
						+ " to left: " + sqlRelation.getLeftPartnerName()
						+ " throuth intermediary: "
						+ sqlRelation.getIntermediateTableName());

		String relName = sqlRelation.getRelationName();

		BasicLECRepository rightPRepo = this.repositories.get(sqlRelation
				.getRightPartnerName());
		BasicLECRepository leftPRepo = this.repositories.get(sqlRelation
				.getLeftPartnerName());
		BasicLECRepository intermRepo = this.repositories.get(sqlRelation
				.getIntermediateTableName());

		List<Object> interimMembers = intermRepo.getMembers();
		for (int i = 0; i < interimMembers.size(); i++) {
			IntermediateRole intM = (IntermediateRole) interimMembers.get(i);

			Long righPartnerId = intM.getRightPartnerIdentity(relName);
			Long leftPartnerId = intM.getLeftPartnerIdentity(relName);

			PartnerRole rightPartner = (PartnerRole) rightPRepo
					.getMemberById(righPartnerId);
			PartnerRole leftPartner = (PartnerRole) leftPRepo
					.getMemberById(leftPartnerId);

			// Effective linking..
			rightPartner.addPartner(relName, leftPartner);
			leftPartner.addPartner(relName, rightPartner);
		}
	}
}
