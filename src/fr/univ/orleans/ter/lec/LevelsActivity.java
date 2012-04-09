package fr.univ.orleans.ter.lec;

import java.util.ArrayList;
import java.util.List;

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

public class LevelsActivity extends Activity {
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
     // Set the layout for this activity
        setContentView(R.layout.levels);
        
        Long langId = getIntent().getLongExtra("language_id", 0L);
        
        /*
         * Retrieve the language for which we present the levels
         */
        Language l = (Language) MainController.repoMediator.getRepositoryByTableName("languages").getMemberById(langId);
        TextView t = (TextView) findViewById(R.id.textViewLanguageName);
        t.setText(l.getName());
        
        List<Level> levels = l.getLevels();
        List<Button> buttons = this.getLevelButtons();
        
        Integer butCount = 0;
        for (Level level : levels) {
        	buttons.get(butCount).setText(level.getName());
        	butCount ++;
		}
        for(Integer i = butCount; i<buttons.size();i++){
        	buttons.get(i).setText("DISABLED");
        }
    }
    
	public void handleClick(View v) {

	}
	
	public List<Button> getLevelButtons() {
		List<Button> buttons = new ArrayList<Button>();
		
		buttons.add((Button) findViewById(R.id.buttonLevel1));
		buttons.add((Button) findViewById(R.id.buttonLevel2));
		
		return buttons;
	}
}
