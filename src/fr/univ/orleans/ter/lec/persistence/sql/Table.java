package fr.univ.orleans.ter.lec.persistence.sql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.util.Log;

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
	private List<HashMap<String, Object>> rows = null;

	private static final String DROP_STUB = "DROP TABLE IF EXISTS ";
	private static final String CREATE_STUB = "CREATE TABLE IF NOT EXISTS ";
	private static final String INSERT_STUB = "INSERT INTO ";

	public Table(String name) {
		super();
		this.name = name;
		this.columns = new ArrayList<Column>();
		this.relations = new ArrayList<SQLRelation>();
		this.rows = new ArrayList<HashMap<String, Object>>();
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
	
	public String getColumnType(String colName) {
		String type = null;
		
		for (Column c : this.columns) {
			if ( c.getName().equalsIgnoreCase(colName)){
				type = c.getType();
			}
		}
		
		if( type == null ){
			Log.w("persistance.sql.Table", "Requested type for unknown column name: " + colName );
			type = "";
		}
		
		return type;
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

		createStmt += ");\n";
		return createStmt + this.getInsertStatement() + "\n";
	}

	/*
	 * Generate the Insert into TABLE_NAME (row1, row2, ..) values (value1,
	 * value2, ..);
	 */
	private String getInsertStatement() {

		if (rows.size() == 0)
			return "";

		String statement = "";
		Iterator<HashMap<String, Object>> itRows = rows.iterator();

		while (itRows.hasNext()) {
			HashMap<String, Object> currentRow = itRows.next();
			Iterator<String> itRowKeys = currentRow.keySet().iterator();

			String rowNames = "(";
			String rowValues = "(";
			while (itRowKeys.hasNext()) {
				String key = itRowKeys.next();

				rowNames += key;

				if (this.getColumnType(key).equals("text")) {
					rowValues += "\"" + currentRow.get(key).toString() + "\"";

				} else {
					rowValues += currentRow.get(key).toString();
				}

				if (itRowKeys.hasNext()) {
					rowNames += ", ";
					rowValues += ", ";
				}
			}
			rowNames += ") VALUES ";
			rowValues += ");\n";
			statement += INSERT_STUB + this.name + rowNames + rowValues;
		}

		return statement;
	}

	@Override
	public String toString() {
		return "Table [name=" + name + ", columns=" + columns.toString() + "]";
	}

	public void addRelation(SQLRelation r) {
		this.relations.add(r);
	}

	public List<SQLRelation> getRelations() {
		return this.relations;
	}

	public void addRow(HashMap<String, Object> row) {
		this.rows.add(row);
	}
}
