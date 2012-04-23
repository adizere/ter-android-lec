package fr.univ.orleans.ter.lec;

import java.util.List;

import fr.univ.orleans.ter.lec.controller.LevelsController;
import fr.univ.orleans.ter.lec.controller.MethodsController;
import fr.univ.orleans.ter.lec.model.Exercise;
import fr.univ.orleans.ter.lec.model.Language;
import fr.univ.orleans.ter.lec.model.Method;
import android.R.integer;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class MethodsListActivity extends Activity  {
	
	private Long languageId;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Set the layout for this activity
		setContentView(R.layout.methods_list);

		MethodsController controller = new MethodsController();

		Long langId = getIntent().getLongExtra("language_id", 0L);
		this.languageId = langId;

		controller.setLanguageId(langId);

		this.setUpView(controller.getLanguage());

	}

	private void setUpView(Language language) {
		List<Method> methods = language.getMethods();
		
		ImageButton imgB1 = (ImageButton) findViewById(R.id.imgButtonCompter);
		imgB1.setTag(methods.get(0).getId());
		
		ImageButton imgB2 = (ImageButton) findViewById(R.id.imgButtonLire);
		imgB2.setTag(methods.get(1).getId());
		
		ImageButton imgB3 = (ImageButton) findViewById(R.id.imgButtonEcrire);
		imgB3.setTag(methods.get(2).getId());
	}
	
	public void handleClick(View v) {
		Intent intent = new Intent();
		intent.setClass(this,LevelsListActivity.class);
		
		intent.putExtra("language_id", this.languageId);
		intent.putExtra("method_id", (Long)v.getTag());
		
		startActivity(intent);
	}


}
