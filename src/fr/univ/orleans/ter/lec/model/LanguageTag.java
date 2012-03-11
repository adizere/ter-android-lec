package fr.univ.orleans.ter.lec.model;

public class LanguageTag extends BasicLECModel {

	private Long language_id;
	private Long tag_id;

	
	public LanguageTag(Long _id, Long lang_id, long tag_id){
		this._id = _id;
		this.language_id = lang_id;
		this.tag_id = tag_id;
	}
	
	public Long getLanguage_id() {
		return language_id;
	}

	public void setLanguage_id(Long language_id) {
		this.language_id = language_id;
	}

	public Long getTag_id() {
		return tag_id;
	}

	public void setTag_id(Long tag_id) {
		this.tag_id = tag_id;
	}

	@Override
	public String toString() {
		return "LanguageTag [language_id=" + language_id + ", tag_id=" + tag_id
				+ ", _id=" + _id + "]";
	}
}
