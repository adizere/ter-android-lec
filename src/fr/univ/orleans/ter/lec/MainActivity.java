package fr.univ.orleans.ter.lec;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import fr.univ.orleans.ter.lec.example.DbInteractionExample;
import fr.univ.orleans.ter.lec.persistence.SQLiteHelper;
import fr.univ.orleans.ter.lec.repository.GlobalRepository;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		SQLiteHelper helper = new SQLiteHelper(getApplicationContext());

		GlobalRepository globalRepo = new GlobalRepository(helper);

		DbInteractionExample example = new DbInteractionExample(globalRepo);

//		example.insertions();
		List<Object> languages = example.retrieveLanguages();
		List<Object> tags = example.retrieveTags();
		List<Object> languages_tags = example.retrieveLanguagesTags();

		Log.d("MainAct", "Number of languages: " + languages.size());
		Log.d("MainAct", "Number of tags: " + tags.size());
		Log.d("MainAct", "Number of languages_tags: " + languages_tags.size());

		helper.getDbCreateStatement();
		helper.getDbDropStatement();
	}
}