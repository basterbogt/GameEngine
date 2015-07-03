package entities;

import input.UserInputManager;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera2 {
	private Vector3f position = new Vector3f(0, 0, 0);
	private float pitch;
	private float yaw;

	private float roll;
	float dx = 0.0f;
	float dy = 0.0f;
	float dt = 0.0f;
	float lastTime = 0.0f;
	float time = 0.0f;

	float mouseSensitivity = 0.2f;
	float movementSpeedDefault = 20.0f;
	float movementSpeedSprint = 40.0f;
	float movementSpeed = movementSpeedDefault;

	boolean jumping = false;
	float jumpingValueDefault = 2.0f;
	float jumpingValue = jumpingValueDefault;
	
	boolean flying = false;
	float flyingValueDefault = 200.0f;

	public Camera2() {
		
	}

	public Camera2(float x, float y, float z, float yaw, float pitch) {
		position.x = x;
		position.y = y;
		position.z = z;
		this.yaw = yaw;
		this.pitch = pitch;
	}

	public void move() {
		time = Sys.getTime();
		dt = (time - lastTime) / 1000.0f;
		lastTime = time;
		dx = Mouse.getDX();
		dy = Mouse.getDY();

		// boolean leftButtonDown = Mouse.isButtonDown(0); // is left mouse
		// button down.
		// boolean rightButtonDown = Mouse.isButtonDown(1); // is right mouse
		// button down.
		if (Mouse.isButtonDown(1)) {
			Mouse.setGrabbed(true);
			yaw += (dx * mouseSensitivity);
			pitch -= (dy * mouseSensitivity);
		} else {
			Mouse.setGrabbed(false);
		}

		if (pitch > 90) {
			pitch = 90;
		}

		if (pitch < -90) {
			pitch = -90;
		}

		if (yaw < 0) {
			yaw += 360;
		}
		if (yaw > 360) {
			yaw -= 360;
		}
		if(flying){
			movementSpeed = flyingValueDefault;
		}else if (UserInputManager.pressedSprint()) {
			movementSpeed = movementSpeedSprint;
		}
		// FORWARD
		if (UserInputManager.pressedMoveUp() || (Mouse.isButtonDown(0) && Mouse.isButtonDown(1))) {
			if (yaw == 0 | yaw > 0 && 90 > yaw) {
				float angle = yaw - 0;
				float percent = 100 / 90 * angle;
				position.x += (movementSpeed * dt / 100 * percent);
				percent = 100 - percent;
				position.z -= (movementSpeed * dt / 100 * percent);
			}

			if (yaw == 90 | yaw > 90 && 180 > yaw) {
				float angle = yaw - 90;
				float percent = 100 / 90 * angle;
				position.z += (movementSpeed * dt / 100 * percent);
				percent = 100 - percent;
				position.x += (movementSpeed * dt / 100 * percent);
			}

			if (yaw == 180 | yaw > 180 && 270 > yaw) {
				float angle = yaw - 180;
				float percent = 100 / 90 * angle;
				position.x -= (movementSpeed * dt / 100 * percent);
				percent = 100 - percent;
				position.z += (movementSpeed * dt / 100 * percent);
			}

			if (yaw == 270 | yaw > 270 && 360 >= yaw) {
				float angle = yaw - 270;
				float percent = 100 / 90 * angle;
				position.z -= (movementSpeed * dt / 100 * percent);
				percent = 100 - percent;
				position.x -= (movementSpeed * dt / 100 * percent);
			}
		}

		// BACKWARD
		if (UserInputManager.pressedMoveDown()) {
			if (yaw == 0 | yaw > 0 && 90 > yaw) {
				float angle = yaw - 0;
				float percent = 100 / 90 * angle;
				position.x -= (movementSpeed * dt / 100 * percent);
				percent = 100 - percent;
				position.z += (movementSpeed * dt / 100 * percent);
			}
			if (yaw == 90 | yaw > 90 && 180 > yaw) {
				float angle = yaw - 90;
				float percent = 100 / 90 * angle;
				position.z -= (movementSpeed * dt / 100 * percent);
				percent = 100 - percent;
				position.x -= (movementSpeed * dt / 100 * percent);
			}

			if (yaw == 180 | yaw > 180 && 270 > yaw) {
				float angle = yaw - 180;
				float percent = 100 / 90 * angle;
				position.x += (movementSpeed * dt / 100 * percent);
				percent = 100 - percent;
				position.z -= (movementSpeed * dt / 100 * percent);
			}
			if (yaw == 270 | yaw > 270 && 360 >= yaw) {
				float angle = yaw - 270;
				float percent = 100 / 90 * angle;
				position.z += (movementSpeed * dt / 100 * percent);
				percent = 100 - percent;
				position.x += (movementSpeed * dt / 100 * percent);
			}
		}

		// LEFT
		if (UserInputManager.pressedMoveLeft()) {
			if (yaw == 0 | yaw > 0 && 90 > yaw) {
				float angle = yaw - 0;
				float percent = 100 / 90 * angle;
				position.z -= (movementSpeed * dt / 100 * percent);
				percent = 100 - percent;
				position.x -= (movementSpeed * dt / 100 * percent);
			}
			if (yaw == 90 | yaw > 90 && 180 > yaw) {
				float angle = yaw - 90;
				float percent = 100 / 90 * angle;
				position.x += (movementSpeed * dt / 100 * percent);
				percent = 100 - percent;
				position.z -= (movementSpeed * dt / 100 * percent);
			}
			if (yaw == 180 | yaw > 180 && 270 > yaw) {
				float angle = yaw - 180;
				float percent = 100 / 90 * angle;
				position.z += (movementSpeed * dt / 100 * percent);
				percent = 100 - percent;
				position.x += (movementSpeed * dt / 100 * percent);
			}
			if (yaw == 270 | yaw > 270 && 360 >= yaw) {
				float angle = yaw - 270;
				float percent = 100 / 90 * angle;
				position.x -= (movementSpeed * dt / 100 * percent);
				percent = 100 - percent;
				position.z += (movementSpeed * dt / 100 * percent);
			}
		}

		// RIGHT
		if (UserInputManager.pressedMoveRight()) {
			if (yaw == 0 | yaw > 0 && 90 > yaw) {
				float angle = yaw - 0;
				float percent = 100 / 90 * angle;
				position.z += (movementSpeed * dt / 100 * percent);
				percent = 100 - percent;
				position.x += (movementSpeed * dt / 100 * percent);
			}
			if (yaw == 90 | yaw > 90 && 180 > yaw) {
				float angle = yaw - 90;
				float percent = 100 / 90 * angle;
				position.x -= (movementSpeed * dt / 100 * percent);
				percent = 100 - percent;
				position.z += (movementSpeed * dt / 100 * percent);
			}
			if (yaw == 180 | yaw > 180 && 270 > yaw) {
				float angle = yaw - 180;
				float percent = 100 / 90 * angle;
				position.z -= (movementSpeed * dt / 100 * percent);
				percent = 100 - percent;
				position.x -= (movementSpeed * dt / 100 * percent);
			}
			if (yaw == 270 | yaw > 270 && 360 >= yaw) {
				float angle = yaw - 270;
				float percent = 100 / 90 * angle;
				position.x += (movementSpeed * dt / 100 * percent);
				percent = 100 - percent;
				position.z -= (movementSpeed * dt / 100 * percent);
			}
		}


		if(flying || UserInputManager.pressedSprint()){
			movementSpeed = movementSpeedDefault;
		}

		if (UserInputManager.pressedJump() && jumping == false) {
			jumping = true;
		}
		if (jumping) {
			position.y += jumpingValue * (movementSpeed * dt);
			jumpingValue -= 0.1f;
			if (isGrounded()) {
				jumping = false;
				jumpingValue = jumpingValueDefault;
			}
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
			position.y -= (150.0f * dt);
			 isGrounded();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
			position.y += (150.0f * dt);
			flying = true;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_F1)) {
			System.out.println(" X: " + position.x + " Z: " + position.z + " Y: " + position.y + " Yaw: " + yaw + " Pitch: " + pitch);
		}
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public void setRoll(float roll) {
		this.roll = roll;
	}

	private boolean isGrounded() {
		if (position.y <= 4) {
			position.y = 4;
			flying = false;
			return true;
		}
		return false;
	}
}
