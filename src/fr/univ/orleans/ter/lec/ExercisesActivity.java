package fr.univ.orleans.ter.lec;

import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;
import fr.univ.orleans.ter.lec.component.VerticalProgressBar;
import fr.univ.orleans.ter.lec.controller.ExercisesController;
import fr.univ.orleans.ter.lec.model.Tag;

public class ExercisesActivity extends Activity implements OnInitListener {

	private Button butQuestion;
	private Button butChoice1;
	private Button butChoice2;
	private Button butChoice3;
	private Button butChoice4;
	private ImageButton help;
	private ImageButton home;
	
	private TextToSpeech mTts;

	private final String choiceTag = "CHOICE";
	private final String questionTag = "QUESTION";

	private final int defaultBackground = Color.BLACK;

	private ExercisesController mController;

	private String EXERCISE_OK;
	private String EXERCISE_NOK;
	private final String HOME_TAG = "HOME";
	private final String HELP_TAG = "HELP";

	/*
	 * By how much the progress increases for 1 exercise.
	 */
	private Integer PROGRESS_INCREASE;

	private VerticalProgressBar mProgress;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Set the layout for this activity
		setContentView(R.layout.exercises);

		Long levelId = getIntent().getLongExtra("level_id", 0L);

		mController = new ExercisesController();
		mController.setLevelId(levelId);

		mProgress = (VerticalProgressBar) findViewById(R.id.verticalRatingBar1);
		mProgress.setProgress(mController.getProgress());

		mTts = new TextToSpeech(this, this);
		
		this.initButtons();
		this.initMessages();
		this.setUpExercise(true);
		
	}

	private void initMessages() {
		List<Tag> tags = mController.getTags();
		for (Tag tag : tags) {
			if (tag.getTarget().equals("EXERCISE_OK")) {
				this.EXERCISE_OK = tag.getContent();
			} else if (tag.getTarget().equals("EXERCISE_NOK")) {
				this.EXERCISE_NOK = tag.getContent();
			}
		}
		this.PROGRESS_INCREASE = mController.getProgressByExercise();
	}

	private void setUpExercise(Boolean force) {
		this.resetButtons();
		String question = mController.getQuestion();

		if (mController.JUST_FINISHED) {
			Log.d("ExercisesActivity", "Passing to the next level.");
			this.toNextLevel();
		}

		List<String> choices = mController.getChoices();

		this.butQuestion.setText(question);
		this.butChoice1.setText(choices.get(0));
		this.butChoice2.setText(choices.get(1));
		this.butChoice3.setText(choices.get(2));
		this.butChoice4.setText(choices.get(3));

		if (!force) {
			mProgress.setProgress(mProgress.getProgress()
					+ this.PROGRESS_INCREASE);
		}
	}

	/*
	 * Finished all the exercises. Navigate to the previous Activity.
	 */
	private void toNextLevel() {
		mController.setCompletedLevel();
		
		Intent resultIntent = new Intent();
		resultIntent.putExtra(LevelsListActivity.finishedLevelExtraName,
				mController.getLevelName());
		setResult(Activity.RESULT_OK, resultIntent);
		finish();
	}

	public void handleClick(View v) {

		if (v instanceof Button) {
			CharSequence butText = ((Button) v).getText();
			// Verify if this was a Choice button
			if (v.getTag().equals(this.choiceTag)) {
				
				mTts.speak((String) butText,
						TextToSpeech.QUEUE_FLUSH, null);
				
				Boolean result = mController.getResultForChoice(butText);
				if (result == true) {
					mController.setCompleted(butText);
					mController.nextExercise();
					this.exerciseTransition();
					this.setUpExercise(false);
					this.speakQuestion();
				} else {
					// Wrong choice
					this.wrongChoiceTransition();
					v.setAlpha(0.3F);
					v.setBackgroundColor(Color.RED);
				}
			}
		} else if (v instanceof ImageButton) {
			
			Intent intent = new Intent();
			intent.putExtra("language_id", mController.getLanguageId());
			
			if (v.getTag().equals(HELP_TAG)) {
				intent.setClass(this, HelpActivity.class);
				startActivity(intent);

			} else if (v.getTag().equals(HOME_TAG)) {
				intent.setClass(this, MainActivity.class);
				// When Home button clicked - automatically clear the stack and get back to the Main
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		}
	}

	private void exerciseTransition() {
		Toast toast = Toast.makeText(this, this.EXERCISE_OK, 2000);
		toast.setGravity(Gravity.TOP, -30, 50);
		toast.show();
	}

	private void wrongChoiceTransition() {
		Toast toast = Toast.makeText(this, this.EXERCISE_NOK, 2000);
		toast.setGravity(Gravity.TOP, -30, 50);
		toast.show();
	}

	private void initButtons() {
		butQuestion = (Button) findViewById(R.id.buttonQuestion);
		butChoice1 = (Button) findViewById(R.id.buttonChoice1);
		butChoice2 = (Button) findViewById(R.id.buttonChoice2);
		butChoice3 = (Button) findViewById(R.id.buttonChoice3);
		butChoice4 = (Button) findViewById(R.id.buttonChoice4);
		help = (ImageButton) findViewById(R.id.help);
		home = (ImageButton) findViewById(R.id.home);
		home.setTag(HOME_TAG);
		help.setTag(HELP_TAG);

		butQuestion.setTag(this.questionTag);
		butChoice1.setTag(this.choiceTag);
		butChoice2.setTag(this.choiceTag);
		butChoice3.setTag(this.choiceTag);
		butChoice4.setTag(this.choiceTag);

		butChoice1.setBackgroundColor(defaultBackground);
		butChoice2.setBackgroundColor(defaultBackground);
		butChoice3.setBackgroundColor(defaultBackground);
		butChoice4.setBackgroundColor(defaultBackground);
	}

	private void resetButtons() {
		if (butChoice1.getAlpha() != 1F) {
			butChoice1.setAlpha(1F);
			butChoice1.setBackgroundColor(defaultBackground);
		}

		if (butChoice2.getAlpha() != 1F) {
			butChoice2.setAlpha(1F);
			butChoice2.setBackgroundColor(defaultBackground);
		}

		if (butChoice3.getAlpha() != 1F) {
			butChoice3.setAlpha(1F);
			butChoice3.setBackgroundColor(defaultBackground);
		}

		if (butChoice4.getAlpha() != 1F) {
			butChoice4.setAlpha(1F);
			butChoice4.setBackgroundColor(defaultBackground);
		}
	}

	public void onInit(int status) {
		if (status != TextToSpeech.SUCCESS) {
			Log.e("ExercisesActivity", "Could not initialize TextToSpeech!");
		} else {
			this.setLanguage(mController.getLanguageAsLocale());
		}
		
		Locale[] all = Locale.getAvailableLocales();
		for (Locale locale : all) {
			int t = mTts.isLanguageAvailable(locale);
			if ( t > 0 )
				Log.d("LOCAL PRESENCE", "For " + locale.getLanguage() +" country: " + locale.getDisplayCountry() + " status: " + t);
		}
	}

	private void setLanguage(Locale x) {
		int result = mTts.setLanguage(x);
		
		if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
			Log.e("LevelsListActivity", "Language is not available for TTS.");
		} else {
			this.speakQuestion();
		}
	}
	
	private void speakQuestion() {
		// No other way to do this.. wait() in UI thread
		try {
			synchronized (this) {
				wait(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mTts.speak((String) this.butQuestion.getText(),
				TextToSpeech.QUEUE_FLUSH, null);
	}

	@Override
    protected void onDestroy()
    {
        if (mTts != null)
        {
            mTts.shutdown();
        }
        super.onDestroy();
    }
}
