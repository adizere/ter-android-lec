package fr.univ.orleans.ter.lec;

import java.util.ArrayList;
import java.util.List;

import fr.univ.orleans.ter.lec.controller.LevelsController;
import fr.univ.orleans.ter.lec.controller.MainController;
import fr.univ.orleans.ter.lec.model.Language;
import fr.univ.orleans.ter.lec.model.Level;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LevelsListActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Set the layout for this activity
		setContentView(R.layout.levels_list);

		LevelsController controller = new LevelsController();

		Long langId = getIntent().getLongExtra("language_id", 0L);
		Long methodId = getIntent().getLongExtra("method_id", 0L);

		controller.setLanguageId(langId);
		controller.setMethodId(methodId);

		this.setUpView(controller.getLanguage(), methodId);
	}

	private void setUpView(Language l, Long methodId) {
		// Set the name of the language on top of the view
		TextView t = (TextView) findViewById(R.id.textViewLanguageName);
		t.setText(l.getName());

		// Now add the levels
		List<Level> levels = l.getLevelsForMethod(methodId);
		List<Button> buttons = this.getLevelButtons();

		Integer butCount = 0;
		for (Level level : levels) {
			if (butCount >= buttons.size()) {
				Log.e("LevelsActivity",
						"Not enough buttons to display all the levels.");
				break;
			}
			Button currentButton = buttons.get(butCount);
			currentButton.setText(level.getName());
			currentButton.setTag(level.getId());

			butCount++;
		}
		for (Integer i = butCount; i < buttons.size(); i++) {
			buttons.get(i).setVisibility(View.INVISIBLE);
		}
	}

	public void handleClick(View v) {
		Intent intent = new Intent();
		intent.setClass(this, ExercisesActivity.class);
		intent.putExtra("level_id", (Long) v.getTag());
		startActivity(intent);
	}

	public List<Button> getLevelButtons() {
		List<Button> buttons = new ArrayList<Button>();

		buttons.add((Button) findViewById(R.id.buttonLevel1));
		buttons.add((Button) findViewById(R.id.buttonLevel2));

		return buttons;
	}
}
