package fr.univ.orleans.ter.lec.repository;

import fr.univ.orleans.ter.lec.model.Language;
import fr.univ.orleans.ter.lec.model.Method;
import fr.univ.orleans.ter.lec.persistence.SQLiteHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class MethodsRepository extends BasicLECRepository {

	public MethodsRepository(SQLiteHelper databaseHelper) {
		super(databaseHelper);
		this.tableName = "methods";
		this.init();
	}

	@Override
	protected Object cursorToMember(Cursor cursor) {
		Method m = new Method();
		
		m.setId(cursor.getLong(0));
		m.setCompleted(cursor.getString(1));
		m.setName(cursor.getString(2));
		
		
		return m;
	}
	
	public Method createMethod(String name) {
		return this.createMethod(name, false);
	}
	
	public Method createMethod(String name, Boolean completed) {
		ContentValues values = new ContentValues();

		values.put(this.columnNames[1], completed);
		values.put(this.columnNames[2], name);
		

		long id = this.insertValue(values);

		return (Method) this.getMemberById(id);
	}

}
