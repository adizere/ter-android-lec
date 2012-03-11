package fr.univ.orleans.ter.lec.model;

public class Tag extends BasicLECModel {

	private String target;
	private String content;

	public String getTarget() {
		return target;
	}

	public void setTarget(String targer) {
		this.target = targer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Tag [id=" + _id + ", target=" + target + ", content=" + content + "]";
	}
}
