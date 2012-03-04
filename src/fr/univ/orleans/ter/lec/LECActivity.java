package fr.univ.orleans.ter.lec;

import fr.univ.orleans.ter.lec.persistence.SQLiteHelper;
import fr.univ.orleans.ter.lec.repository.LanguageRepository;
import android.app.Activity;
import android.os.Bundle;

public class LECActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        SQLiteHelper helper = new SQLiteHelper(getApplicationContext());
//        LanguageRepository langRepo = new LanguageRepository(helper);
        
        helper.getDbCreateStatement();
        helper.getDbDropStatement();
    }
}