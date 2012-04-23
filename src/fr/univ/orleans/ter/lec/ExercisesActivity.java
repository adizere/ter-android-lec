package fr.univ.orleans.ter.lec;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import fr.univ.orleans.ter.lec.controller.ExercisesController;
import fr.univ.orleans.ter.lec.model.Choice;
import fr.univ.orleans.ter.lec.model.Exercise;
import fr.univ.orleans.ter.lec.model.Level;

public class ExercisesActivity extends Activity {

	private Button butQuestion;
	private Button butChoice1;
	private Button butChoice2;
	private Button butChoice3;
	private Button butChoice4;
	
	private final String choiceTag = "CHOICE";
	private final String questionTag = "CHOICE";
	
	private ExercisesController mController;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Set the layout for this activity
		setContentView(R.layout.exercises);

		mController = new ExercisesController();

		Long levelId = getIntent().getLongExtra("level_id", 0L);

		mController.setLevelId(levelId);

		Level l = mController.getLevel();

		this.initButtons();
		this.setUpExercise();
	}

	private void setUpExercise() {
		String question = mController.getQuestion();
		List<String> choices = mController.getChoices();
		
		this.butQuestion.setText(question);
		this.butChoice1.setText(choices.get(0));
		this.butChoice2.setText(choices.get(1));
		this.butChoice3.setText(choices.get(2));
		this.butChoice4.setText(choices.get(3));
	}

	public void handleClick(View v) {
		if(v.getTag().equals(this.choiceTag)){
			Boolean result = mController.getResultForChoice(((Button)v).getText());
		}
	}
	
	private void initButtons() {
		butQuestion = (Button) findViewById(R.id.buttonQuestion);
		butChoice1 = (Button) findViewById(R.id.buttonChoice1);
		butChoice2 = (Button) findViewById(R.id.buttonChoice2);
		butChoice3 = (Button) findViewById(R.id.buttonChoice3);
		butChoice4 = (Button) findViewById(R.id.buttonChoice4);
		
		butQuestion.setTag(this.questionTag);
		butChoice1.setTag(this.choiceTag);
		butChoice2.setTag(this.choiceTag);
		butChoice3.setTag(this.choiceTag);
		butChoice4.setTag(this.choiceTag);
	}
}
