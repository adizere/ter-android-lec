package fr.univ.orleans.ter.lec.repository;

import fr.univ.orleans.ter.lec.model.LComponent;
import fr.univ.orleans.ter.lec.persistence.SQLiteHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class LComponentsRepository extends BasicLECRepository {

	public LComponentsRepository(SQLiteHelper helper){
		super(helper);
		this.tableName = "lcomponents";
		this.init();
	}
	
	@Override
	protected Object cursorToMember(Cursor cursor) {
		LComponent lc = new LComponent();
		
		lc.setId(cursor.getLong(0));
		lc.setContent(cursor.getString(1));
		lc.setType(cursor.getString(2));
		
		return lc;
	}
	
	public LComponent createLComponent(String content, String type) {
		ContentValues values = new ContentValues();

		values.put(this.columnNames[1], content);
		values.put(this.columnNames[2], type);
		
		Long id = this.insertValue(values);
		
		return (LComponent)this.getMemberById(id);
	}
}
