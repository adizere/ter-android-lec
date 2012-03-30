package fr.univ.orleans.ter.lec.example;

import java.util.List;

import fr.univ.orleans.ter.lec.model.Language;
import fr.univ.orleans.ter.lec.model.Method;
import fr.univ.orleans.ter.lec.model.Tag;
import fr.univ.orleans.ter.lec.repository.BasicLECRepository;
import fr.univ.orleans.ter.lec.repository.LevelsRepository;
import fr.univ.orleans.ter.lec.repository.MethodsRepository;
import fr.univ.orleans.ter.lec.repository.LanguagesRepository;
import fr.univ.orleans.ter.lec.repository.LanguagesTagsRepository;
import fr.univ.orleans.ter.lec.repository.TagsRepository;
import fr.univ.orleans.ter.lec.repository.mediation.RepositoryMediator;

public class DbInteractionExample {

	private RepositoryMediator repoMediator;

	public DbInteractionExample(RepositoryMediator gr) {
		this.repoMediator = gr;
	}

	public void insertions() {

		BasicLECRepository langRepo = repoMediator
				.getRepositoryByTableName("languages");

		Language l1 = ((LanguagesRepository) langRepo).createLanguage("French");
		Language l2 = ((LanguagesRepository) langRepo).createLanguage("English");
		
		/*
		 * Insert some tags..
		 */
		BasicLECRepository tagRepo = repoMediator
				.getRepositoryByTableName("tags");

		Tag t1 = ((TagsRepository) tagRepo).createTag("WELCOME_MESSAGE",
				"Hello!");
		Tag t2 = ((TagsRepository) tagRepo).createTag("WELCOME_MESSAGE",
				"Bonjour!");

		/*
		 * Now map the two languages with these two tags..
		 */

		BasicLECRepository langTagRepo = repoMediator
				.getRepositoryByTableName("languages_tags");
		
		((LanguagesTagsRepository)langTagRepo).createLanguageTag(l1.getId(), t1.getId());
		((LanguagesTagsRepository)langTagRepo).createLanguageTag(l1.getId(), t2.getId());

		((LanguagesTagsRepository)langTagRepo).createLanguageTag(l2.getId(), t1.getId());
		((LanguagesTagsRepository)langTagRepo).createLanguageTag(l2.getId(), t2.getId());
	}
	
	public List<Object> retrieveLanguages() {
		BasicLECRepository langRepo = repoMediator
				.getRepositoryByTableName("languages");

		return langRepo.getMembers();
	}

	public List<Object> retrieveTags() {
		BasicLECRepository tagRepo = repoMediator
				.getRepositoryByTableName("tags");

		return tagRepo.getMembers();
	}

	public List<Object> retrieveLanguagesTags() {
		BasicLECRepository langTagRepo = repoMediator
				.getRepositoryByTableName("languages_tags");

		return langTagRepo.getMembers();
	}

	public void basicDBInit() {
		Long langIds[] = this.insertLanguages();
		Long methodIds[] = this.insertMethods();
		
		
		for (int i = 0; i < langIds.length; i++) {
			for (int j = 0; j < methodIds.length; j++) {
				Long levels[] = this.insertLevels(langIds[i], methodIds[j]);
			}
		}
	}

	private Long[] insertLanguages() {
		/*
		 * Inserting some languages
		 */
		BasicLECRepository langRepo = repoMediator
				.getRepositoryByTableName("languages");

		Language l1 = ((LanguagesRepository) langRepo).createLanguage("French");
		Language l2 = ((LanguagesRepository) langRepo).createLanguage("English");
		
		Long ret[] = {l1.getId(), l2.getId()};
		return ret;
	}
	
	public Long[] insertMethods(){
		MethodsRepository meRepo = (MethodsRepository)repoMediator.getRepositoryByTableName("methods");
		
		Method m1 = meRepo.createMethod("Read");
		Method m2 = meRepo.createMethod("Write");
		
		Long ret[] = {m1.getId(), m2.getId()};
		return ret;
	}
	
	public Long[] insertLevels(Long languageId, Long methodId) {
		LevelsRepository levelRepo = (LevelsRepository)repoMediator.getRepositoryByTableName("levels");
		
		Long ret[] = new Long[10];
		for (int i = 0; i < 10; i++) {
			levelRepo.createLevel(languageId, methodId);
		}
		
		return null;
	}

}
