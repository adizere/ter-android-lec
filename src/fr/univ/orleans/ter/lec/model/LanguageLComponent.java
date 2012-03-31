package fr.univ.orleans.ter.lec.model;

import android.util.Log;
import fr.univ.orleans.ter.lec.persistence.sql.relation.SQLRelation;
import fr.univ.orleans.ter.lec.persistence.sql.relation.roles.IntermediateRole;

/**
 * LanguageLComponent - Model
 * 
 * Class that links between a Language and an LComponents, being the
 * intermediary in a many-to-many relation.
 * 
 * The relation name is given inside this.relName;
 * The left member is represented by an LComponent.
 * The right member is a Language.
 * 
 * @author AdrianSeredinschi
 * 
 */
public class LanguageLComponent extends BasicLECModel implements
		IntermediateRole {

	private Long lcomponentId;
	private Long languageId;

	public LanguageLComponent() {
		super();
	}

	public LanguageLComponent(Long lcomponentId, Long languageId) {
		super();
		this.lcomponentId = lcomponentId;
		this.languageId = languageId;
	}

	public Long getLcomponentId() {
		return lcomponentId;
	}

	public void setLcomponentId(Long lcomponentId) {
		this.lcomponentId = lcomponentId;
	}

	public Long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}

	public Long getLeftPartnerIdentity(String relName) {
		if (!relName.equalsIgnoreCase(SQLRelation.RELNAME_LANGUAGES_LCOMPONENTS)) {
			Log.e(this.getClass().toString(),
					"Invalid relationName left member requested: " + relName);
			return null;
		}
		return this.getLcomponentId();
	}

	public Long getRightPartnerIdentity(String relName) {
		if (!relName.equalsIgnoreCase(SQLRelation.RELNAME_LANGUAGES_LCOMPONENTS)) {
			Log.e(this.getClass().toString(),
					"Invalid relationName right member requested: " + relName);
			return null;
		}
		return this.getLanguageId();
	}

}
