package fr.univ.orleans.ter.lec.repository;

import android.database.Cursor;
import fr.univ.orleans.ter.lec.model.Choice;
import fr.univ.orleans.ter.lec.persistence.SQLiteHelper;

public class ChoicesRepository extends BasicLECRepository {

	public ChoicesRepository(SQLiteHelper databaseHelper) {
		super(databaseHelper);
		this.tableName = "choices";
		this.init();
	}

	@Override
	protected Object cursorToMember(Cursor cursor) {
		Choice c = new Choice();
		
		c.setId(cursor.getLong(0));
		c.setExerciseId(cursor.getLong(1));
		c.setResult(cursor.getString(2));
		c.setChoice1(cursor.getString(3));
		c.setChoice2(cursor.getString(4));
		c.setChoice3(cursor.getString(5));
		
		return c;
	}

}
