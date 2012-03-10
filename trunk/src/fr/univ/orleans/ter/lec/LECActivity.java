package fr.univ.orleans.ter.lec;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import fr.univ.orleans.ter.lec.persistence.SQLiteHelper;
import fr.univ.orleans.ter.lec.utility.Exercises;

public class LECActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		SQLiteHelper helper = new SQLiteHelper(getApplicationContext());
		// LanguageRepository langRepo = new LanguageRepository(helper);
		
		for (int j = 0; j < 3; j++) {
			String rand = Exercises.getRandomString(1);
			Log.d("Activity", "Generated a random character: " + rand);
		}
		
		helper.getDbCreateStatement();
		helper.getDbDropStatement();
	}
}