/**
 * 
 */
package fr.univ.orleans.ter.lec.model;

/**
 * @author AdrianSeredinschi
 *
 */
public class Method extends BasicLECModel {
	
	String name;
	Boolean completed;
	
	public Method(){
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public void setCompleted(String string) {
		if (string.equals("1")){
			this.completed = true;
		} else {
			this.completed = false;
		}
	}

	@Override
	public String toString() {
		return "Method [name=" + name + ", completed=" + completed + "]";
	}
	
}
