package fr.univ.orleans.ter.lec.model;

import java.util.ArrayList;
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
	private ParentRole levelParent;
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
				this.levelParent = pr;
			} else {
				this.methodParent = pr;
			}
		}
	}

	public ParentRole getParent(String relName) {
		if (this.validRelationNameForChild(relName, "setParent")){
			if (relName.equalsIgnoreCase(SQLRelation.RELNAME_LEVELS_LANGUAGE)){
				return this.levelParent;
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
}
