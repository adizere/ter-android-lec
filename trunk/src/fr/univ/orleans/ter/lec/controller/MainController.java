package fr.univ.orleans.ter.lec.controller;

import java.util.ArrayList;
import java.util.List;

import fr.univ.orleans.ter.lec.model.Language;
import fr.univ.orleans.ter.lec.repository.mediation.RepositoryMediator;

public class MainController extends BasicLECController {
	
	public static RepositoryMediator repoMediator;

	public MainController(RepositoryMediator mediator) {
		super(mediator);
		this.repoMediator = mediator;
	}

	/*
	 * TODO: Remove costly casting.
	 */
	public List<Language> getLanguages() {
		List<Object> langsAsObj = this.getRepositoryMediator().getRepositoryByTableName("languages").getMembers();
		List<Language> langs = new ArrayList<Language>();
		
		for (Object langAsObject : langsAsObj) {
			langs.add((Language)langAsObject);
		}
		
		return langs;
	}

}
