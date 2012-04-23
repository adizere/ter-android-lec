package fr.univ.orleans.ter.lec.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.util.Log;
import fr.univ.orleans.ter.lec.model.Exercise;
import fr.univ.orleans.ter.lec.model.Language;
import fr.univ.orleans.ter.lec.model.Level;

public class ExercisesController extends BasicLECController {

	private Long levelId;
	private Level level;
	
	private Exercise exercise;

	public Long getLevelId() {
		return levelId;
	}

	public void setLevelId(Long levelId) {
		if (this.validLevelId(levelId)) {
			this.levelId = levelId;
			this.loadLevel();
			this.setSessionExercise();
		} else {
			Log.w(ExercisesController.class.toString(),
					"Invalid level ID passed.");
		}
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	private void loadLevel() {
		this.level = (Level) this.repoMediator.getRepositoryByTableName(
				"levels").getMemberById(this.levelId);
	}

	private Boolean validLevelId(Long id) {
		if (id != null && id > 0L) {
			return true;
		} else {

			return false;
		}
	}
	
	public void setSessionExercise(){
		this.exercise = this.level.getSessionExercises();
	}

	public String getQuestion() {
		return this.exercise.getQuestion();
	}

	public List<String> getChoices() {
		
		List<String> choices = this.exercise.getChoice().getChoicesList();
		List<String> ret = new ArrayList<String>();
		Iterator<String> choicesIt = choices.iterator();
		
		Boolean added = false;
		while(choicesIt.hasNext()){
			if( (! added ) && (  Math.random() > 0.5 ) ){
				ret.add(this.exercise.getChoice().getResult());
				added = true;
			}
			ret.add(choicesIt.next());
			if( (! added ) && (  Math.random() > 0.5 ) ){
				ret.add(this.exercise.getChoice().getResult());
				added = true;
			}
		}
		if ( !added ){
			ret.add(this.exercise.getChoice().getResult());
		}
		
		return ret;
	}

	public Boolean getResultForChoice(CharSequence choice) {
		if(this.exercise.getChoice().getResult().equals(choice)){
			return true;
		}
		
		return false;
	}
}
