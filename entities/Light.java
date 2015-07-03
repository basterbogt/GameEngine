package entities;

import org.lwjgl.util.vector.Vector3f;

public class Light {

	protected Vector3f position;
	protected Vector3f colour;
	protected Vector3f attenuation = new Vector3f(1, 0, 0);
	
	private final int max = 10000;
	private final int stepSize = 10;
	private boolean daytime = true;
	
	private float nightTimeMultiplier = 0.1f;
	private int nightTime;

	private float dayColour = 1.0f;
	private float nightColour = 0.0f;
	private float ColourFadeStep = 0.002f;

	public Light(){
		super();
		resetLight();
	}
	
	public Light(Vector3f position, Vector3f colour) {
		super();
		this.position = position;
		this.colour = colour;
	}

	public Light(Vector3f position, Vector3f colour, Vector3f attenuation) {
		super();
		this.position = position;
		this.colour = colour;
		this.attenuation = attenuation;
	}
	
	public void update() {

		if (daytime) {
			DayTime();
		} else {
			NightTime();
		}
	}

	private void DayTime() {
		if (position.x < 0) {
			this.position.y += stepSize;
			fadeToDay();
		} else if (position.x > 0) {
			this.position.y -= stepSize;
			if (position.x > ((max / stepSize) - ((dayColour - nightColour) / ColourFadeStep)) * stepSize) {
				fadeToNight();
			}
		}
		this.position.x += stepSize;
		if (position.x > max) {
			resetLight();
			setDaytime();
		}
//		System.out.println("Y = " + position.y + " X = " + position.x);
	}

	private void NightTime() {
		nightTime -= stepSize;
		if (nightTime <= 0) {
			setDaytime();
		}
	}

	private void setDaytime() {
		if (daytime) {
			resetNightTimeDuration();
			daytime = false;
		} else {
			daytime = true;
		}
	}

	private void fadeToDay() {
		if (colour.x < dayColour) {
			ChangeColour(ColourFadeStep);
		}

	}

	private void fadeToNight() {
		if (colour.x > nightColour) {
			ChangeColour(-ColourFadeStep);
		}
	}
	
	private void resetNightTimeDuration(){
		nightTime = (int) (max * nightTimeMultiplier);
	}
	private void resetLight(){
		position = new Vector3f(-max, 0, 0);
		colour = new Vector3f(nightColour,nightColour,nightColour);
	}

	private void ChangeColour(float amount) {
		colour.x += amount;
		colour.y += amount;
		colour.z += amount;
//		System.out.println("Colour: " + colour.x);
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Vector3f getColour() {
		return colour;
	}

	public void setColour(Vector3f colour) {
		this.colour = colour;
	}
	
	public Vector3f getAttenuation(){
		return this.attenuation;
	}

	public void setAttenuation(Vector3f attenuation) {
		this.attenuation = attenuation;
	}

}
