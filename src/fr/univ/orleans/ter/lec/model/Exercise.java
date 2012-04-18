package fr.univ.orleans.ter.lec.model;

import android.util.Log;
import fr.univ.orleans.ter.lec.persistence.sql.relation.SQLRelation;
import fr.univ.orleans.ter.lec.persistence.sql.relation.roles.ChildRole;
import fr.univ.orleans.ter.lec.persistence.sql.relation.roles.ParentRole;

public class Exercise extends BasicLECModel implements ChildRole {
	
	private Long levelId;
	private Boolean completed;
	private String statement;
	private String equation;
	private String result;
	
	private ParentRole levelParent;
	
	public Exercise(){
		super();
	}

	public Long getLevelId() {
		return levelId;
	}

	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	@Override
	public String toString() {
		return "Exercise [levelId=" + levelId + ", completed=" + completed
				+ ", statement=" + statement + "]";
	}
	
	/*
	 * Strinfigied to be shown in a list of multiple Exercise objects.
	 */
	public String toStringForListEntry() {
		return "[" + this.getId() + "]" + this.getStatement() + ": " + this.getEquation();
	}

	public void setParent(String relName, ParentRole pr) {
		if (this.validRelationName(relName, "setParent")) {
			this.levelParent = pr;
		}
	}

	public ParentRole getParent(String relName) {
		if (this.validRelationName(relName, "setParent")) {
			return this.levelParent;
		} else {
			return null;
		}
	}

	public Long getParentIdentity(String relName) {
		if (this.validRelationName(relName, "setParent")) {
			return this.levelId;
		} else {
			return null;
		}
	}
	
	private Boolean validRelationName(String relName, String operation) {
		if (relName.equalsIgnoreCase(SQLRelation.RELNAME_EXERCISES_LEVEL)) {
			return true;
		} else {
			Log.e(this.getClass().toString(),
					"Invalid relationName " + operation + " requested: " + relName);
			return false;
		}
	}

	public void setCompleted(String string) {
		if (string.equals("1")) {
			this.completed = true;
		} else {
			this.completed = false;
		}
	}

	public String getEquation() {
		return equation;
	}

	public void setEquation(String equation) {
		this.equation = equation;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
