package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	private float distanceFromPlayer=50;
        private float angleAroundPlayer=0;
        
        
	private Vector3f position = new Vector3f(0,5,0);
	private float pitch =20;
	private float yaw ;
	private float roll;
	
        private Player player;
        
        
	public Camera(Player player){
        
            this.player=player;
        }
	
	public void move(){
            calculateZoom();
            calculatePitch();
            calculateAngleAroundPlayer();
            float horizontalDistance=calculateHorizontalDistance();
            float verticalDistace=calculateVerticalDistance();
            calculateCameraPosition(horizontalDistance,verticalDistace);
            this.yaw=180-(player.getRotY()+angleAroundPlayer);
//		if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
//			position.z-=2f;
//		}
//                if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
//                        position.z+=2f;
//                }
//                //F/B
//		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
//			position.x+=2f;
//		}
//		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
//			position.x-=2f;
//		}
                //L/R
                
//		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
//			position.y-=2f;
//		}
//		if(Keyboard.isKeyDown(Keyboard.KEY_U)){
//			position.y+=2f;
//		}
//                if(Keyboard.isKeyDown(Keyboard.KEY_M)){
//                     pitch+=2f;
//                }
//                 if(Keyboard.isKeyDown(Keyboard.KEY_N)){
//                     pitch-=2f;
//                }
//                   if(Keyboard.isKeyDown(Keyboard.KEY_V)){
//                     yaw+=2f;
//                }
//                 if(Keyboard.isKeyDown(Keyboard.KEY_B)){
//                     yaw-=2f;
//                } 
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;  // act on x axis
	}

	public float getYaw() {
		return yaw;  //act on y axis
	}

	public float getRoll() {
		return roll;  // act on z axis
	}
        
        private float calculateHorizontalDistance(){
            return (float)(distanceFromPlayer*Math.cos(Math.toRadians(pitch)));
            
        }
          private float calculateVerticalDistance(){
            return (float)(distanceFromPlayer*Math.sin(Math.toRadians(pitch)));
            
        }
	
	private void calculateZoom(){
            float zoomLevel=Mouse.getDWheel()*0.1f;
            distanceFromPlayer-=zoomLevel;
        }
        
        private void calculatePitch(){
            if(Mouse.isButtonDown(1)){
                float pitchChange=Mouse.getDY()*0.1f;
                pitch-=pitchChange;
            }
        }
        
        private void calculateAngleAroundPlayer(){
            if(Mouse.isButtonDown(0)){
                float angleChange=Mouse.getDX()*0.3f;
                angleAroundPlayer-=angleChange;
            }
        }
        private void calculateCameraPosition(float horizDistance,float verticDistance){
            float theta = player.getRotY()+angleAroundPlayer;
            float offsetX=(float) (horizDistance*Math.sin(Math.toRadians(theta)));
             float offsetZ=(float) (horizDistance*Math.cos(Math.toRadians(theta)));
             
           position.x=player.getPosition().x-offsetX;
           position.z=player.getPosition().z-offsetZ;
           
            position.y=player.getPosition().y+verticDistance;
        }
}
