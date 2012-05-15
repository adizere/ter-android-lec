package fr.univ.orleans.ter.lec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import fr.univ.orleans.ter.lec.controller.LevelsController;
import fr.univ.orleans.ter.lec.controller.MainController;
import fr.univ.orleans.ter.lec.model.Language;
import fr.univ.orleans.ter.lec.model.Level;
import fr.univ.orleans.ter.lec.model.Tag;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LevelsListActivity extends Activity implements OnInitListener {
	
	static Integer lastFinishedLevelId = 1337;
	public static final String finishedLevelExtraName = "LEVEL_FINISHED";
	public static final String doneResourceName = "DONE";
	public static final String inProgressResourceName = "IN_PROGRESS";
	public static final String lockedResourceName = "LOCKED";
	
	private LevelsController mController;
	private TextToSpeech mTts;
	
	private String FINISHED_TEXT;
	private String CONGRATZ_TEXT;
	
	private final HashMap<String, Integer> LEVELS_STATUS = getLevelsSrcMap();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.levels_list);

		mController = new LevelsController();
		mTts = new TextToSpeech(this, this);

		Long langId = getIntent().getLongExtra("language_id", 0L);
		Long methodId = getIntent().getLongExtra("method_id", 0L);

		mController.setLanguageId(langId);
		mController.setMethodId(methodId);

		this.initMessages();
		this.setUpView(mController.getLanguage(), methodId);
	}


	private void initMessages() {
		List<Tag> tags = mController.getTags();
		for (Tag tag : tags) {
			if( tag.getTarget().equals("LEVEL_FINISHED")) {
				this.FINISHED_TEXT = tag.getContent();
			} else if ( tag.getTarget().equals("CONGRATZ")) {
				this.CONGRATZ_TEXT = tag.getContent();
			}
		}
	}

	private void setUpView(Language l, Long methodId) {
		// Set the name of the language on top of the view
		TextView t = (TextView) findViewById(R.id.textViewLanguageName);
		t.setText(l.getName());

		// Now add the levels
		List<Level> levels = l.getLevelsForMethod(methodId);
		List<ImageButton> levelButtons = this.getLevelButtons();
		List<ImageView> levelStatusImage = this.getLevelStatusImages();

		Integer butCount = 0;
		Boolean locked = false;
		for (Level level : levels) {
			if (butCount >= levelButtons.size()) {
				Log.e("LevelsActivity",
						"Not enough buttons to display all the levels.");
				break;
			}
			ImageButton currentButton = levelButtons.get(butCount);
			currentButton.setTag(level.getId());
			ImageView currentView = levelStatusImage.get(butCount);
			
			if (locked.equals(false) && level.getCompleted().equals(false)){
				// Everything from here on is locked
				locked = true;
				currentView.setImageResource(LEVELS_STATUS.get(this.inProgressResourceName));
				
			} else if (level.getCompleted().equals(true)){
				// this Level is already completed, so disable it 
				currentButton.setEnabled(false);
				currentView.setImageResource(LEVELS_STATUS.get(this.doneResourceName));
				
			} else if (locked == true) {
				// locked Level
				currentView.setImageResource(LEVELS_STATUS.get(this.lockedResourceName));
			}
			
			
			butCount++;
		}
		for (Integer i = butCount; i < levelButtons.size(); i++) {
			levelButtons.get(i).setVisibility(View.INVISIBLE);
		}
	}


	public void handleClick(View v) {
		Intent intent = new Intent();
		intent.setClass(this, ExercisesActivity.class);
		intent.putExtra("level_id", (Long) v.getTag());
		startActivityForResult(intent, lastFinishedLevelId);
	}

	public List<ImageButton> getLevelButtons() {
		List<ImageButton> imagebuttons = new ArrayList<ImageButton>();

		imagebuttons.add((ImageButton) findViewById(R.id.buttonlevel1));
		imagebuttons.add((ImageButton) findViewById(R.id.buttonLevel2));
		imagebuttons.add((ImageButton) findViewById(R.id.buttonLevel3));

		return imagebuttons;
	}
	
	private List<ImageView> getLevelStatusImages() {
		List<ImageView> imagebuttons = new ArrayList<ImageView>();

		imagebuttons.add((ImageView) findViewById(R.id.imgViewLevelStatus1));
		imagebuttons.add((ImageView) findViewById(R.id.imgViewLevelStatus2));
		imagebuttons.add((ImageView) findViewById(R.id.imgViewLevelStatus3));

		return imagebuttons;
	}
	
	/*
	 * Navigation from ExercisesListActivity back into this Activity.
	 */
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == lastFinishedLevelId && resultCode == Activity.RESULT_OK){
			String previousLevelName = data.getStringExtra(finishedLevelExtraName);
			Log.d("LevelsListActivity", "Finished the level with id:" + previousLevelName);
			
			if (previousLevelName.length() > 0){
				
				String text = this.FINISHED_TEXT + " " + previousLevelName + ". " + this.CONGRATZ_TEXT;
				
				Toast toast = Toast.makeText(this, text, 1000);
				toast.setGravity(Gravity.TOP, -30, 50);
				toast.show();
				
				mTts.speak(text,
						TextToSpeech.QUEUE_FLUSH, null);
				
				this.setUpView(mController.getLanguage(), mController.getMethodId());
			}
		}
	}

	public void onInit(int status) {
		if (status != TextToSpeech.SUCCESS) {
			Log.e("LevelsListActivity", "Could not initialize TextToSpeech!");
		} else {
			this.setLanguage(mController.getLanguageAsLocale());
		}
	}
	
	private void setLanguage(Locale x) {
		int result = mTts.setLanguage(x);
		
		if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
			Log.e("LevelsListActivity", "Language is not available for TTS.");
		}
	}
	
	private HashMap<String, Integer> getLevelsSrcMap() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put(this.doneResourceName, R.drawable.ok);
		map.put(this.lockedResourceName, R.drawable.lockred);
		map.put(this.inProgressResourceName, R.drawable.player_play);
		
		return map;
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
