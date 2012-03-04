package fr.univ.orleans.ter.lec.repository;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import fr.univ.orleans.ter.lec.persistence.SQLiteHelper;
import fr.univ.orleans.ter.lec.persistence.sql.Column;
import fr.univ.orleans.ter.lec.persistence.sql.Table;

/**
 * 
 * SqlRepository
 * 
 * Abstract class that acts as an intermediary between the persistence layer and the models.
 * Each Model class has assigned a Repository that handles creating the objects, loading them from
 * the database and also keeping them in an central place - a container.
 * 
 * All Repository classes should inherit from here.
 * 
 * @author AdrianSeredinschi
 *
 */
public abstract class SqlRepository {
	
	/*
	 * Attributes accessible only from this (base) class.
	 */
	private SQLiteDatabase database;
	private SQLiteHelper databaseHelper;
	
	/*
	 * Attributes that need to be considered in each subclass
	 */
	protected List<Object> members; // the container for all the members
	protected String tableName; // name of the database table from/to were the member are retrieved/inserted
	protected String[] columnNames;

	public SqlRepository() {
		members = new ArrayList<Object>();
	}
	
	/*
	 * Initializes the columnNames attribute.
	 * Called after the tableName is set, usually from the class constructor.
	 */
	protected void init() {
		Table t = this.databaseHelper.getDbStructure().getTableByName(this.tableName);
		
		Integer count = 0;
		for (Column col : t.getColumns()) {
			columnNames[count++] = col.getName();
		}
	}

	public List<Object> getMembers() {
		return members;
	}

	public void open() {
		database = databaseHelper.getWritableDatabase();
	}

	public void close() {
		databaseHelper.close();
	}
	
	protected void insertValues(ContentValues values) {
		long insertId = this.database.insert(this.tableName, null, values);
	}
}
