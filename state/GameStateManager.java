package state;

import input.UserInputManager;
import renderEngine.DisplayManager;

public class GameStateManager {

	private GameState currentState;

	public GameStateManager() {
//		SetGameState(GameStateName.MainMenu);
		SetGameState(GameStateName.LevelStateManager);
	}

	public void update(float dt) {

		if (UserInputManager.pressedOnceScreenshot()) {
			DisplayManager.ScreenShot();
		}

		currentState.update(dt);
	}

	public void SetGameState(GameStateName name) {
		switch (name) {
		case LevelStateManager:
			if (!(currentState instanceof LevelStateManager)) {
				currentState = new LevelStateManager(this);
			}
			break;
		case MainMenu:
			if (!(currentState instanceof MainMenu)) {
				currentState = new MainMenu(this);
			}
			break;
		default:
			// Exception
			break;
		}
	}

	public void cleanUp() {
		currentState.cleanUp();
	}

}
