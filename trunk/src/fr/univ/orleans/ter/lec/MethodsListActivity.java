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
import android.widget.ListView;
import android.widget.Toast;

public class MethodsListActivity extends ListActivity  {
	
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

		String[] values = new String[methods.size()];
		int i = 0;
		for (Method m : methods) {
			values[i++] = m.toString();
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, values);

		// Assign adapter to ListView
		setListAdapter(adapter);
	}
	
	protected void onListItemClick (ListView l, View v, int position, long id){
		Intent intent = new Intent();
		intent.setClass(this,LevelsListActivity.class);
		intent.putExtra("language_id", this.languageId);
		
		Long methodId = (long) (position + 1);
		intent.putExtra("method_id", methodId);
		
		startActivity(intent);
	}


}
