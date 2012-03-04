package fr.univ.orleans.ter.lec.repository;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;

import fr.univ.orleans.ter.lec.model.Language;
import fr.univ.orleans.ter.lec.persistence.SQLiteHelper;
import fr.univ.orleans.ter.lec.persistence.sql.DbStructure;

public class LanguageRepository extends SqlRepository {

	public LanguageRepository(SQLiteHelper helper) {
		super();
		this.tableName = "languages";
		this.init();
	}

	public Language createLanguage(String name, Long alphabetSetId) {
		ContentValues values = new ContentValues();

		values.put(this.columnNames[1], name);
		values.put(this.columnNames[2], alphabetSetId);

		this.insertValues(values);

		return null;
	}

}
