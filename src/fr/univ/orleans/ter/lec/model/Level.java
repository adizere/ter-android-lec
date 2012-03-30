package fr.univ.orleans.ter.lec.model;

public class Level extends BasicLECModel {

	private Long languageId;
	private Long methodId;
	private Boolean completed;
	private String name;
	
	public Level(){
		super();
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
	public Boolean getCompleted() {
		return completed;
	}
	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public void setCompleted(String str) {
		if (str.equals("1")){
			this.completed = true;
		} else {
			this.completed = false;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Level [languageId=" + languageId + ", methodId=" + methodId
				+ ", completed=" + completed + ", name=" + name + "]";
	}
}
