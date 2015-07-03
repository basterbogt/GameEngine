package state.level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.RawModel;
import models.TexturedModel;
import objConverter.ModelData;
import objConverter.OBJFileLoader;

import org.lwjgl.util.vector.Vector3f;

import sound.SoundManager;
import sound.SoundName;
import state.LevelState;
import terrains.Terrain;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;
import entities.Entity;
import entities.Lamp;

public class Overworld extends LevelState {

	private List<Entity> entities;
	private List<Entity> forestEntities;
	private Terrain[][] allTerrain;

	public Overworld() {
		super();
		init();
	}

	private void init() {

		entities = new ArrayList<Entity>();
		
		/* TERRAIN */
		heightmap = "heightmap";
		TerrainTexture backgroundTexture = new TerrainTexture(loader.loadEntityTexture("grassy"));
		TerrainTexture rTexture = new TerrainTexture(loader.loadEntityTexture("dirt"));
		TerrainTexture gTexture = new TerrainTexture(loader.loadEntityTexture("pinkFlowers"));
		TerrainTexture bTexture = new TerrainTexture(loader.loadEntityTexture("path"));
		
		TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
		TerrainTexture blendMap = new TerrainTexture(loader.loadMapTexture("blendMap"));
		
		allTerrain = new Terrain[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				allTerrain[i][j] = new Terrain(i, j, loader, texturePack, blendMap, heightmap);
			}
		}

		
		/* DRAGON */
		ModelData dragonData = OBJFileLoader.loadOBJ("dragon");
		RawModel dragonModel = loader.loadToVAO(dragonData.getVertices(), dragonData.getTextureCoords(), dragonData.getNormals(), dragonData.getIndices());
		TexturedModel dragonTexturedModel = new TexturedModel(dragonModel, new ModelTexture(loader.loadEntityTexture("black")));
		ModelTexture dragonTexture = dragonTexturedModel.getTexture();
		dragonTexture.setShineDamper(10);
		dragonTexture.setReflectivity(1);
		entities.add(new Entity(dragonTexturedModel, new Vector3f(1700, 0, 1500), 0, 0, 0, 3f));

		
		/* Car */
		ModelData carData = OBJFileLoader.loadOBJ("pillar");
		RawModel carModel = loader.loadToVAO(carData.getVertices(), carData.getTextureCoords(), carData.getNormals(), carData.getIndices());
		TexturedModel carTexturedModel = new TexturedModel(carModel, new ModelTexture(loader.loadEntityTexture("pillar")));
		ModelTexture carTexture = dragonTexturedModel.getTexture();
		carTexture.setShineDamper(10);
		carTexture.setReflectivity(1);
		entities.add(new Entity(carTexturedModel, new Vector3f(1600, 0, 1500), 0, 0, 0, 0.3f));

		/* Bunnies */
		ModelData bunnyData = OBJFileLoader.loadOBJ("bunny");
		RawModel bunnyModel = loader.loadToVAO(bunnyData.getVertices(), bunnyData.getTextureCoords(), bunnyData.getNormals(), bunnyData.getIndices());
		TexturedModel bunnyTexturedModel = new TexturedModel(bunnyModel, new ModelTexture(loader.loadEntityTexture("white")));
		ModelTexture bunnyTexture = bunnyTexturedModel.getTexture();
		bunnyTexture.setShineDamper(10);
		bunnyTexture.setReflectivity(1);
		entities.add(new Entity(bunnyTexturedModel, new Vector3f(1672, 0, 1570), 0, 0, 0, 0.3f));
		entities.add(new Entity(bunnyTexturedModel, new Vector3f(1675, 0, 1570), 0, 0, 0, 0.3f));
		entities.add(new Entity(bunnyTexturedModel, new Vector3f(1678, 0, 1570), 0, 0, 0, 0.3f));

		/* Tree's */
		ModelData treeData = OBJFileLoader.loadOBJ("lowPolyTree");
		RawModel treeModel = loader.loadToVAO(treeData.getVertices(), treeData.getTextureCoords(), treeData.getNormals(), treeData.getIndices());
		TexturedModel treeTexturedModel = new TexturedModel(treeModel, new ModelTexture(loader.loadEntityTexture("lowPolyTree")));

		ModelData grassData = OBJFileLoader.loadOBJ("grassModel");
		RawModel grassModel = loader.loadToVAO(grassData.getVertices(), grassData.getTextureCoords(), grassData.getNormals(), grassData.getIndices());
		TexturedModel grassTexturedModel = new TexturedModel(grassModel, new ModelTexture(loader.loadEntityTexture("grassTexture")));
		grassTexturedModel.getTexture().setHasTransparency(true);
		grassTexturedModel.getTexture().setUseFakeLightning(true);

		ModelData fernData = OBJFileLoader.loadOBJ("fern");
		RawModel fernModel = loader.loadToVAO(fernData.getVertices(), fernData.getTextureCoords(), fernData.getNormals(), fernData.getIndices());
		ModelTexture fernTextureAtlas = new ModelTexture(loader.loadEntityTexture("fern"));
		fernTextureAtlas.setNumberOfRows(2);
		TexturedModel fernTexturedModel = new TexturedModel(fernModel,fernTextureAtlas);
		fernTexturedModel.getTexture().setHasTransparency(true);

		ModelData plantData = OBJFileLoader.loadOBJ("plant");
		RawModel plantModel = loader.loadToVAO(plantData.getVertices(), plantData.getTextureCoords(), plantData.getNormals(), plantData.getIndices());
		TexturedModel plantTexturedModel = new TexturedModel(plantModel, new ModelTexture(loader.loadEntityTexture("green")));

		Random random = new Random();
		forestEntities = new ArrayList<Entity>();
		for (int i = 0; i < 4000; i++) {
			float x = random.nextFloat() * 3200;
			float z = random.nextFloat() * 3200;
			Terrain currentTerrain = getCurrentTerrain(x, z);
			float y = currentTerrain.getHeightOfTerrain(x, z);
			
			int chance = random.nextInt(4);

			switch(chance){
			case 0:
				forestEntities.add(new Entity(treeTexturedModel, new Vector3f(x, -5, z), 0, random.nextFloat() * 180f, 0, 10));
				break;
			case 1:
				forestEntities.add(new Entity(grassTexturedModel, new Vector3f(x, y, z), 0, random.nextFloat() * 180f, 0, 1));
				break;
			case 2:
				forestEntities.add(new Entity(fernTexturedModel, random.nextInt(4), new Vector3f(x, y, z), 0, random.nextFloat() * 180f, 0, 0.6f));
				break;
			case 3:
				forestEntities.add(new Entity(plantTexturedModel, new Vector3f(x, y, z), 0, random.nextFloat() * 180f, 0, 0.6f));
				break;
			}
		}
		Terrain currentTerrain = getCurrentTerrain(1650, 1550);
		entities.add(new Entity(grassTexturedModel, new Vector3f(1650, currentTerrain.getHeightOfTerrain(1650, 1550), 1550), 0, 90f, 0, 1));
		entities.add(new Entity(fernTexturedModel, new Vector3f(1650, currentTerrain.getHeightOfTerrain(1650, 1550), 1550), 0, 90f, 0, 0.6f));
		entities.add(new Entity(plantTexturedModel, new Vector3f(1620, currentTerrain.getHeightOfTerrain(1620, 1570), 1570), 0, 120f, 0, 0.6f));
		
		
		/* Lamps */
		ModelData lampData = OBJFileLoader.loadOBJ("lamp");
		RawModel lampModel = loader.loadToVAO(lampData.getVertices(), lampData.getTextureCoords(), lampData.getNormals(), lampData.getIndices());
		TexturedModel lampTexturedModel = new TexturedModel(lampModel, new ModelTexture(loader.loadEntityTexture("lamp")));
		ModelTexture lampTexture = lampTexturedModel.getTexture();
		lampTexture.setShineDamper(10);
		lampTexture.setReflectivity(0);
		lampTexture.setUseFakeLightning(true);
		
		
		
		entities.add(new Lamp(lampTexturedModel, new Vector3f(1750, getCurrentTerrain(1750,1500).getHeightOfTerrain(1750,1500), 1500), 0, 0, 0, 1f, new Vector3f(2, 0, 0), new Vector3f(1, 0.01f, 0.002f), lights));
		entities.add(new Lamp(lampTexturedModel, new Vector3f(1650, getCurrentTerrain(1750,1500).getHeightOfTerrain(1650,1550), 1550), 0, 0, 0, 1f, new Vector3f(0, 2, 2), new Vector3f(1, 0.01f, 0.002f), lights));


		SoundManager.getInstance().playMusic(SoundName.Overworld);
	}

	@Override
	public void ProcessRenderer() {
		player.move(getCurrentTerrain(player.getPosition().x, player.getPosition().z));
		camera.move();
		renderer.processEntity(camera, player);
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				renderer.processTerrain(camera, allTerrain[i][j]);
			}
		}

		// Entities
		for (Entity entity : entities) {
			renderer.processEntity(camera, entity);
		}

		// Forest
		for (Entity entity : forestEntities) {
			Vector3f position = entity.getPosition();
			if (!((position.x > 1550 && position.x < 1740) && (position.z > 1400 && position.z < 1650))) {
				renderer.processEntity(camera, entity);
			}
		}
	}

	
	private Terrain getCurrentTerrain(float worldX, float worldY){
		float terrainSize = 800;
		int gridX = (int) Math.floor(worldX / terrainSize);
		int gridZ = (int) Math.floor(worldY / terrainSize);
		return allTerrain[gridX][gridZ];
	}
	
}
