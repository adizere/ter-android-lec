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
		l.setCompleted(cursor.getString(1));
		l.setLanguageId(cursor.getLong(2));
		l.setMethodId(cursor.getLong(3));
		return l;
	}
	
	public Level createLevel(Long langId, Long methodId){
		return this.createLevel(langId, methodId, false);
	}

	public Level createLevel(Long langId, Long methodId, Boolean completed) {
		ContentValues values = new ContentValues();

		values.put(this.columnNames[1], completed);
		values.put(this.columnNames[1], langId);
		values.put(this.columnNames[1], methodId);

		long id = this.insertValue(values);

		return (Level) this.getMemberById(id);
	}
}
