package fr.univ.orleans.ter.lec.repository.mediation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.util.Log;
import fr.univ.orleans.ter.lec.model.Language;
import fr.univ.orleans.ter.lec.model.LanguageTag;
import fr.univ.orleans.ter.lec.model.Tag;
import fr.univ.orleans.ter.lec.persistence.SQLiteHelper;
import fr.univ.orleans.ter.lec.repository.BasicLECRepository;
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

	/*
	 * Create all individual Repositories, initialize them and finally link the
	 * Models.
	 */
	private void init() {
		repositories.put("languages", new LanguagesRepository(
				this.databaseHelper));
		repositories.put("tags", new TagsRepository(this.databaseHelper));
		repositories.put("languages_tags", new LanguagesTagsRepository(
				this.databaseHelper));
		repositories.put("languages_methods", new LanguagesMethodsRepository(
				this.databaseHelper));
		repositories.put("methods", new MethodsRepository(this.databaseHelper));
		repositories.put("levels", new LevelsRepository(this.databaseHelper));

		Collection<BasicLECRepository> repositoriesList = repositories.values();
		for (Iterator iterator = repositoriesList.iterator(); iterator
				.hasNext();) {
			BasicLECRepository basicSqlRepository = (BasicLECRepository) iterator
					.next();
			if (basicSqlRepository instanceof BasicLECRepository) {

			}
		}

		this.linkModels();
	}

	/*
	 * This method ensures that relations between different Model objects are
	 * consistent.
	 */
	private void linkModels() {

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

}
