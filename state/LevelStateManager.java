package state;

import input.UserInputManager;
import state.level.Hell;
import state.level.Overworld;

public class LevelStateManager extends GameState {

	private LevelState _currentLevel;

	public LevelStateManager(GameStateManager gsm) {
		super(gsm);
		SetLevel(LevelStateName.Overworld);
	}

	public void update(float dt) {

		/* Get Use Input */
		if (UserInputManager.pressedOnceInteract()) {
			if ((_currentLevel instanceof Overworld)) {
				SetLevel(LevelStateName.Hell);
			} else if ((_currentLevel instanceof Hell)) {
				SetLevel(LevelStateName.Overworld);
			}
		}

		_currentLevel.update(dt);
	}

	public void SetLevel(LevelStateName name) {
		switch (name) {
		case Overworld:
			if (!(_currentLevel instanceof Overworld)) {
				_currentLevel = new Overworld();
			}
			break;
		case Hell:
			if (!(_currentLevel instanceof Hell)) {
				_currentLevel = new Hell();
			}
			break;
		default:
			// Exception
			break;
		}
	}

	@Override
	public void cleanUp() {
		super.cleanUp();
		_currentLevel.cleanUp();
	}

}
