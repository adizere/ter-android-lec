package fr.univ.orleans.ter.lec.model;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import fr.univ.orleans.ter.lec.persistence.sql.relation.SQLRelation;
import fr.univ.orleans.ter.lec.persistence.sql.relation.roles.PartnerRole;

/**
 * 
 * LComponent - Model
 * 
 * LComponents has the main purpose of making easier the differentiation between
 * various Languages.
 * 
 * An LComponent can effectively be: - a letter - a syllable - a phrase
 * 
 * Each Language will have a multitude of different LComponents attached to it,
 * which can be used to construct exercises.
 * 
 * This class plays the Role of an Partner in a many-to-many relation with the
 * Language class, being the leftPartner.
 * 
 * @author AdrianSeredinschi
 * 
 */
public class LComponent extends BasicLECModel implements PartnerRole {

	private String content;
	private String type;

	// Role-specific members
	private List<PartnerRole> partners;

	public LComponent() {
		this.content = null;
		this.type = null;
		this.partners = new ArrayList<PartnerRole>();
	}

	public LComponent(String content, String type) {
		super();
		this.content = content;
		this.type = type;
		this.partners = new ArrayList<PartnerRole>();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void addPartner(String relName, PartnerRole pr) {
		if (!relName
				.equalsIgnoreCase(SQLRelation.RELNAME_LANGUAGES_LCOMPONENTS)) {
			Log.e(this.getClass().toString(),
					"Invalid relationName addPartner requested: " + relName);
		}
		this.partners.add(pr);
	}

	public List<PartnerRole> getPartners(String relname) {
		if (!relname
				.equalsIgnoreCase(SQLRelation.RELNAME_LANGUAGES_LCOMPONENTS)) {
			Log.e(this.getClass().toString(),
					"Invalid relationName getPartners requested: " + relname);
			return null;
		}
		return this.partners;
	}

}
