package fr.univ.orleans.ter.lec;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import fr.univ.orleans.ter.lec.model.Language;
import fr.univ.orleans.ter.lec.persistence.SQLiteHelper;
import fr.univ.orleans.ter.lec.repository.LanguageRepository;
import fr.univ.orleans.ter.lec.utility.Exercises;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		SQLiteHelper helper = new SQLiteHelper(getApplicationContext());
		LanguageRepository langRepo = new LanguageRepository(helper);
		
		List<Object> languages;
		
//		languages = langRepo.getMembers();
//		langRepo.createLanguage("French", 1L);
//		langRepo.createLanguage("English", 2L);
//		langRepo.createLanguage("Arabic", 3L);
//		langRepo.createLanguage("Chinese", 4L);
		languages = langRepo.getMembers();
		
		
		Log.d("Activity", "Members found:" + languages.size());
		Language french = (Language) langRepo.getMemberById(1L);
		Log.d("Activity", "Retrieved language by id:" + french.toString() );
		
		for (Object object : languages) {
			Log.d("Activity", "Language found:" + object.toString() );
		}
		
		for (int j = 0; j < 3; j++) {
			String rand = Exercises.getRandomString(1);
			Log.d("Activity", "Generated a random character: " + rand);
		}
		
		helper.getDbCreateStatement();
		helper.getDbDropStatement();
	}
}