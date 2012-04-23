package fr.univ.orleans.ter.lec.model;

import android.util.Log;
import fr.univ.orleans.ter.lec.persistence.sql.relation.SQLRelation;
import fr.univ.orleans.ter.lec.persistence.sql.relation.roles.ChildRole;
import fr.univ.orleans.ter.lec.persistence.sql.relation.roles.ParentRole;

public class Choice extends BasicLECModel implements ChildRole {

	private Long exerciseId;
	private String result;
	private String choice1;
	private String choice2;
	private String choice3;
	
	private ParentRole exerciseParent;

	public Long getExerciseId() {
		return exerciseId;
	}

	public void setExerciseId(Long exerciseId) {
		this.exerciseId = exerciseId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getChoice1() {
		return choice1;
	}

	public void setChoice1(String choice1) {
		this.choice1 = choice1;
	}

	public String getChoice2() {
		return choice2;
	}

	public void setChoice2(String choice2) {
		this.choice2 = choice2;
	}

	public String getChoice3() {
		return choice3;
	}

	public void setChoice3(String choice3) {
		this.choice3 = choice3;
	}

	public void setParent(String relName, ParentRole pr) {
		if (this.validRelationName(relName)) {
			this.exerciseParent = pr;
		}
	}

	private boolean validRelationName(String relName) {
		if (relName.equalsIgnoreCase(SQLRelation.RELNAME_CHOICES_EXERCISE)) {
			return true;
		} else {
			Log.e(this.getClass().toString(),
					"Invalid relationName requested: " + relName);
			return false;
		}
	}

	public ParentRole getParent(String relName) {
		if (this.validRelationName(relName)) {
			return this.exerciseParent;
		} else {
			return null;
		}
	}

	public Long getParentIdentity(String relName) {
		if (this.validRelationName(relName)) {
			return this.exerciseId;
		} else {
			return null;
		}
	}

}
