package fr.univ.orleans.ter.lec.persistence.sql;

import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;

import fr.univ.orleans.ter.lec.R;
import fr.univ.orleans.ter.lec.persistence.SQLiteHelper;
import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;

/**
 * DbStructure - Persistence Layer, SQLite
 * 
 * Intermediary class between the SQLiteHelper and the statements that this
 * class needs in order to ensure proper db initialisation.
 * 
 * Loads the structure of the database (comprising mainly of an array of Table)
 * from an external configuration file (XML) and returns it.
 * 
 * @author AdrianSeredinschi
 * 
 */
public class DbStructure {

	private Context context = null;
	private ArrayList<Table> tables = null;
	private String dbVersion = null;

	public DbStructure(Context context) {
		this.context = context;
		this.tables = new ArrayList<Table>();
		this.dbVersion = "1";
	}

	/*
	 * Loads the Database structure from an external configuration file
	 * Effectively sets the 'tables' property.
	 */
	private void load() {

		XmlResourceParser myxml = context.getResources().getXml(
				R.xml.table_definitions);
		String NodeValue = null;
		int eventType = XmlPullParser.END_DOCUMENT;

		try {
			myxml.next();

			eventType = myxml.getEventType();

			Table t = null;
			Column c = null;

			while (eventType != XmlPullParser.END_DOCUMENT) {

				NodeValue = myxml.getName();

				if (eventType == XmlPullParser.START_TAG) {

					if (NodeValue.equalsIgnoreCase("table")) {

						String tableName = myxml
								.getAttributeValue(null, "name");
						t = new Table(tableName);
					}

					if (NodeValue.equalsIgnoreCase("column")) {
						String colName = myxml.getAttributeValue(null, "name");
						String colType = myxml.getAttributeValue(null, "type");
						String colOptions = myxml.getAttributeValue(null,
								"options");

						c = new Column(colName, colType, colOptions);
						t.addColumn(c);
					}

				} else if (eventType == XmlPullParser.END_TAG) {
					if (NodeValue.equalsIgnoreCase("table")) {
						if (t != null)
							this.tables.add(t);
					}
				}

				eventType = myxml.next(); // Get next event from xml parser
			}
		} catch (Exception e) {
			Log.e(SQLiteHelper.class.getName(),
					"Failed parsing the XML with the DB structure: "
							+ e.getMessage());
			e.printStackTrace();
		}
	}

	public ArrayList<Table> getTables() {
		if (this.tables.size() == 0)
			this.load();

		return this.tables;
	}

	public Table getTableByName(String tableName) {
		if (this.tables.size() == 0)
			this.load();
		for (Table tab : this.tables) {
			if (tab.getName().equals(tableName)) {
				return tab;
			}
		}
		Log.e(SQLiteHelper.class.getName(), "Could not find any table having the name: " + tableName);
		return null;
	}

}
