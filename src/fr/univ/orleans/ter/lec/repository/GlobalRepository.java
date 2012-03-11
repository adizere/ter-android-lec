package fr.univ.orleans.ter.lec.repository;

import java.util.HashMap;
import java.util.List;

import android.util.Log;
import fr.univ.orleans.ter.lec.model.Language;
import fr.univ.orleans.ter.lec.model.LanguageTag;
import fr.univ.orleans.ter.lec.model.Tag;
import fr.univ.orleans.ter.lec.persistence.SQLiteHelper;

public class GlobalRepository {

	private HashMap<String, BasicSqlRepository> repositories;
	private SQLiteHelper databaseHelper;

	public GlobalRepository(SQLiteHelper databaseHelper) {
		super();
		this.databaseHelper = databaseHelper;
		this.repositories = new HashMap<String, BasicSqlRepository>();

		this.init();
	}

	/*
	 * Create all individual Repositories, initialize them and finally link the Models.
	 */
	private void init() {
		repositories.put("languages", new LanguageRepository(this.databaseHelper));
		repositories.put("tags", new TagRepository(this.databaseHelper));
		repositories.put("languages_tags", new LanguageTagRepository(this.databaseHelper));
		
		this.linkModels();
	}

	/*
	 * This method ensures that relations between different Model objects are consistent.
	 */
	private void linkModels() {
		// Multiple Languages with multiple Tags..
		List<Object> languages_tags = this.repositories.get("languages_tags").getMembers();
		
		for(Object lang_tag : languages_tags){
			Long language_id = ((LanguageTag)lang_tag).getLanguage_id();
			Long tag_id = ((LanguageTag)lang_tag).getTag_id();
			
			Language l = (Language)this.repositories.get("languages").getMemberById(language_id);
			Tag t = (Tag)this.repositories.get("tags").getMemberById(tag_id);
			
			l.addTag(t);
		}
		
	}

	public BasicSqlRepository getRepositoryByTableName(String tableName) {
		if (repositories.containsKey(tableName)){
			return repositories.get(tableName);
		}
		
		Log.e("GlobalRepository", "Tried to retrieve a repository by name that wasn't found: " + tableName );
		return null;
	}

}
