package fr.univ.orleans.ter.lec.controller;

import android.util.Log;
import fr.univ.orleans.ter.lec.model.Language;


public class MethodsController extends BasicLECController {

	
	private Long languageId;
	private Language language;
	
	
	public Long getLanguageId() {
		return languageId;
	}
	public void setLanguageId(Long languageId) {
		if ( ! this.validLanguageId(languageId)){
			Log.e("MethodsController", "Invalid Level id passed.");
		} else {
			this.languageId = languageId;
			this.loadLanguage();
		}
	}
	private void loadLanguage() {
		this.language = (Language)this.repoMediator.getRepositoryByTableName("languages").getMemberById(this.languageId);
	}
	private boolean validLanguageId(Long langId) {
		if ( langId != null && langId >= 0L)
			return true;
		return false;
	}
	public Language getLanguage() {
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	
	
}
