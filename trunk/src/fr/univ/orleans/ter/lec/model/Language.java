package fr.univ.orleans.ter.lec.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.util.Log;

import fr.univ.orleans.ter.lec.persistence.sql.relation.SQLRelation;
import fr.univ.orleans.ter.lec.persistence.sql.relation.roles.ChildRole;
import fr.univ.orleans.ter.lec.persistence.sql.relation.roles.ParentRole;
import fr.univ.orleans.ter.lec.persistence.sql.relation.roles.PartnerRole;

/**
 * 
 * Language - Model
 * 
 * For each of the Languages defined in the database, the application will
 * provide structured exercises that will help a child progress in using that
 * language.
 * 
 * For example, we might have exercises defined for: French, Arabic, English,
 * etc..
 * 
 * A Language is implied in multiple SQL relations:
 * 
 * 1. many-to-many, with LComponent, a Language being the Right Partner
 * 
 * 2. many-to-many, with Tag, ditto.
 * 
 * 3. many-to-many, with Method, ditto.
 * 
 * 4. one-to-many, with Level, a Language being the Parent.
 * 
 * @author AdrianSeredinschi
 * 
 */
public class Language extends BasicLECModel implements ParentRole, PartnerRole {

	private String name;

	/* Link with other models, through Relations */
	private HashMap<String, List<PartnerRole>> manyRelationMaps;
	private List<ChildRole> oneRelationList;

	public Language() {
		super();
		
		this.manyRelationMaps = new HashMap<String, List<PartnerRole>>();

		this.manyRelationMaps.put(SQLRelation.RELNAME_LANGUAGES_LCOMPONENTS,
				new ArrayList<PartnerRole>());
		this.manyRelationMaps.put(SQLRelation.RELNAME_LANGUAGES_METHODS,
				new ArrayList<PartnerRole>());
		this.manyRelationMaps.put(SQLRelation.RELNAME_LANGUAGES_TAGS,
				new ArrayList<PartnerRole>());
		this.oneRelationList = new ArrayList<ChildRole>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Language [" + _id + ", " + name + "]";
	}

	public void addPartner(String relName, PartnerRole pr) {
		if (this.validRelationNameForManyRelation(relName, "addPartner")) {
			this.manyRelationMaps.get(relName).add(pr);
		}
	}

	public List<PartnerRole> getPartners(String relname) {
		if (this.validRelationNameForManyRelation(relname, "getPartners")) {
			return this.manyRelationMaps.get(relname);
		} else {
			return null;
		}
	}

	public void addChild(String relationName, ChildRole child) {
		if (this.validRelationNameForOneRelation(relationName, "addChild")) {
			this.oneRelationList.add(child);
		}
	}

	public List<ChildRole> getChilds(String relationName) {
		if (this.validRelationNameForOneRelation(relationName, "getChilds")) {
			return this.oneRelationList;
		} else {
			return null;
		}
	}

	private Boolean validRelationNameForManyRelation(String relName,
			String operation) {
		if (!this.manyRelationMaps.containsKey(relName)) {
			Log.e(this.getClass().toString(), "Invalid relationName "
					+ operation + " requested: " + relName);
			return false;
		}
		return true;
	}

	private Boolean validRelationNameForOneRelation(String relName,
			String operation) {
		if (!relName.equalsIgnoreCase(SQLRelation.RELNAME_LEVELS_LANGUAGE)) {
			Log.e(this.getClass().toString(), "Invalid relationName "
					+ operation + " requested: " + relName);
			return false;
		}
		return true;
	}
}
