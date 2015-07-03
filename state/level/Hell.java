package state.level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.RawModel;
import models.TexturedModel;
import objConverter.ModelData;
import objConverter.OBJFileLoader;

import org.lwjgl.util.vector.Vector3f;

import renderEngine.MasterRenderer;
import sound.SoundManager;
import sound.SoundName;
import state.LevelState;
import terrains.Terrain;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;
import entities.Entity;

public class Hell extends LevelState{

	private List<Entity> entities;
	private List<Terrain> allTerrain;
	
	public Hell(){
		super();
		init();
	}
	
	private void init(){

		entities = new ArrayList<Entity>();

		/* ENDERMAN */
		ModelData endermanData = OBJFileLoader.loadOBJ("lowPolyTree");
		RawModel endermanModel = loader.loadToVAO(endermanData.getVertices(), endermanData.getTextureCoords(), endermanData.getNormals(), endermanData.getIndices());
		TexturedModel endermanTexturedModel = new TexturedModel(endermanModel, new ModelTexture(loader.loadEntityTexture("lowPolyHellTree")));
		
		Random random = new Random();
		for (int i = 0; i < 2000; i++) {
			float x = random.nextFloat() * (3200 - 0) + 0;
			float y = 0;
			float z = random.nextFloat() * (3200 - 0) + 0;
			entities.add(new Entity(endermanTexturedModel, new Vector3f(x, y, z), 0, random.nextFloat() * 180f, 0, 1));
		}

		/* Terrain */
		heightmap = "heightmap";
		TerrainTexture backgroundTexture = new TerrainTexture(loader.loadEntityTexture("hellGrass"));
		TerrainTexture rTexture = new TerrainTexture(loader.loadEntityTexture("hellDirt"));
		TerrainTexture gTexture = new TerrainTexture(loader.loadEntityTexture("hellPinkFlowers"));
		TerrainTexture bTexture = new TerrainTexture(loader.loadEntityTexture("hellPath"));

		TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
		TerrainTexture blendMap = new TerrainTexture(loader.loadMapTexture("hellBlendMap"));
		
		allTerrain = new ArrayList<Terrain>();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				allTerrain.add(new Terrain(i, j, loader, texturePack, blendMap, heightmap));
			}
		}

		setCameraPosition(1638, 45, 1610, 46, 20);
		setLightColour(new Vector3f(0.2f, 0.2f, 0.2f));
		MasterRenderer.setBackgroundColour(1.0f, 0.271f, 0.0f, 1);

		SoundManager.getInstance().playMusic(SoundName.Hell);
	}
	
	@Override
	public void ProcessRenderer(){

		for (Terrain terrain : allTerrain) {
			renderer.processTerrain(camera, terrain);
		}

		// enderman
		for (Entity entity : entities) {
			renderer.processEntity(camera, entity);
		}
	}
	

	
}
