package fr.univ.orleans.ter.lec.persistence.sql;

import java.util.ArrayList;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;

import fr.univ.orleans.ter.lec.R;
import fr.univ.orleans.ter.lec.persistence.SQLiteHelper;
import fr.univ.orleans.ter.lec.persistence.sql.relation.ManyToMany;
import fr.univ.orleans.ter.lec.persistence.sql.relation.OneToMany;
import fr.univ.orleans.ter.lec.persistence.sql.relation.SQLRelation;
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
	private HashMap<String, Table> tables = null;
	private String dbVersion = null;

	public DbStructure(Context context) {
		this.context = context;
		this.dbVersion = "1";
		this.tables = new HashMap<String, Table>();
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
			SQLRelation r = null;

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

					if (NodeValue.equalsIgnoreCase("relation")) {
						String name = myxml.getAttributeValue(null, "name");
						Integer type = Integer.parseInt(myxml
								.getAttributeValue(null, "type"));

						if (type == SQLRelation.RELTYPE_ONE_TO_MANY) {
							String parent = myxml.getAttributeValue(null,
									"parentTable");
							String child = myxml.getAttributeValue(null,
									"childTable");

							r = new OneToMany(parent, child, name);
						} else if (type == SQLRelation.RELTYPE_MANY_TO_MANY) {
							String interm = myxml.getAttributeValue(null,
									"intermediateTable");
							String right = myxml.getAttributeValue(null,
									"rightPartnerTable");
							String left = myxml.getAttributeValue(null,
									"leftPartnerTable");

							r = new ManyToMany(interm, right, left, name);
						}
						t.addRelation(r);
					}

				} else if (eventType == XmlPullParser.END_TAG) {
					if (NodeValue.equalsIgnoreCase("table")) {
						if (t != null)
							this.tables.put(t.getName(), t);
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

		return new ArrayList<Table>(this.tables.values());
	}

	public Table getTableByName(String tableName) {
		if (this.tables.size() == 0)
			this.load();

		Table t = this.tables.get(tableName);

		if (t == null) {
			Log.e(SQLiteHelper.class.getName(),
					"Could not find any table having the name: " + tableName);
		}
		return t;
	}

}
