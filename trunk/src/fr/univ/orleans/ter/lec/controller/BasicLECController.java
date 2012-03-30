package fr.univ.orleans.ter.lec.controller;

import fr.univ.orleans.ter.lec.repository.mediation.RepositoryMediator;

public abstract class BasicLECController {
	
	private RepositoryMediator repoMediator;
	
	public BasicLECController(RepositoryMediator mediator){
		this.repoMediator = mediator;
	}
}
