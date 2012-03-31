package fr.univ.orleans.ter.lec.repository;

import fr.univ.orleans.ter.lec.model.LanguageMethod;
import fr.univ.orleans.ter.lec.persistence.SQLiteHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class LanguagesMethodsRepository extends BasicLECRepository {

	public LanguagesMethodsRepository(SQLiteHelper databaseHelper) {
		super(databaseHelper);
		this.tableName = "languages_methods";
		this.init();
	}

	@Override
	protected Object cursorToMember(Cursor cursor) {
		LanguageMethod lm = new LanguageMethod();
		
		lm.setId(cursor.getLong(0));
		lm.setLanguageId(cursor.getLong(1));
		lm.setMethodId(cursor.getLong(2));
		
		return lm;
	}
	
	public LanguageMethod createLanguageMethod(Long lanId, Long methodId){
		ContentValues values = new ContentValues();
		
		values.put(this.columnNames[1], lanId);
		values.put(this.columnNames[2], methodId);
		
		long id = this.insertValue(values);
		
		return (LanguageMethod)this.getMemberById(id);
	}

}
