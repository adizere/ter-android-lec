package fr.univ.orleans.ter.lec.controller;

import android.util.Log;
import fr.univ.orleans.ter.lec.model.Language;
import fr.univ.orleans.ter.lec.model.Method;

public class LevelsController extends BasicLECController {

	private Long languageId;
	private Language language;
	private Long methodId;

	public Long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Long langId) {
		if ( ! this.validId(langId)){
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

	private boolean validId(Long langId) {
		if ( langId != null && langId >= 0L)
			return true;
		return false;
	}

	public void setMethodId(Long methodId) {
		if ( ! this.validId(methodId)){
			Log.e("LevelsController", "Invalid Method id passed.");
		} else {
			this.methodId = methodId;
		}
	}
}
