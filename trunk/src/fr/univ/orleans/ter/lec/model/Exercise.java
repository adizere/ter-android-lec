package fr.univ.orleans.ter.lec.model;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import fr.univ.orleans.ter.lec.persistence.sql.relation.SQLRelation;
import fr.univ.orleans.ter.lec.persistence.sql.relation.roles.ChildRole;
import fr.univ.orleans.ter.lec.persistence.sql.relation.roles.ParentRole;

public class Exercise extends BasicLECModel implements ChildRole, ParentRole {

	private Long levelId;
	private Boolean completed;
	private String question;

	private ParentRole levelParent;
	private List<ChildRole> choiceChilds;

	public Exercise() {
		super();
		this.choiceChilds = new ArrayList<ChildRole>();
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

	@Override
	public String toString() {
		return "Exercise [levelId=" + levelId + ", completed=" + completed
				+ ", question=" + question + "]";
	}

	/*
	 * Strinfigied to be shown in a list of multiple Exercise objects.
	 */
	public String toStringForListEntry() {
		return "[" + this.getId() + "]" + this.getQuestion();
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
		if (relName.equalsIgnoreCase(SQLRelation.RELNAME_EXERCISES_LEVEL)
				|| relName
						.equalsIgnoreCase(SQLRelation.RELNAME_CHOICES_EXERCISE)) {
			return true;
		} else {
			Log.e(this.getClass().toString(), "Invalid relationName "
					+ operation + " requested: " + relName);
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

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void addChild(String relationName, ChildRole child) {
		if (this.validRelationName(relationName, "addChild")) {
			this.choiceChilds.add(child);
		}
	}

	public List<ChildRole> getChilds(String relationName) {
		if (this.validRelationName(relationName, "addChild")) {
			return this.choiceChilds;
		} else {
			return null;
		}
	}

	/*
	 * Convenience methods
	 */
	public Choice getChoice() {
		List<ChildRole> childs = this
				.getChilds(SQLRelation.RELNAME_CHOICES_EXERCISE);

		if (childs.size() == 0 ){
			Log.w("model.Exercise", "No choice associated with exercise: " + this._id );
			return null;
		}
		return (Choice)childs.get(0);
	}
}
