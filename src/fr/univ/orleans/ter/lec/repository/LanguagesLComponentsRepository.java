package fr.univ.orleans.ter.lec.repository;

import fr.univ.orleans.ter.lec.model.Language;
import fr.univ.orleans.ter.lec.model.LanguageLComponent;
import fr.univ.orleans.ter.lec.persistence.SQLiteHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class LanguagesLComponentsRepository extends BasicLECRepository {

	public LanguagesLComponentsRepository(SQLiteHelper helper) {
		super(helper);
		this.tableName = "languages_lcomponents";
		this.init();
	}
	
	/*
	 * Creation of a new LanguageLComponent
	 */
	public LanguageLComponent createLanguage(Long lcompId, Long langId) {
		ContentValues values = new ContentValues();

		values.put(this.columnNames[1], langId);
		values.put(this.columnNames[2], lcompId);

		long id = this.insertValue(values);

		return (LanguageLComponent) this.getMemberById(id);
	}
	
	@Override
	protected Object cursorToMember(Cursor cursor) {
		LanguageLComponent ll = new LanguageLComponent();
		
		ll.setId(cursor.getLong(0));
		ll.setLanguageId(cursor.getLong(1));
		ll.setLcomponentId(cursor.getLong(2));
		
		return ll;
	}

}
