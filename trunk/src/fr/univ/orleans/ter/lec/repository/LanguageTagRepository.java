package fr.univ.orleans.ter.lec.repository;

import android.content.ContentValues;
import android.database.Cursor;
import fr.univ.orleans.ter.lec.model.LanguageTag;
import fr.univ.orleans.ter.lec.persistence.SQLiteHelper;

public class LanguageTagRepository extends BasicSqlRepository {

	
	public LanguageTagRepository(SQLiteHelper helper) {
		super(helper);
		this.tableName = "languages_tags";
		this.init();
	}
	
	@Override
	protected Object cursorToMember(Cursor cursor) {
		LanguageTag lt = new LanguageTag(cursor.getLong(0), cursor.getLong(1), cursor.getLong(2));
		return lt;
	}
	
	public LanguageTag createLanguageTag(Long lang_id, Long tag_id){
	
		ContentValues value = new ContentValues();

		value.put(this.columnNames[1], lang_id);
		value.put(this.columnNames[2], tag_id);

		long id = this.insertValue(value);

		return (LanguageTag) this.getMemberById(id);
	}
}
