package fr.univ.orleans.ter.lec.controller;

import android.util.Log;
import fr.univ.orleans.ter.lec.model.Language;
import fr.univ.orleans.ter.lec.model.Level;

public class ExercisesListController extends BasicLECController {

	private Long levelId;
	private Level level;

	public Long getLevelId() {
		return levelId;
	}

	public void setLevelId(Long levelId) {
		if (this.validLevelId(levelId)) {
			this.levelId = levelId;
			this.loadLevel();
		} else {
			Log.w(ExercisesListController.class.toString(),
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
}
