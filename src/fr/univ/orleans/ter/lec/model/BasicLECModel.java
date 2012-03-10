package fr.univ.orleans.ter.lec.model;

/**
 * 
 * BasicLECModel - Model base class, from which all model classes will inherit.
 * 
 * @author AdrianSeredinschi
 * 
 */
public abstract class BasicLECModel {

	protected long _id;

	public long getId() {
		return _id;
	}

	public void setId(long _id) {
		this._id = _id;
	}

}
