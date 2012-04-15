package fr.univ.orleans.ter.lec.controller;

import android.util.Log;
import fr.univ.orleans.ter.lec.model.Language;

public class LevelsController extends BasicLECController {

	private Long languageId;
	private Language language;

	public Long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Long langId) {
		if ( ! this.validLanguageId(langId)){
			Log.e("LevelsController", "Invalid Level id passed.");
		} else {
			this.languageId = langId;
			this.loadLanguage();
		}
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}
	
	private void loadLanguage(){
		this.language = (Language)this.repoMediator.getRepositoryByTableName("languages").getMemberById(this.languageId);
	}

	private boolean validLanguageId(Long langId) {
		if ( langId != null && langId >= 0L)
			return true;
		return false;
	}

}
