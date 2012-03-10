package fr.univ.orleans.ter.lec.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import fr.univ.orleans.ter.lec.model.Language;
import fr.univ.orleans.ter.lec.persistence.SQLiteHelper;

/**
 * 
 * LanguageRepository - Persistence Layer
 * 
 * Class that server as intermediary for manipulating Language (model) objects
 * retrieved from the DB.
 * 
 * @author AdrianSeredinschi
 *
 */
public class LanguageRepository extends BasicSqlRepository {

	public LanguageRepository(SQLiteHelper helper) {
		super(helper);
		this.tableName = "languages";
		this.init();
	}

	public Language createLanguage(String name, Long alphabetSetId) {
		ContentValues values = new ContentValues();

		values.put(this.columnNames[1], name);
		values.put(this.columnNames[2], alphabetSetId);

		long id = this.insertValues(values);
		
		return (Language) this.getMemberById( id );
	}

	@Override
	protected Object cursorToMember(Cursor cursor) {
		Language l = new Language();
		l.setId(cursor.getLong(0));
		l.setName(cursor.getString(1));
		l.setAlphabet_set_id(cursor.getLong(2));
		return l;
	}

	@Override
	public Object getMemberById(long id) {
		for (Object lang : this.members) {
			if (((Language)lang).getId() == id) {
				return lang;
			}
		}
		
		Log.w("LanguageRepository", "Could not find any Language with id " + id );
		
		return null;
	}

}
