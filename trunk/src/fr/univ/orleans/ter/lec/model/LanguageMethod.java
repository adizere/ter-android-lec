package fr.univ.orleans.ter.lec.model;

import android.util.Log;
import fr.univ.orleans.ter.lec.persistence.sql.relation.SQLRelation;
import fr.univ.orleans.ter.lec.persistence.sql.relation.roles.IntermediateRole;

public class LanguageMethod extends BasicLECModel implements IntermediateRole {

	private Long languageId;
	private Long methodId;

	public LanguageMethod() {
		super();
	}

	public LanguageMethod(Long languageId, Long methodId) {
		super();
		this.languageId = languageId;
		this.methodId = methodId;
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

	public Long getLeftPartnerIdentity(String relName) {
		if (!relName.equalsIgnoreCase(SQLRelation.RELNAME_LANGUAGES_METHODS)) {
			Log.e(this.getClass().toString(),
					"Invalid relationName left member requested: " + relName);
			return null;
		}
		return this.getMethodId();
	}

	public Long getRightPartnerIdentity(String relName) {
		if (!relName.equalsIgnoreCase(SQLRelation.RELNAME_LANGUAGES_METHODS)) {
			Log.e(this.getClass().toString(),
					"Invalid relationName right member requested: " + relName);
			return null;
		}
		return this.getLanguageId();
	}

}
