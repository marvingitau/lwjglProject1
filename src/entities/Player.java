/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import models.TexturedModel;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.DisplayManager;
import terrains.Terrain;

/**
 *
 * @author L
 */
public class Player extends Entity {
    private static final float RUN_SPEED=1000;
    private static final float TURN_SPEED=160;
    private static final float GRAVITY=-500;
    private static final float JUMP_POWER=1000;
    
    private static final float TERRAIN_HEIGHT=0;
    
    private float currentSpeed=0;
    private float currentTurnSpeed=0;
    private float upwardSpeed=0;
    
    boolean inAir=true;
    
     
    public Player(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        super(model, position, rotX, rotY, rotZ, scale);
    }
    public void move(Terrain terrain){
       checkInputs();
       super.increaseRotation(0, currentTurnSpeed*DisplayManager.getFrameTimeSeconds(), 0);
       //super.setRotY(currentTurnSpeed*DisplayManager.getFrameTimeSeconds());
       
       float distance = currentSpeed*DisplayManager.getFrameTimeSeconds();
       float dx = (float) (distance*Math.sin(Math.toRadians(super.getRotY())));
       float dz = (float) (distance*Math.cos(Math.toRadians(super.getRotY())));
       
       super.increasePosition(dx, 0, dz);
       upwardSpeed+=GRAVITY*DisplayManager.getFrameTimeSeconds();
    
      super.increasePosition(0,upwardSpeed*DisplayManager.getFrameTimeSeconds(),0);
   
      // System.out.println("x>>"+super.getPosition().x+"z>>"+super.getPosition().z);
      try{
      float terrainHeight=terrain.getHeightOfTerrain(super.getPosition().x, super.getPosition().z);
      
       if(super.getPosition().y<terrainHeight){
           upwardSpeed=0;
           super.getPosition().y=terrainHeight;
           inAir=true;
       }
      }catch(ArrayIndexOutOfBoundsException ex){
          System.out.println("out of landscape"+ex);
          
      }
      
      
    }
    private void jump(){
        if(inAir){
        this.upwardSpeed=JUMP_POWER;
        inAir=true;
        }
    }
    public void checkInputs(){
        if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
            this.currentSpeed=RUN_SPEED;
        }else if(Keyboard.isKeyDown((Keyboard.KEY_DOWN))){
            this.currentSpeed=-RUN_SPEED;
        }else{
            this.currentSpeed=0;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
            this.currentTurnSpeed=+TURN_SPEED;
        }else if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
            this.currentTurnSpeed=-TURN_SPEED;
        }else{
            this.currentTurnSpeed=0;
        }
//        if(Keyboard.isKeyDown(Keyboard.KEY_V)){
//            this.currentTurnSpeed=-TURN_SPEED;
//        }else if(Keyboard.isKeyDown(Keyboard.KEY_C)){
//            this.currentTurnSpeed=TURN_SPEED;
//        }else{
//            this.currentTurnSpeed=0;
//        }
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
            jump();
        }
        
    }
}
