package fr.univ.orleans.ter.lec.persistence;

import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import fr.univ.orleans.ter.lec.persistence.sql.DbStructure;
import fr.univ.orleans.ter.lec.persistence.sql.Table;

/**
 * Persistence Layer
 * 
 * SQLite Convenience Class - Helper needed for providing the database
 * creation/update functionality.
 * 
 * Makes use of the DbStructure class in order to obtain information with regard
 * to the SQL statements needed for various tables.
 * 
 * @author AdrianSeredinschi
 * 
 */
public class SQLiteHelper extends SQLiteOpenHelper {

	// Some *constants*
	private static final String DATABASE_NAME = "lec.db";
	private static final int DATABASE_VERSION = 7;

	private DbStructure dbStructure;

	public SQLiteHelper(Context context, DbStructure dbStruct) {
		super(context, DATABASE_NAME, null, dbStruct.getVersion());
		this.dbStructure = dbStruct;
	}

	public void onCreate(SQLiteDatabase database) {
		database.execSQL(getDbCreateStatement());
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(SQLiteHelper.class.getName(), "Upgrading/Downgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data.");

		onDrop(db);
		onCreate(db);
	}
	
	public void onDowngrade (SQLiteDatabase db, int oldVersion, int newVersion) {
		this.onUpgrade(db, oldVersion, newVersion);
	}

	public String getDbCreateStatement() {
		String createStmt = "";

		ArrayList<Table> tables = dbStructure.getTables();
		for (Table t : tables) {
			createStmt += t.getCreateStatement();
		}

		return createStmt;
	}

	private void onDrop(SQLiteDatabase database) {
		database.execSQL(getDbDropStatement());
	}

	public String getDbDropStatement() {
		String dropStmt = "";
		ArrayList<Table> tables = dbStructure.getTables();
		for (Table t : tables) {
			dropStmt += t.getDropStatement();
		}

		return dropStmt;
	}

	public DbStructure getDbStructure() {
		return this.dbStructure;
	}
}
