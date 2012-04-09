package fr.univ.orleans.ter.lec.persistence.sql;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.univ.orleans.ter.lec.persistence.sql.relation.SQLRelation;

/**
 * Table - Persistence Layer, SQLite
 * 
 * Represents a table from the database structure. Used in creating and loading
 * the database. Has 1..* Column.
 * 
 * @author AdrianSeredinschi
 * 
 */
public class Table {

	private String name = null;
	private ArrayList<Column> columns = null;
	private ArrayList<SQLRelation> relations = null;

	private static final String DROP_STUB = "DROP TABLE IF EXISTS ";
	private static final String CREATE_STUB = "CREATE TABLE IF NOT EXISTS ";

	public Table(String name) {
		super();
		this.name = name;
		this.columns = new ArrayList<Column>();
		this.relations = new ArrayList<SQLRelation>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addColumn(Column c) {
		this.columns.add(c);
	}

	public ArrayList<Column> getColumns() {
		return this.columns;
	}

	public String getDropStatement() {
		return DROP_STUB + this.name + "; ";
	}

	public String getCreateStatement() {
		String createStmt = "";
		createStmt += CREATE_STUB + this.name + " (";

		Iterator<Column> itCol = this.columns.iterator();
		Column c = null;
		while (itCol.hasNext()) {
			c = itCol.next();
			createStmt += c.getName() + " " + c.getType() + " "
					+ c.getOptions();
			if (itCol.hasNext())
				createStmt += ", ";
		}

		createStmt += "); ";
		return createStmt;
	}

	@Override
	public String toString() {
		return "Table [name=" + name + ", columns=" + columns.toString() + "]";
	}

	public void addRelation(SQLRelation r) {
		this.relations.add(r);
	}
	
	public List<SQLRelation> getRelations(){
		return this.relations;
	}

}
