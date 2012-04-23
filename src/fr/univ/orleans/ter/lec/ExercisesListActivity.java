package fr.univ.orleans.ter.lec;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import fr.univ.orleans.ter.lec.controller.ExercisesListController;
import fr.univ.orleans.ter.lec.model.Exercise;
import fr.univ.orleans.ter.lec.model.Level;

public class ExercisesListActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Set the layout for this activity
		setContentView(R.layout.exercises_list);

		ExercisesListController controller = new ExercisesListController();

		Long levelId = getIntent().getLongExtra("level_id", 0L);

		controller.setLevelId(levelId);

		Level l = controller.getLevel();
		
		this.setUpView(l);
	}

	private void setUpView(Level level) {
		
		ListView listView = (ListView) findViewById(R.id.exercisesList);
		
		List<Exercise> exercises = level.getExercises();

		Integer i = 0;
		String[] values = new String[exercises.size()];
		for (Exercise ex : exercises) {
			values[i++] = ex.toStringForListEntry();
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, values);
		
			// Assign adapter to ListView
			listView.setAdapter(adapter);
	}
}
