package fr.univ.orleans.ter.lec.repository;

import fr.univ.orleans.ter.lec.model.Language;
import fr.univ.orleans.ter.lec.model.Level;
import fr.univ.orleans.ter.lec.persistence.SQLiteHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class LevelsRepository extends BasicLECRepository {

	public LevelsRepository(SQLiteHelper databaseHelper) {
		super(databaseHelper);
		this.tableName = "levels";
		this.init();
	}

	@Override
	protected Object cursorToMember(Cursor cursor) {
		Level l = new Level();
		
		l.setId(cursor.getLong(0));
		l.setLanguageId(cursor.getLong(1));
		l.setMethodId(cursor.getLong(2));
		l.setCompleted(cursor.getString(3));
		l.setName(cursor.getString(4));
		
		return l;
	}
	
	public Level createLevel(Long langId, Long methodId, String name){
		return this.createLevel(langId, methodId, false, name);
	}

	public Level createLevel(Long langId, Long methodId, Boolean completed, String name) {
		ContentValues values = new ContentValues();
		
		values.put(this.columnNames[1], langId);
		values.put(this.columnNames[2], methodId);
		values.put(this.columnNames[3], completed);
		values.put(this.columnNames[4], name);

		long id = this.insertValue(values);

		return (Level) this.getMemberById(id);
	}

	public void setCompleted(Long levelId) {
		ContentValues values = new ContentValues();
		
		values.put("completed", 1);
		
		this.updateValueById(values, levelId);
		((Level)this.getMemberById(levelId)).setCompleted(true);
	}
}
