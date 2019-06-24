package lwjgltest15.a;
//muharwo niwe uthigataga githaka
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.RawModel;
import models.TexturedModel;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrains.Terrain;
import textures.ModelTexture;
import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import gui.GuiRenderer;
import gui.GuiTexture;
import java.io.IOException;
import org.lwjgl.util.vector.Vector2f;
import textures.TerrainTexture;
import textures.TerrainTexturePack;

public class LwjglTest15A {

	public static void main(String[] args) throws IOException {

		DisplayManager.createDisplay();
		Loader loader = new Loader();
		
                //terrain textures//
                TerrainTexture backgroundTexture=new TerrainTexture(loader.loadTexture("grassy"));
		TerrainTexture rTexture=new TerrainTexture(loader.loadTexture("mud"));
                TerrainTexture gTexture=new TerrainTexture(loader.loadTexture("white"));
                TerrainTexture bTexture=new TerrainTexture(loader.loadTexture("path"));
                
                
                
                TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture,rTexture,gTexture,bTexture);
                TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));
                
                //....................//
                
                
                
                
                
                
		RawModel model = OBJLoader.loadObjModel("pine", loader);
		TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("pine")));
                
                RawModel model1 = OBJLoader.loadObjModel("tree0", loader);
		TexturedModel staticModel1 = new TexturedModel(model1,new ModelTexture(loader.loadTexture("tree0")));
                
                RawModel model2=OBJLoader.loadObjModel("box", loader);
                TexturedModel box=new TexturedModel(model2,new ModelTexture(loader.loadTexture("box")));
                
                TexturedModel grass=new TexturedModel(OBJLoader.loadObjModel("grassModel", loader),new ModelTexture(loader.loadTexture("grassTexture")));
                grass.getTexture().setHasTransparency(true);
                grass.getTexture().setUseFakeLighting(true);
               // TexturedModel fern = new TexturedModel(OBJLoader.loadObjModel("fern", loader),new ModelTexture(loader.loadTexture("fern")));
                
               // fern.getTexture().setHasTransparency(true);
                
                  ModelTexture fernTextureAtlas = new ModelTexture(loader.loadTexture("atlas"));
                  fernTextureAtlas.setNumberOfRows(2);
                  TexturedModel atla=new TexturedModel(OBJLoader.loadObjModel("fern", loader),fernTextureAtlas);
                 // fernTextureAtlas.setUseFakeLighting(true);
                
                Terrain terrain = new Terrain(-.5f,0,loader,texturePack,blendMap,"heightmap");
                
		List<Entity> entities = new ArrayList<Entity>();
		Random random = new Random();
		for(int i=0;i<3000;i++){
                    if(i%3==0){
                        float x=random.nextFloat()*8000;
                        float z=random.nextFloat()*6000;
                        float y=terrain.getHeightOfTerrain(x, z);
                        
                      
                        
                         //entities.add(new Entity(fern,new Vector3f(x,y,z),0,random.nextFloat()*3600,0,0.6f));
                         entities.add(new Entity(atla,random.nextInt(4),new Vector3f(x,y,z),0,random.nextFloat()*3600,0,4f));
                         // entities.add(new Entity(staticModel1, new Vector3f(x,y,z),0,0,0,18));
                    }
                    if(i%5==0){
                         float x=random.nextFloat()*3000;
                        float z=random.nextFloat()*9000;
                        float y=terrain.getHeightOfTerrain(x, z);
                        
                        entities.add(new Entity(staticModel, new Vector3f(x,y,z),0,random.nextFloat()*360,0,10));
                      
                        entities.add(new Entity(grass,new Vector3f(x,y,z),0,0,0,3f));
                        
                    }
			  float x=random.nextFloat()*3000;
                        float z=random.nextFloat()*9000;
                        float y=terrain.getHeightOfTerrain(x, z);
                       
                        // entities.add(new Entity(fern,new Vector3f(random.nextFloat()*8000,y,random.nextFloat()*600),0,0,0,0.6f));
                       
                        //entities.add(new Entity(grass,new Vector3f(random.nextFloat()*8000-4000,0,random.nextFloat()*-9000),0,0,0,3f));
                       // entities.add(new Entity(fern,new Vector3f(random.nextFloat()*8000-4000,0,random.nextFloat()*-9000),0,0,0,0.6f));
		}
//                for(int i=0;i<1000;i++){
//                    entities.add(new Entity(grass,new Vector3f(random.nextFloat()*8000-4000,0,random.nextFloat()*-9000),0,0,0,3f));
//                        entities.add(new Entity(fern,new Vector3f(random.nextFloat()*8000-4000,0,random.nextFloat()*-9000),0,0,0,0.6f));
//                }
		entities.add(new Entity(box,new Vector3f(50,3,140),0,0,0,3));
                //entities.add(new Entity(box,new Vector3f(-50,53,-300),0,0,0,50));
                
		Light light = new Light(new Vector3f(4000,2000,4000),new Vector3f(.906f,.973f,.055f));   //.906f,.973f,.055f
		
//		Terrain terrain = new Terrain(-.5f,0,loader,texturePack,blendMap,"heightmap");
		//Terrain terrain2 = new Terrain(2f,0,loader,texturePack,blendMap);
		
			
		MasterRenderer renderer = new MasterRenderer();
		
                
                RawModel bunnyModel= OBJLoader.loadObjModel("person", loader);
                TexturedModel stanBunny=new TexturedModel(bunnyModel,new ModelTexture(loader.loadTexture("playerTexture")));
                
                Player player = new Player(stanBunny,new Vector3f(4600,0,5900),0,0,0,1);
                
                Camera camera = new Camera(player);
                
                List<GuiTexture> guis=new ArrayList<GuiTexture>();
                GuiTexture gui= new GuiTexture(loader.loadTexture("health"),new Vector2f(-0.7f,0.9f),new Vector2f(.25f,.25f));
                guis.add(gui);
                GuiRenderer guiRenderer= new GuiRenderer(loader);
                
                
		while(!Display.isCloseRequested()){
			player.move(terrain);
                        camera.move();
			renderer.processEntity(player);
			renderer.processTerrain(terrain);
			//renderer.processTerrain(terrain2);
			for(Entity entity:entities){
				renderer.processEntity(entity);
			}
			renderer.render(light, camera);
                        guiRenderer.render(guis);
			DisplayManager.updateDisplay();
		}
                
                guiRenderer.cleanUp();
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
