package fr.univ.orleans.ter.lec;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import fr.univ.orleans.ter.lec.controller.MainController;
import fr.univ.orleans.ter.lec.example.DbInteractionExample;
import fr.univ.orleans.ter.lec.model.Language;
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
		MainController mController = new MainController(repoMediator);
		
		/*
		 * Main Activity presents the available Languages
		 */
		List<Language> languages = mController.getLanguages();
		
		ImageButton imgB1 = (ImageButton) findViewById(R.id.imageLanguage01);
		imgB1.setTag(languages.get(0).getId());
		
		ImageButton imgB2 = (ImageButton) findViewById(R.id.ImageLanguage02);
		imgB2.setTag(languages.get(1).getId());
	}
	
	public void handleClick(View v) {
		Intent intent = new Intent();
		intent.setClass(this,LevelsActivity.class);
		intent.putExtra("language_id", (Long)v.getTag());
		startActivity(intent);
	}
}