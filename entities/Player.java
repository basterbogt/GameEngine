package entities;

import input.UserInputManager;
import models.TexturedModel;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.DisplayManager;
import terrains.Terrain;

public class Player extends Entity{

	private static final float RUN_SPEED = 20;
	private static final float TURN_SPEED = 160;
	private static final float GRAVITY = -50;
	private static final float JUMP_POWER = 30;
		
	private float currentSpeed = 0;
	private float currentTurnSpeed = 0;
	private float upwardsSpeed = 0;
	private float strafeSpeed = 0;
	
	private boolean isInAir = false;
	
	public Player(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(model, position, rotX, rotY, rotZ, scale);

	}

	public void move(Terrain terrain){
		checkInputs();
		
		
		super.increaseRotation(0, getCurrentTurnSpeed(), 0);
		
		float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
		float strafeDistance = strafeSpeed * DisplayManager.getFrameTimeSeconds();
		
		float dx = 0;
		float dz = 0;
		
		if(distance != 0 && strafeDistance == 0){ //If just walking forward/backwards:
			dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
			dz = (float) (distance * Math.cos(Math.toRadians(super.getRotY())));
		}else if(distance == 0 && strafeDistance != 0){ //If just strafing left/right
			dx = (float) (strafeDistance * Math.sin(Math.toRadians(super.getRotY() + 90)));
			dz = (float) (strafeDistance * Math.cos(Math.toRadians(super.getRotY() + 90)));
		}else if(distance != 0 && strafeDistance != 0){ // If combining walk and strafe. Using pythagoras to calculate actual distance...
			float distanceDirection = (distance > 0)? 1: -1;
			float strafeDirection = (strafeDistance > 0)? 1: -1;
			float crossDistance = ((float) Math.sqrt(Math.pow(distance, 2) + Math.pow(strafeDistance, 2))) / 2;
			dx += (float) (crossDistance * distanceDirection * Math.sin(Math.toRadians(super.getRotY())));
			dz += (float) (crossDistance * distanceDirection * Math.cos(Math.toRadians(super.getRotY())));
			dx += (float) (crossDistance * strafeDirection * Math.sin(Math.toRadians(super.getRotY() + 90)));
			dz += (float) (crossDistance * strafeDirection * Math.cos(Math.toRadians(super.getRotY() + 90)));
		}

		
		super.increasePosition(dx, 0, dz);
		upwardsSpeed += GRAVITY * DisplayManager.getFrameTimeSeconds();
		super.increasePosition(0, upwardsSpeed * DisplayManager.getFrameTimeSeconds(), 0);
		
		float terrainHeight = terrain.getHeightOfTerrain(super.getPosition().x, super.getPosition().z);
		if(super.getPosition().y < terrainHeight){
			upwardsSpeed = 0;
			isInAir = false;
			super.getPosition().y = terrainHeight;
		}
		
	}
	
	private void jump(){
		if(!isInAir){
			this.upwardsSpeed = JUMP_POWER;
			isInAir = true;
		}
	}
	
	
	private void checkInputs(){
		if(isInAir){
			return;
		}
		if(UserInputManager.pressedMoveUp() || (Mouse.isButtonDown(1) && Mouse.isButtonDown(0))){
			currentSpeed = RUN_SPEED;
		}
		else if(UserInputManager.pressedMoveDown()){
			this.currentSpeed = -RUN_SPEED;
		}
		else{
			currentSpeed = 0;
		}
		
		
		if(!Mouse.isButtonDown(1)){
			strafeSpeed = 0;
			if(UserInputManager.pressedMoveLeft()){
				currentTurnSpeed = TURN_SPEED;
			}
			else if(UserInputManager.pressedMoveRight()){
				currentTurnSpeed = -TURN_SPEED;
			}
			else{
				currentTurnSpeed = 0;
			}
		}
		else{
			currentTurnSpeed = 0;
			if(UserInputManager.pressedMoveLeft()){
				strafeSpeed = RUN_SPEED;
			}
			else if(UserInputManager.pressedMoveRight()){
				strafeSpeed = -RUN_SPEED;
			}
			else{
				strafeSpeed = 0;
			}
		}
		
		if(UserInputManager.pressedJump()){
			jump();
		}
		
		

		if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
			super.increasePosition(0, 5, 0);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
			super.increasePosition(0, -5, 0);
			if(this.getPosition().y < 0) getPosition().y = 0;
		}
		
	}
	
	
	public float getCurrentTurnSpeed(){
		return currentTurnSpeed * DisplayManager.getFrameTimeSeconds();
	}
	
	public boolean isInAir(){
		return isInAir;
	}
}
