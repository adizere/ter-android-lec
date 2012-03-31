/**
 * 
 */
package fr.univ.orleans.ter.lec.model;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import fr.univ.orleans.ter.lec.persistence.sql.relation.SQLRelation;
import fr.univ.orleans.ter.lec.persistence.sql.relation.roles.PartnerRole;

/**
 * @author AdrianSeredinschi
 * 
 */
public class Method extends BasicLECModel implements PartnerRole {

	private String name;
	private Boolean completed;

	private List<PartnerRole> languagesList;

	public Method() {
		super();
		this.languagesList = new ArrayList<PartnerRole>();
	}

	public Method(String name, Boolean completed) {
		super();
		this.name = name;
		this.completed = completed;
		this.languagesList = new ArrayList<PartnerRole>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	/*
	 * Set a Boolean using a String. Convenience method to simplify
	 * transformation from SQL types to ours.
	 */
	public void setCompleted(String string) {
		if (string.equals("1")) {
			this.completed = true;
		} else {
			this.completed = false;
		}
	}

	@Override
	public String toString() {
		return "Method [name=" + name + ", completed=" + completed + "]";
	}

	public void addPartner(String relName, PartnerRole pr) {
		if (!relName.equalsIgnoreCase(SQLRelation.RELNAME_LANGUAGES_METHODS)) {
			Log.e(this.getClass().toString(),
					"Invalid relationName addPartner requested: " + relName);
		} else {
			this.languagesList.add(pr);
		}
	}

	public List<PartnerRole> getPartners(String relName) {
		if (!relName.equalsIgnoreCase(SQLRelation.RELNAME_LANGUAGES_METHODS)) {
			Log.e(this.getClass().toString(),
					"Invalid relationName addPartner requested: " + relName);
			return null;
		} else {
			return this.languagesList;
		}
	}

}
