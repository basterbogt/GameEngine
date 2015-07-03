package input;

import org.lwjgl.input.Keyboard;

public class UserInputKeyboard {

	public void update() {

		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			UserInputManager.Quit();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_F12)) {
			UserInputManager.Screenshot();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_ADD)) {
			UserInputManager.SoundUp();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_SUBTRACT)) {
			UserInputManager.SoundDown();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_F2)) {
			UserInputManager.Interact();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
			UserInputManager.Fullscreen();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_V)) {
			UserInputManager.VSync();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			UserInputManager.MoveUp();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			UserInputManager.MoveLeft();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			UserInputManager.MoveDown();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			UserInputManager.MoveRight();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			UserInputManager.Jump();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			UserInputManager.Sprint();
		}
	}

}
