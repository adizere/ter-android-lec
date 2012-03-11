package fr.univ.orleans.ter.lec.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Language - Model
 * 
 * For each of the Languages defined in the database, the application will
 * provide structured exercises that will help a child progress in using that
 * language.
 * 
 * For example, we might have exercises defined for: French, Arabic, English,
 * etc..
 * 
 * @author AdrianSeredinschi
 * 
 */
public class Language extends BasicLECModel {

	private String name;
	private List<LComponent> lComponents;
	private List<Tag> tags;

	public Language() {
		super();
		this.lComponents = new ArrayList<LComponent>();
		this.tags = new ArrayList<Tag>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<LComponent> getlComponents() {
		return lComponents;
	}

	public void setlComponents(List<LComponent> lComponents) {
		this.lComponents = lComponents;
	}
	
	public void addTag(Tag t) {
		this.tags.add(t);
	}
	
	public List<Tag> getTags(){
		return this.tags;
	}

	@Override
	public String toString() {
		return "Language [" + _id + ", " + name + "]";
	}
}
