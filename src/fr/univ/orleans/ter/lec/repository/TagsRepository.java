package fr.univ.orleans.ter.lec.repository;

import android.content.ContentValues;
import android.database.Cursor;
import fr.univ.orleans.ter.lec.model.Tag;
import fr.univ.orleans.ter.lec.persistence.SQLiteHelper;

public class TagsRepository extends BasicLECRepository {

	public TagsRepository(SQLiteHelper helper) {
		super(helper);
		this.tableName = "tags";
		this.init();
	}

	@Override
	protected Object cursorToMember(Cursor cursor) {
		Tag t = new Tag();
		t.setId(cursor.getLong(0));
		t.setContent(cursor.getString(1));
		t.setTarget(cursor.getString(2));
		return t;
	}

	public Tag createTag(String target,
			String content) {
		ContentValues value = new ContentValues();

		value.put(this.columnNames[1], target);
		value.put(this.columnNames[2], content);

		long id = this.insertValue(value);

		return (Tag) this.getMemberById(id);
	}

}
