package fr.univ.orleans.ter.lec;

import java.util.List;
import java.util.Locale;

import fr.univ.orleans.ter.lec.component.VerticalProgressBar;
import fr.univ.orleans.ter.lec.controller.ExercisesController;
import fr.univ.orleans.ter.lec.controller.HelpController;
import fr.univ.orleans.ter.lec.model.Tag;
import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.widget.TextView;

public class HelpActivity extends Activity implements OnInitListener  {
	private String HELP_GENERIC;
	private HelpController mController;
	private TextView helpText;
	private TextToSpeech mTts;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Long languageId = getIntent().getLongExtra("language_id", 0L);
		
		mController = new HelpController();
		mController.setLanguageId(languageId);
		this.loadMessages();
		
		// Set the layout for this activity
		setContentView(R.layout.help);
		mTts = new TextToSpeech(this, this);

		helpText = (TextView)findViewById(R.id.help_text);
		
		helpText.setText(HELP_GENERIC);
	}

	private void loadMessages() {

		List<Tag> tags = mController.getTags();
		for (Tag tag : tags) {
			if (tag.getTarget().equals("HELP_GENERIC")) {
				this.HELP_GENERIC = tag.getContent();
			} else if (tag.getTarget().equals("HELP_GENERIC")) {
				this.HELP_GENERIC = tag.getContent();
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
		} else {
			this.speakHelp();
		}
	}

	private void speakHelp() {
		mTts.speak(this.HELP_GENERIC,
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
