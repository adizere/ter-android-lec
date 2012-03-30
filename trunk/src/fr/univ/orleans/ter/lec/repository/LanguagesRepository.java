package fr.univ.orleans.ter.lec.repository;

import android.content.ContentValues;
import android.database.Cursor;
import fr.univ.orleans.ter.lec.model.Language;
import fr.univ.orleans.ter.lec.persistence.SQLiteHelper;

/**
 * 
 * LanguagesRepository - Persistence Layer
 * 
 * Class that server as intermediary for manipulating Language (model) objects
 * retrieved from the DB.
 * 
 * @author AdrianSeredinschi
 * 
 */
public class LanguagesRepository extends BasicLECRepository {

	public LanguagesRepository(SQLiteHelper helper) {
		super(helper);
		this.tableName = "languages";
		this.init();
	}

	/*
	 * Creation of a new Language. Returns the new object that was created.
	 */
	public Language createLanguage(String name) {
		ContentValues values = new ContentValues();

		values.put(this.columnNames[1], name);

		long id = this.insertValue(values);

		return (Language) this.getMemberById(id);
	}

	@Override
	protected Object cursorToMember(Cursor cursor) {
		Language l = new Language();
		l.setId(cursor.getLong(0));
		l.setName(cursor.getString(1));
		return l;
	}
}
