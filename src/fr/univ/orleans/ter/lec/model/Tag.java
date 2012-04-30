package fr.univ.orleans.ter.lec.model;

import java.util.ArrayList;
import java.util.List;

import fr.univ.orleans.ter.lec.persistence.sql.relation.SQLRelation;
import fr.univ.orleans.ter.lec.persistence.sql.relation.roles.PartnerRole;

public class Tag extends BasicLECModel implements PartnerRole {

	private String target;
	private String content;
	
	private List<PartnerRole> languagesList;
	
	public Tag(){
		this.languagesList = new ArrayList<PartnerRole>();
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String targer) {
		this.target = targer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Tag [id=" + _id + ", target=" + target + ", content=" + content + "]";
	}

	/*
	 * TODO: Add relName validation 
	 */
	public void addPartner(String relName, PartnerRole pr) {
		this.languagesList.add(pr);
		
	}

	public List<PartnerRole> getPartners(String relname) {
		return this.languagesList;
	}
}
