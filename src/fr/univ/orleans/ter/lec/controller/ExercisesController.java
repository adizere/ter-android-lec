package fr.univ.orleans.ter.lec.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import android.util.Log;
import fr.univ.orleans.ter.lec.model.Exercise;
import fr.univ.orleans.ter.lec.model.Level;
import fr.univ.orleans.ter.lec.model.Tag;
import fr.univ.orleans.ter.lec.repository.ExercisesRepository;
import fr.univ.orleans.ter.lec.repository.LevelsRepository;
import fr.univ.orleans.ter.lec.utility.LocaleDefs;

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
			this.nextExercise();
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
		this.level = (Level) ExercisesController.repoMediator
				.getRepositoryByTableName("levels").getMemberById(this.levelId);
		
		Log.d("ExercisesController", "Level loaded with completed=" + this.level.getCompleted());
	}

	private Boolean validLevelId(Long id) {
		if (id != null && id > 0L) {
			return true;
		} else {
			return false;
		}
	}

	public void nextExercise() {
		Long currentId = null;
		if (this.exercise == null ){
			currentId = 0L;
		} else {
			currentId = this.exercise.getId();
		}

		this.exercise = this.level.getSessionExercises(currentId);
		
		if (this.exercise == null) {
			Log.w("ExercisesController", "Could not find any exercise.");
			return;
		}

		if (this.exercise.getCompleted() == true) {
			Log.d("ExercisesController", "All exercises were completed.");
		}
	}

	public String getQuestion() {
		if (this.exercise == null) {
			return "";
		}

		return this.exercise.getQuestion();
	}

	public List<String> getChoices() {

		if (this.exercise == null) {
			return new ArrayList<String>(Arrays.asList("", "", "", ""));
		}

		List<String> choices = this.exercise.getChoice().getChoicesList();
		List<String> ret = new ArrayList<String>();
		Iterator<String> choicesIt = choices.iterator();

		Boolean added = false;
		while (choicesIt.hasNext()) {
			if ((!added) && (Math.random() > 0.7)) {
				ret.add(this.exercise.getChoice().getResult());
				added = true;
			}
			ret.add(choicesIt.next());
			if ((!added) && (Math.random() > 0.65)) {
				ret.add(this.exercise.getChoice().getResult());
				added = true;
			}
		}
		if (!added) {
			ret.add(this.exercise.getChoice().getResult());
		}

		return ret;
	}

	public Boolean getResultForChoice(CharSequence choice) {
		if (this.exercise.getChoice().getResult().equals(choice)) {
			return true;
		}

		return false;
	}

	public void setCompleted(CharSequence butText) {
		ExercisesRepository exRepo = (ExercisesRepository) ExercisesController.repoMediator
				.getRepositoryByTableName("exercises");
		exRepo.setCompleted(this.exercise.getId());
	}

	public List<Tag> getTags() {
		return this.level.getLanguage().getTags();
	}

	public String getLevelName() {
		return this.level.getName();
	}

	public int getProgress() {
		if (this.level == null) {
			return 0;
		}
		int progressByExercise = this.getProgressByExercise();
		int progressSoFar = 0;
		Iterator<Exercise> itExercises =this.level.getExercises().iterator();
		while(itExercises.hasNext()){
			if (itExercises.next().getId() != this.exercise.getId()){
				progressSoFar += progressByExercise;
			} else {
				break;
			}
		}
		
		Log.d("ExercisesController","Progress calculation: " + progressSoFar + " out of: " + this.level.getExercises().size());
		
		return progressSoFar;
	}

	public int getProgressByExercise() {
		if (this.level == null) {
			return 0;
		}
		int total = this.level.getExercises().size();
		if (total == 0)
			return 100;
		int res = 100/total;
		
		return res;
	}

	public Long getLanguageId() {
		return this.level.getLanguageId();
	}

	public Locale getLanguageAsLocale() {
		return LocaleDefs.getLocaleForLanguageName(this.level.getLanguage().getName());
	}

	public void setCompletedLevel() {
		LevelsRepository lRepo = (LevelsRepository) ExercisesController.repoMediator.getRepositoryByTableName("levels");
		lRepo.setCompleted(this.levelId);
	}
}
