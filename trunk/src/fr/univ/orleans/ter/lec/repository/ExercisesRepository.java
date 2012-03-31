package fr.univ.orleans.ter.lec.repository;

import fr.univ.orleans.ter.lec.model.Exercise;
import fr.univ.orleans.ter.lec.persistence.SQLiteHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class ExercisesRepository extends BasicLECRepository {

	public ExercisesRepository(SQLiteHelper helper){
		super(helper);
		this.tableName = "exercises";
		this.init();
	}
	
	@Override
	protected Object cursorToMember(Cursor cursor) {
		Exercise e = new Exercise();
		
		e.setId(cursor.getLong(0));
		e.setLevelId(cursor.getLong(1));
		e.setCompleted(cursor.getString(2));
		e.setStatement(cursor.getString(3));
		
		return e;
	}
	
	public Exercise createExercise(Long levelId, Boolean compl, String stmt){
		ContentValues values = new ContentValues();

		values.put(this.columnNames[1], levelId);
		values.put(this.columnNames[2], compl);
		values.put(this.columnNames[3], stmt);
		
		long id = this.insertValue(values);
		
		return (Exercise)this.getMemberById(id);
	}

}
