package fr.univ.orleans.ter.lec.model;

import android.util.Log;
import fr.univ.orleans.ter.lec.persistence.sql.relation.SQLRelation;
import fr.univ.orleans.ter.lec.persistence.sql.relation.roles.IntermediateRole;

public class LanguageTag extends BasicLECModel implements IntermediateRole {

	private Long languageId;
	private Long tagId;

	
	public LanguageTag(Long _id, Long lang_id, long tag_id){
		this._id = _id;
		this.languageId = lang_id;
		this.tagId = tag_id;
	}
	
	public Long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Long language_id) {
		this.languageId = language_id;
	}

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tag_id) {
		this.tagId = tag_id;
	}

	@Override
	public String toString() {
		return "LanguageTag [language_id=" + languageId + ", tag_id=" + tagId
				+ ", _id=" + _id + "]";
	}

	public Long getLeftPartnerIdentity(String relName) {
		if (!relName.equalsIgnoreCase(SQLRelation.RELNAME_LANGUAGES_TAGS)) {
			Log.e(this.getClass().toString(),
					"Invalid relationName left member requested: " + relName);
			return null;
		}
		return this.getTagId();
	}

	public Long getRightPartnerIdentity(String relName) {
		if (!relName.equalsIgnoreCase(SQLRelation.RELNAME_LANGUAGES_TAGS)) {
			Log.e(this.getClass().toString(),
					"Invalid relationName right member requested: " + relName);
			return null;
		}
		return this.getLanguageId();
	}
}
