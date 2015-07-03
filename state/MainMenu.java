package state;

import input.UserInputManager;


public class MainMenu extends GameState{

	public MainMenu(GameStateManager gsm){
		super(gsm);
		init();
	}
	
	private void init(){

	}
	
	@Override
	public void update(float dt) {
		
		if (UserInputManager.pressedOnceInteract()) {
			_gsm.SetGameState(GameStateName.LevelStateManager);
		}
		
	}

	@Override
	public void cleanUp(){
		
	}
	
}
