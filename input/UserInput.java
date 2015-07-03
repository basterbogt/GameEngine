package input;

public class UserInput {

	private int MoveUp;
	private int MoveDown;
	private int MoveLeft;
	private int MoveRight;
	private int Jump;
	private int Sprint;

	private int Quit;
	private int Interact;
	private int Screenshot;

	private int SoundUp;
	private int SoundDown;

	private int VSync;
	private int Fullscreen;

	public UserInput() {
		MoveUp = MoveDown = MoveLeft = MoveRight = Jump = Sprint =  Quit = Interact = Screenshot = SoundUp = SoundDown = VSync = Fullscreen = 0;
	}

	public UserInput(int moveUp, int moveDown, int moveLeft, int moveRight, int jump, int sprint, int quit, int interact, int screenshot, int soundUp, int soundDown, int vSync,
			int fullscreen) {
		MoveUp = moveUp;
		MoveDown = moveDown;
		MoveLeft = moveLeft;
		MoveRight = moveRight;
		Jump = jump;
		Sprint = sprint;
		Quit = quit;
		Interact = interact;
		Screenshot = screenshot;
		SoundUp = soundUp;
		SoundDown = soundDown;
		VSync = vSync;
		Fullscreen = fullscreen;
	}

	public int getMoveUp() {
		return MoveUp;
	}

	public void setMoveUp() {
		MoveUp++;
	}

	public int getMoveDown() {
		return MoveDown;
	}

	public void setMoveDown() {
		MoveDown++;
	}

	public int getMoveLeft() {
		return MoveLeft;
	}

	public void setMoveLeft() {
		MoveLeft++;
	}

	public int getMoveRight() {
		return MoveRight;
	}

	public void setMoveRight() {
		MoveRight++;
	}

	public int getQuit() {
		return Quit;
	}

	public void setQuit() {
		Quit++;
	}

	public int getInteract() {
		return Interact;
	}

	public void setInteract() {
		Interact++;
	}

	public int getScreenshot() {
		return Screenshot;
	}

	public void setScreenshot() {
		Screenshot++;
	}

	public int getSoundUp() {
		return SoundUp;
	}

	public void setSoundUp() {
		SoundUp++;
	}

	public int getSoundDown() {
		return SoundDown;
	}

	public void setSoundDown() {
		SoundDown++;
	}

	public int getVSync() {
		return VSync;
	}

	public void setVSync() {
		VSync++;
	}

	public int getFullscreen() {
		return Fullscreen;
	}

	public void setFullscreen() {
		Fullscreen++;
	}

	public int getJump() {
		return Jump;
	}
	
	public void setJump(){
		Jump++;
	}

	public void setSprint(){
		Sprint++;
	}
	
	public int getSprint() {
		return Sprint;
	}

}
