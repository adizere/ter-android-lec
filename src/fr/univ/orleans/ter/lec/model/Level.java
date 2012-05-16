package fr.univ.orleans.ter.lec.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.util.Log;

import fr.univ.orleans.ter.lec.persistence.sql.relation.SQLRelation;
import fr.univ.orleans.ter.lec.persistence.sql.relation.roles.ChildRole;
import fr.univ.orleans.ter.lec.persistence.sql.relation.roles.ParentRole;

public class Level extends BasicLECModel implements ChildRole, ParentRole {

	private Long languageId;
	private Long methodId;
	private Boolean completed;
	private String name;
	
	
	// A Level is parent for multiple Exercises
	private ParentRole languageParent;
	private ParentRole methodParent;
	private List<ChildRole> exerciseChilds;

	public Level() {
		super();
		this.exerciseChilds = new ArrayList<ChildRole>();
	}

	public Long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}

	public Long getMethodId() {
		return methodId;
	}

	public void setMethodId(Long methodId) {
		this.methodId = methodId;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public void setCompleted(String str) {
		if (str.equals("1")) {
			this.completed = true;
		} else {
			this.completed = false;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Level [languageId=" + languageId + ", methodId=" + methodId
				+ ", completed=" + completed + ", name=" + name + "]";
	}

	public Long getParentIdentity(String relName) {
		if (this.validRelationNameForChild(relName, "geParentIdentity")){
			if (relName.equalsIgnoreCase(SQLRelation.RELNAME_LEVELS_LANGUAGE)){
				return this.getLanguageId();
			} else {
				return this.getMethodId();
			}
		} else {
			return null;
		}
	}

	public void setParent(String relName, ParentRole pr) {
		if (this.validRelationNameForChild(relName, "setParent")){
			if (relName.equalsIgnoreCase(SQLRelation.RELNAME_LEVELS_LANGUAGE)){
				this.languageParent = pr;
			} else {
				this.methodParent = pr;
			}
		}
	}

	public ParentRole getParent(String relName) {
		if (this.validRelationNameForChild(relName, "setParent")){
			if (relName.equalsIgnoreCase(SQLRelation.RELNAME_LEVELS_LANGUAGE)){
				return this.languageParent;
			} else {
				return this.methodParent;
			}
		} else {
			return null;
		}
	}
	
	private Boolean validRelationNameForChild(String relName, String operation) {
		if (relName.equalsIgnoreCase(SQLRelation.RELNAME_LEVELS_LANGUAGE) || relName.equalsIgnoreCase(SQLRelation.RELNAME_LEVELS_METHOD)) {
			return true;
		} else {
			Log.e(this.getClass().toString(),
					"Invalid relationName " + operation + " requested: " + relName);
			return false;
		}
	}
	
	private Boolean validRelationNameForParent(String relName, String operation) {
		if (relName.equalsIgnoreCase(SQLRelation.RELNAME_EXERCISES_LEVEL)) {
			return true;
		} else {
			Log.e(this.getClass().toString(),
					"Invalid relationName " + operation + " requested: " + relName);
			return false;
		}
	}

	public void addChild(String relationName, ChildRole child) {
		if (this.validRelationNameForParent(relationName, "addChild")){
			this.exerciseChilds.add(child);
		}
	}

	public List<ChildRole> getChilds(String relationName) {
		if (this.validRelationNameForParent(relationName, "getChilds")){
			return this.exerciseChilds;
		} else {
			return null;
		}
	}

	/*
	 * TODO: Avoid casting.
	 */
	public List<Exercise> getExercises() {
		List<ChildRole> childs = this.getChilds(SQLRelation.RELNAME_EXERCISES_LEVEL);
		
		List<Exercise> ret = new ArrayList<Exercise>();
		for (ChildRole childRole : childs) {
			ret.add((Exercise)childRole);
		}
		return ret;
	}

	/*
	 * Returns an Exercise for the current Level.
	 * The Exercise will be either one of the two:
	 *  - the first exercise having completed = false
	 *  - the first exercise minId >  if all have completed = true
	 *  - the first exercise if none satisfiable was found
	 */
	public Exercise getSessionExercises(long minId) {
		List<ChildRole> childs = this.getChilds(SQLRelation.RELNAME_EXERCISES_LEVEL);
		
		if( childs.size() == 0){
			Log.w("model.Level", "Level is empty, returning null Exercise.");
			return null;
		}
		
		Exercise ret = null;
		Exercise first = null;
		
		Iterator<ChildRole> it = childs.iterator();
		while(it.hasNext()){
			Exercise current = (Exercise)it.next();
			if (first == null && current.getId() > minId)
				first = current;
			if (current.getCompleted() == false){
				ret = current;
				break;
			}
		}
		
		// No un-completed exercise was found, returning the first one
		if ( ret == null && first == null ){
			ret = (Exercise)childs.get(0);
		} else {
			ret = first;
		}
		
		return ret;
	}
	
	public Language getLanguage() {
		return (Language)this.languageParent;
	}
}
