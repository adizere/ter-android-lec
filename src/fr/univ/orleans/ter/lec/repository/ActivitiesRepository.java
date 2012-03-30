package fr.univ.orleans.ter.lec.repository;

import fr.univ.orleans.ter.lec.persistence.SQLiteHelper;
import android.database.Cursor;

public class ActivitiesRepository extends BasicLECRepository {

	public ActivitiesRepository(SQLiteHelper databaseHelper) {
		super(databaseHelper);
		this.tableName = "activities";
		this.init();
	}

	@Override
	protected Object cursorToMember(Cursor cursor) {
		// TODO Auto-generated method stub
		return null;
	}

}
