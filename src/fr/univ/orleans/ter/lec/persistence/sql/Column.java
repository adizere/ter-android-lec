package fr.univ.orleans.ter.lec.persistence.sql;

/**
 * Column - Persistence Layer, SQLite
 * 
 * Represents the column of a table from the database structure.
 * 
 * @author AdrianSeredinschi
 *
 */
public class Column {
	private String name;
	private String type;
	private String options;

	public Column(String name, String type, String options) {
		super();
		this.name = name;
		this.type = type;
		
		if (options == null)
			options = "";
		this.options = options;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	@Override
	public String toString() {
		return "Column [name=" + name + ", type=" + type + ", options="
				+ options + "]";
	}
}
