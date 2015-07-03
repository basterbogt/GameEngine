package state;

import java.util.ArrayList;
import java.util.List;

import models.RawModel;
import models.TexturedModel;
import objConverter.ModelData;
import objConverter.OBJFileLoader;

import org.lwjgl.util.vector.Vector3f;

import renderEngine.Loader;
import renderEngine.MasterRenderer;
import textures.ModelTexture;
import entities.Camera;
import entities.Light;
import entities.Player;
import entities.Sun;
import gui.GuiRenderer;
import gui.GuiTexture;

public abstract class LevelState {

	protected Camera camera;
	protected MasterRenderer renderer;
	protected Loader loader;
	protected List<Light> lights;
	protected Sun sun;
	protected Player player;
	
	protected String heightmap;
	
	protected List<GuiTexture> guis;
	protected GuiRenderer guiRenderer;
	
	
	public LevelState() {
		super();
		loader = new Loader();
		renderer = new MasterRenderer();
		lights = new ArrayList<Light>();
		sun = new Sun();
		lights.add(sun);
				
		guis  = new ArrayList<GuiTexture>();
		guiRenderer= new GuiRenderer(loader);
		


		/* Player */
		ModelData playerData = OBJFileLoader.loadOBJ("person");
		RawModel playerModel = loader.loadToVAO(playerData.getVertices(), playerData.getTextureCoords(), playerData.getNormals(), playerData.getIndices());
		TexturedModel playerTexturedModel = new TexturedModel(playerModel, new ModelTexture(loader.loadEntityTexture("person")));
		ModelTexture playerTexture = playerTexturedModel.getTexture();
		playerTexture.setShineDamper(10);
		player = new Player(playerTexturedModel, new Vector3f(1650, 0, 1500), 0, 0, 0, 0.5f);

		camera = new Camera(player);
//		camera = new Camera(1638, 4, 1610, 46, 0);
		
		
		//HUD:
		//guis.add(new GuiTexture(loader.loadGUITexture("shade"), new Vector2f(-1.0f, -1.0f), new Vector2f(3.0f, 3.0f)));
		//guis.add(new GuiTexture(loader.loadGUITexture("pikachu"), new Vector2f(0.5f, 0.5f), new Vector2f(0.25f, 0.25f)));
		
		
	}
	
//	public LevelState(Camera camera, Sun sun) {
//		super();
//		loader = new Loader();
//		renderer = new MasterRenderer();
//		this.camera = camera;
//		this.sun = sun;
//	}

	public void update(float dt) {
		sun.update();
		ProcessRenderer();
		renderer.render(lights, camera);
		guiRenderer.render(guis);
	}
	
	public abstract void ProcessRenderer();

	public void cleanUp() {
		guiRenderer.cleanUp();
		renderer.cleanUp();
		loader.cleanUp();
	}
	
	public void setLightPosition(Vector3f position){
		sun.setPosition(position);
	}
	public void setLightColour(Vector3f colour){
		sun.setColour(colour);
	}
	
	public void setCameraPosition(float x, float y, float z, float yaw, float pitch){
		camera.setPosition(new Vector3f(x, y, z));
		camera.setYaw(yaw);
		camera.setPitch(pitch);
	}

}
