package fr.univ.orleans.ter.lec.repository;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fr.univ.orleans.ter.lec.persistence.SQLiteHelper;
import fr.univ.orleans.ter.lec.persistence.sql.Column;
import fr.univ.orleans.ter.lec.persistence.sql.DbStructure;
import fr.univ.orleans.ter.lec.persistence.sql.Table;

/**
 * 
 * SqlRepository - Persistence Layer
 * 
 * Abstract class that acts as an intermediary between the persistence layer and
 * the models. Each Model class has assigned a Repository that handles creating
 * the objects, loading them from the database and also keeping them in an
 * central place - a container.
 * 
 * All Repository classes should inherit from here.
 * 
 * @author AdrianSeredinschi
 * 
 */
public abstract class BasicSqlRepository {

	/*
	 * Attributes accessible only from this (base) class.
	 */
	private SQLiteDatabase database;
	private SQLiteHelper databaseHelper;

	/*
	 * Attributes that need to be considered in each subclass
	 */
	protected List<Object> members;
	protected String tableName;
	protected String[] columnNames;

	public BasicSqlRepository() {
		this.members = new ArrayList<Object>();
	}

	public BasicSqlRepository(SQLiteHelper helper) {
		this.databaseHelper = helper;
		this.database = helper.getWritableDatabase();
		this.members = new ArrayList<Object>();
	}

	protected abstract Object cursorToMember(Cursor cursor);
//	protected abstract void 

	public abstract Object getMemberById(long id);

	/*
	 * Initializes the columnNames attribute. Called after the tableName is set,
	 * usually from the class constructor.
	 */
	protected void init() {
		DbStructure dbS = this.databaseHelper.getDbStructure();
		Table t = dbS.getTableByName(this.tableName);

		this.columnNames = new String[t.getColumns().size()];

		Integer count = 0;
		for (Column col : t.getColumns()) {
			columnNames[count++] = col.getName();
		}
	}

	public List<Object> getMembers() {
		if (this.members.size() == 0)
			this.retrieveAllMembers();
		return members;
	}

	private void retrieveAllMembers() {
		this.members.clear();

		Cursor cursor = this.database.query(tableName, columnNames, null, null,
				null, null, null);

		// Nothing to do here...
		if (cursor.getCount() == 0) {
			cursor.close();
			return;
		}

		cursor.moveToFirst();
		while (!cursor.isLast() && cursor.getCount() != 0) {
			Object member = this.cursorToMember(cursor);
			this.members.add(member);
			cursor.moveToNext();
		}

		cursor.close();
	}

	public void close() {
		databaseHelper.close();
	}

	protected long insertValues(ContentValues values) {
		long insertId = this.database.insert(this.tableName, null, values);

		// forcing refresh of our repository, to be in sync..
		this.retrieveAllMembers();

		return insertId;
	}
}
