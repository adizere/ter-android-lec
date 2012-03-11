package fr.univ.orleans.ter.lec.example;

import java.util.List;

import fr.univ.orleans.ter.lec.model.Language;
import fr.univ.orleans.ter.lec.model.Tag;
import fr.univ.orleans.ter.lec.repository.BasicSqlRepository;
import fr.univ.orleans.ter.lec.repository.GlobalRepository;
import fr.univ.orleans.ter.lec.repository.LanguageRepository;
import fr.univ.orleans.ter.lec.repository.LanguageTagRepository;
import fr.univ.orleans.ter.lec.repository.TagRepository;

public class DbInteractionExample {

	private GlobalRepository globalRepo;

	public DbInteractionExample(GlobalRepository gr) {
		this.globalRepo = gr;
	}

	public void insertions() {

		/*
		 * Inserting some languages
		 */
		BasicSqlRepository langRepo = globalRepo
				.getRepositoryByTableName("languages");

		Language l1 = ((LanguageRepository) langRepo).createLanguage("French");
		Language l2 = ((LanguageRepository) langRepo).createLanguage("English");

		/*
		 * Insert some tags..
		 */
		BasicSqlRepository tagRepo = globalRepo
				.getRepositoryByTableName("tags");

		Tag t1 = ((TagRepository) tagRepo).createTag("WELCOME_MESSAGE",
				"Hello!");
		Tag t2 = ((TagRepository) tagRepo).createTag("WELCOME_MESSAGE2",
				"Hello there!");

		/*
		 * Now map the two languages with these two tags..
		 */

		BasicSqlRepository langTagRepo = globalRepo
				.getRepositoryByTableName("languages_tags");
		
		((LanguageTagRepository)langTagRepo).createLanguageTag(l1.getId(), t1.getId());
		((LanguageTagRepository)langTagRepo).createLanguageTag(l1.getId(), t2.getId());

		((LanguageTagRepository)langTagRepo).createLanguageTag(l2.getId(), t1.getId());
		((LanguageTagRepository)langTagRepo).createLanguageTag(l2.getId(), t2.getId());
	}

	public List<Object> retrieveLanguages() {
		BasicSqlRepository langRepo = globalRepo
				.getRepositoryByTableName("languages");

		return langRepo.getMembers();
	}

	public List<Object> retrieveTags() {
		BasicSqlRepository tagRepo = globalRepo
				.getRepositoryByTableName("tags");

		return tagRepo.getMembers();
	}

	public List<Object> retrieveLanguagesTags() {
		BasicSqlRepository langTagRepo = globalRepo
				.getRepositoryByTableName("languages_tags");

		return langTagRepo.getMembers();
	}
}
