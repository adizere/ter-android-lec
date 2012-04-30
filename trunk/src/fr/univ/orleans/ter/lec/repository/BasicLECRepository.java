package fr.univ.orleans.ter.lec.repository;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import fr.univ.orleans.ter.lec.model.BasicLECModel;
import fr.univ.orleans.ter.lec.persistence.SQLiteHelper;
import fr.univ.orleans.ter.lec.persistence.sql.Column;
import fr.univ.orleans.ter.lec.persistence.sql.DbStructure;
import fr.univ.orleans.ter.lec.persistence.sql.Table;
import fr.univ.orleans.ter.lec.repository.mediation.RepositoryMediator;

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
public abstract class BasicLECRepository {

	/*
	 * Attributes accessible only from this (base) class.
	 */
	private SQLiteDatabase database;
	private SQLiteHelper databaseHelper;
	
	/*
	 * Attributes that need to be considered in each subclass
	 */
	protected String tableName;
	protected List<Object> members;
	protected String[] columnNames;
	
	/*
	 * Static mediator shared across all repositories
	 */
	protected static RepositoryMediator repoMediator;

	public BasicLECRepository() {
		this.members = new ArrayList<Object>();
	}

	public BasicLECRepository(SQLiteHelper helper) {
		this.databaseHelper = helper;
		this.database = helper.getWritableDatabase();
		this.members = new ArrayList<Object>();
	}

	protected abstract Object cursorToMember(Cursor cursor);

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
	
	public Object getMemberById(long id){
		if (this.members.size() == 0)
			this.refresh();
		
		for (Object member : this.members) {
			if (((BasicLECModel) member).getId() == id) {
				return member;
			}
		}

		Log.w(this.getClass().toString(), "Could not find any member with id " + id);
		return null;
	}

	public List<Object> getMembers() {
		if (this.members.size() == 0)
			this.refresh();
		return members;
	}

	private void refresh() {
		this.members.clear();

		Cursor cursor = this.database.query(this.tableName, this.columnNames, null, null,
				null, null, null);

		// Nothing to do here...
		if (cursor.getCount() == 0) {
			cursor.close();
			return;
		}

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Object member = this.cursorToMember(cursor);
			this.members.add(member);
			cursor.moveToNext();
		}

		cursor.close();
	}

	public void close() {
		databaseHelper.close();
	}

	protected long insertValue(ContentValues values) {
		long insertId = this.database.insert(this.tableName, null, values);

		// forcing refresh of our repository, to be in sync..
		this.refresh();

		return insertId;
	}
	
	public void deleteMemberById(Long memberId){
		Log.i(this.getClass().toString(), "Deleting Member id: " + memberId);
		this.database.delete(this.tableName, "_id = " + memberId, null );
	}
	
	protected void updateValueById(ContentValues values, Long id){
		String whereClause = "_id=?";
		String[] whereArgs = {id.toString()};
		
		this.database.update(this.tableName, values, whereClause, whereArgs);
	}
	
	public static void setRepositoryMediator(RepositoryMediator mediator){
		BasicLECRepository.repoMediator = mediator;
	}
}
