package entities;

import java.util.List;

import models.TexturedModel;

import org.lwjgl.util.vector.Vector3f;

public class Lamp extends Entity{

	private Light light;
	
	public Lamp(TexturedModel model, Vector3f position, float rotX,
			float rotY, float rotZ, float scale, Vector3f colour, Vector3f attenuation, List<Light> lights) {
		super(model, position, rotX, rotY, rotZ, scale);
		this.light = new Light(NewLightPosition(), colour, attenuation);
		lights.add(light);
	}
	
	@Override
	public void increasePosition(float dx, float dy, float dz){
		this.position.x += dx;
		this.position.y += dy;
		this.position.z += dz;
		AdjustLightPosition();
	}

	@Override
	public void setPosition(Vector3f position) {
		this.position = position;
		AdjustLightPosition();
	}
	

	private Vector3f NewLightPosition(){
		Vector3f newPosition = new Vector3f(this.position.x, this.position.y, this.position.z);
		newPosition.y += 12.7f;
		return newPosition;
	}
	
	private void AdjustLightPosition(){
		this.light.setPosition(NewLightPosition());
	}

	public Vector3f getLightColour() {
		return this.light.getColour();
	}

	public void setLightColour(Vector3f colour) {
		this.light.setColour(colour);
	}

	public Vector3f getAttenuation() {
		return this.light.getAttenuation();
	}

	public void setAttenuation(Vector3f attenuation) {
		this.light.setAttenuation(attenuation);
	}
	
	
	
	
	
}
