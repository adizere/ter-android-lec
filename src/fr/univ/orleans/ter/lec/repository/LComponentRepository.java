package fr.univ.orleans.ter.lec.repository;

import fr.univ.orleans.ter.lec.persistence.SQLiteHelper;
import android.database.Cursor;

public class LComponentRepository extends BasicSqlRepository {

	public LComponentRepository(SQLiteHelper helper){
		super(helper);
		this.tableName = "lcomponents";
		this.init();
	}
	
	@Override
	protected Object cursorToMember(Cursor cursor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getMemberById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
