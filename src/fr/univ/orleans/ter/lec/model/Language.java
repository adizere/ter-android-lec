package fr.univ.orleans.ter.lec.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
	
	/*
	 * Convenience methods: pretty-accessors for different relations
	 */
	
	public List<Level> getLevels() {
		List<ChildRole> childs = this.getChilds(SQLRelation.RELNAME_LEVELS_LANGUAGE);
		
		/*
		 * TODO: Remove costly casting.
		 */
		List<Level> ret = new ArrayList<Level>();
		for (Object child : childs) {
			ret.add((Level)child);
		}
		
		return ret;
	}

	public List<Method> getMethods() {
		List<PartnerRole> methods = this.getPartners(SQLRelation.RELNAME_LANGUAGES_METHODS);
		
		/*
		 * TODO: Remove casting - there has to be an easier way of doing this..
		 */
		List<Method> ret = new ArrayList<Method>();
		for (PartnerRole partnerRole : methods) {
			ret.add((Method)partnerRole);
		}
		return ret;
	}

	/*
	 * Return all levels for a specific method Id
	 */
	public List<Level> getLevelsForMethod(Long methodId) {
		List<Level> levels = this.getLevels();
		
		Iterator<Level> it = levels.iterator();
		while(it.hasNext()){
			if (it.next().getMethodId() != methodId)
				it.remove();
		}
		
		return levels;
	}

	public List<Tag> getTags() {
		List<PartnerRole> tags = this.getPartners(SQLRelation.RELNAME_LANGUAGES_TAGS);
		
		/*
		 * TODO: Remove casting - there has to be an easier way of doing this..
		 */
		List<Tag> ret = new ArrayList<Tag>();
		for (PartnerRole partnerRole : tags) {
			ret.add((Tag)partnerRole);
		}
		return ret;
	}
}
