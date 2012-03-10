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
	private long alphabet_set_id;
	private List<LComponent> lComponents;

	public Language() {
		super();
		this.lComponents = new ArrayList<LComponent>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAlphabet_set_id() {
		return alphabet_set_id;
	}

	public void setAlphabet_set_id(long alphabet_set_id) {
		this.alphabet_set_id = alphabet_set_id;
	}

	public List<LComponent> getlComponents() {
		return lComponents;
	}

	public void setlComponents(List<LComponent> lComponents) {
		this.lComponents = lComponents;
	}

	@Override
	public String toString() {
		return "Language [" + _id + ", " + name + ", " + alphabet_set_id + "]";
	}
}
