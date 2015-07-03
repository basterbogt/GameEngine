package input;

public class UserInputManager {

	private static UserInput userInput;
	private static UserInput previousFrameUserInput;

	private static int MoveUp;
	private static int MoveDown;
	private static int MoveLeft;
	private static int MoveRight;
	private static int Jump;
	private static int Sprint;

	private static int Quit;
	private static int Interact;
	private static int Screenshot;

	private static int SoundUp;
	private static int SoundDown;
	
	private static int VSync;
	private static int Fullscreen;

	public UserInputManager() {
		init();
	}
	
	private void init(){
		resetFields();
		userInput = new UserInput();
		previousFrameUserInput = userInput;
	}

	private void resetFields(){
		MoveUp = MoveDown = MoveLeft = MoveRight = Jump = Sprint = Quit = Interact = Screenshot = SoundUp = SoundDown = VSync = Fullscreen = 0;
	}
	
	public void update() {
		previousFrameUserInput = userInput;
		userInput = new UserInput(MoveUp, MoveDown, MoveLeft, MoveRight, Jump, Sprint, Quit, Interact, Screenshot, SoundUp, SoundDown, VSync, Fullscreen);
		resetFields();
	}

	/* is ButtonsPressed once?: */
	public static boolean pressedOnceQuit() {
		return ((previousFrameUserInput.getQuit() <= 0) && (userInput.getQuit() > 0)) ? true : false;
	}

	public static boolean pressedOnceInteract() {
		boolean result = ((previousFrameUserInput.getInteract() <= 0) && (userInput.getInteract() > 0)) ? true : false;
		if(result) previousFrameUserInput.setInteract();//prevents multiple interactins within one frame
		return result;
	}

	public static boolean pressedOnceScreenshot() {
		return ((previousFrameUserInput.getScreenshot() <= 0) && (userInput.getScreenshot() > 0)) ? true : false;
	}

	public static boolean pressedOnceFullscreen() {
		return ((previousFrameUserInput.getFullscreen() <= 0) && (userInput.getFullscreen() > 0)) ? true : false;
	}
	
	public static boolean pressedOnceVSync() {
		return ((previousFrameUserInput.getVSync() <= 0) && (userInput.getVSync() > 0)) ? true : false;
	}
	
	// butting being pressed:
	public static boolean pressedMoveUp() {
		return (userInput.getMoveUp() > 0) ? true : false;
	}

	public static boolean pressedMoveDown() {
		return (userInput.getMoveDown() > 0) ? true : false;
	}

	public static boolean pressedMoveLeft() {
		return (userInput.getMoveLeft() > 0) ? true : false;
	}

	public static boolean pressedMoveRight() {
		return (userInput.getMoveRight() > 0) ? true : false;
	}

	public static boolean pressedJump() {
		return (userInput.getJump() > 0) ? true : false;
	}
	public static boolean pressedSprint() {
		return (userInput.getSprint() > 0) ? true : false;
	}

	public static boolean pressedQuit() {
		return (userInput.getQuit() > 0) ? true : false;
	}

	public static boolean pressedInteract() {
		return (userInput.getInteract() > 0) ? true : false;
	}

	public static boolean pressedScreenshot() {
		return (userInput.getScreenshot() > 0) ? true : false;
	}

	public static boolean pressedSoundUp() {
		return (userInput.getSoundUp() > 0) ? true : false;
	}

	public static boolean pressedSoundDown() {
		return (userInput.getSoundDown() > 0) ? true : false;
	}

	/* if ButtonsPressed: */
	public static void MoveUp() {
		MoveUp++;
	}

	public static void MoveDown() {
		MoveDown++;
	}

	public static void MoveLeft() {
		MoveLeft++;
	}

	public static void MoveRight() {
		MoveRight++;
	}

	public static void Quit() {
		Quit++;
	}

	public static void Interact() {
		Interact++;
	}

	public static void Screenshot() {
		Screenshot++;
	}

	public static void SoundUp() {
		SoundUp++;
	}

	public static void SoundDown() {
		SoundDown++;
	}
	
	public static void VSync() {
		VSync++;
	}
	
	public static void Fullscreen() {
		Fullscreen++;
	}

	public static void Jump() {
		Jump++;
	}

	public static void Sprint() {
		Sprint++;
	}

}
