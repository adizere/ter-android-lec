package fr.univ.orleans.ter.lec;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import fr.univ.orleans.ter.lec.example.DbInteractionExample;
import fr.univ.orleans.ter.lec.model.LanguageTag;
import fr.univ.orleans.ter.lec.persistence.SQLiteHelper;
import fr.univ.orleans.ter.lec.repository.mediation.RepositoryMediator;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		SQLiteHelper helper = new SQLiteHelper(getApplicationContext());

		RepositoryMediator repoMediator = new RepositoryMediator(helper);

		DbInteractionExample example = new DbInteractionExample(repoMediator);

		example.basicDBInit();
		List<Object> languages = example.retrieveLanguages();


		Log.d("MainAct", "Number of languages: " + languages.size());
		
		Button Button1 = (Button) findViewById(R.id.Button1);
		
		Button1.setText(languages.get(0).toString());
	}
}