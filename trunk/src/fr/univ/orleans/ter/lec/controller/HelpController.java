package fr.univ.orleans.ter.lec.controller;

import java.util.List;
import java.util.Locale;

import android.util.Log;

import fr.univ.orleans.ter.lec.model.Language;
import fr.univ.orleans.ter.lec.model.Tag;
import fr.univ.orleans.ter.lec.utility.LocaleDefs;

public class HelpController extends BasicLECController {

	private Long languageId;
	private Language language;
	
	
	public Long getLanguageId() {
		return languageId;
	}
	public void setLanguageId(Long languageId) {
		if (this.validLanguageId(languageId)) {
			this.languageId = languageId;
			this.loadLanguage();
		} else {
			Log.w(HelpController.class.toString(),
					"Invalid language ID passed.");
		}
	}
	
	private Boolean validLanguageId(Long id) {
		if (id != null && id > 0L) {
			return true;
		} else {
			return false;
		}
	}

	
	private void loadLanguage() {
		this.setLanguage((Language) BasicLECController.repoMediator.getRepositoryByTableName("languages").getMemberById(this.getLanguageId()));
	}
	public Language getLanguage() {
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	public List<Tag> getTags() {
		return this.language.getTags();
	}
	public Locale getLanguageAsLocale() {
		return LocaleDefs.getLocaleForLanguageName(this.language.getName());
	}
	
	
}
