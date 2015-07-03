package entities;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

	private Player player;

	private float distanceFromPlayer = 50;
	private float maxZoomDistance = 60;
	private float angleAroundPlayer = 0;
	private float CameraYOffset = 5;

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

	public Camera(Player player) {
		this.player = player;
	}

	public Camera(float x, float y, float z, float yaw, float pitch) {
		position.x = x;
		position.y = y;
		position.z = z;
		this.yaw = yaw;
		this.pitch = pitch;
	}

	public void move() {
	
		calculateZoom();
		calculateAngleAroundPlayer();
		calculateCameraPosition(calculateHorizontalDistance(), calculateVerticalDistance());
		calculatePitch();
		calculateJaw();

		if (Mouse.isButtonDown(1) || Mouse.isButtonDown(0)) {
			Mouse.setGrabbed(true);
		} else {
			Mouse.setGrabbed(false);
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

	private void calculateCameraPosition(float horizontalDistance, float verticalDistance) {
		float theta = player.getRotY() + angleAroundPlayer;
		float offsetX = (float) (horizontalDistance * Math.sin(Math.toRadians(theta)));
		float offsetZ = (float) (horizontalDistance * Math.cos(Math.toRadians(theta)));
		position.x = player.getPosition().x - offsetX;
		position.z = player.getPosition().z - offsetZ;
		position.y = CameraYOffset + player.getPosition().y + verticalDistance;

		if (position.y < 1) {
			position.y = 1;
		}

	}

	private float calculateHorizontalDistance() {
		return (float) (distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
	}

	private float calculateVerticalDistance() {
		return (float) (distanceFromPlayer * Math.sin(Math.toRadians(pitch)));
	}

	private void calculateZoom() {
		float zoomLevel = Mouse.getDWheel() * 0.1f;
		distanceFromPlayer -= zoomLevel;
		if (distanceFromPlayer < 0)
			distanceFromPlayer = 0;
		if (distanceFromPlayer > maxZoomDistance)
			distanceFromPlayer = maxZoomDistance;
	}

	private void calculatePitch() {
		if (Mouse.isButtonDown(1) || Mouse.isButtonDown(0)) {
			float pitchChange = Mouse.getDY() * 0.1f;
			pitch -= pitchChange;
		}
		if (pitch > 90) {
			pitch = 90;
		}
		// if (pitch < -90) {
		// pitch = -90;
		// }
		if (pitch < -80) { //Quickfix so you wont see yourself while looking WAY up!
			pitch = -80;
		}
	}

	private void calculateJaw() {
		this.yaw = 180 - (player.getRotY() + angleAroundPlayer);
		if (yaw < 0) {
			yaw += 360;
		}
		if (yaw > 360) {
			yaw -= 360;
		}
	}

	private void calculateAngleAroundPlayer() {

		angleAroundPlayer -= player.getCurrentTurnSpeed();
		
		if (Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) {
			float angleChange = Mouse.getDX() * 0.3f;
			angleAroundPlayer -= angleChange;
		}
		if (angleAroundPlayer > 360){
			angleAroundPlayer -= 360;
		}
		if (angleAroundPlayer < 0){
			angleAroundPlayer += 360;
		}
		
		if (Mouse.isButtonDown(1)){
			if(angleAroundPlayer != 0){
				if(!player.isInAir()){
					player.setRotY(player.getRotY() + angleAroundPlayer); //Force player angle like the camera angle
					angleAroundPlayer = 0; //Set this to 0 to keep the camera behind the player
				}
			}
		}
	}

}
