package fr.univ.orleans.ter.lec.repository;

import fr.univ.orleans.ter.lec.persistence.SQLiteHelper;
import android.database.Cursor;

public class LanguagesMethodsRepository extends BasicLECRepository {

	public LanguagesMethodsRepository(SQLiteHelper databaseHelper) {
		super(databaseHelper);
		this.tableName = "languages_methods";
		this.init();
	}

	@Override
	protected Object cursorToMember(Cursor cursor) {
		// TODO Auto-generated method stub
		return null;
	}

}
