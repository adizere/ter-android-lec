package fr.univ.orleans.ter.lec.model;

public class Exercise extends BasicLECModel {
	
	private Long levelId;
	private Boolean completed;
	private String statement;
	
	public Exercise(){
		super();
	}

	public Long getLevelId() {
		return levelId;
	}

	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	@Override
	public String toString() {
		return "Exercise [levelId=" + levelId + ", completed=" + completed
				+ ", statement=" + statement + "]";
	}
	
	

}
