package fr.univ.orleans.ter.lec.controller;

import fr.univ.orleans.ter.lec.repository.mediation.RepositoryMediator;

public abstract class BasicLECController {
	
	/*
	 * Static property so we can access it from all the controllers
	 */
	public static RepositoryMediator repoMediator;
	
	public BasicLECController(RepositoryMediator mediator){
		this.repoMediator = mediator;
	}
	
	public BasicLECController(){
		
	}

	public RepositoryMediator getRepositoryMediator() {
		return repoMediator;
	}
}
