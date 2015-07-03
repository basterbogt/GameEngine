package engineTester;



import input.UserInputKeyboard;
import input.UserInputManager;

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;
import sound.SoundManager;
import state.GameStateManager;

public class GameLoop {

	private boolean running = true;
	private GameStateManager gsm;
	private SoundManager sm;
	private UserInputManager  uim;
	private UserInputKeyboard keyboard;
	private long lastFrame;

	int fps;
	long lastFPS;

	public void start() {
		init();
		
		while (!Display.isCloseRequested() && running) {
			uim.update();
			gsm.update(getDelta());
			updateFPS();
			DisplayManager.updateDisplay();
			UserInput();
		}
		
		cleanUp();
	}
	
	public void init(){
		getDelta();
		lastFPS = getTime(); 
		DisplayManager.createDisplay();
		
		gsm = new GameStateManager();
		sm = SoundManager.getInstance();
		uim = new UserInputManager();
		keyboard = new UserInputKeyboard();

		//Make Cursor invisible:
		/*
		try {
			Cursor emptyCursor = new Cursor(1, 1, 0, 0, 1, BufferUtils.createIntBuffer(1), null);
			Mouse.setNativeCursor(emptyCursor);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		*/
		
	}
	
	private void UserInput(){
		
		if (UserInputManager.pressedOnceQuit()) {
			running = false;
		}
		if (UserInputManager.pressedSoundUp()) {
			sm.SoundUp();
		}
		if (UserInputManager.pressedSoundDown()) {
			sm.SoundDown();
		}
		keyboard.update();
	}
	
	public void cleanUp(){
		gsm.cleanUp();
		sm.cleanUp();
		DisplayManager.closeDisplay();
	}

	public float getDelta() {
		long time = getTime();
		float delta = ((float) (time - lastFrame))/1000000000;
		lastFrame = time;
		return delta;
	}

	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			System.out.println("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

}
