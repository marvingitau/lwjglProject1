/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textures;

/**
 *
 * @author L
 */
public class TerrainTexturePack {     
    private TerrainTexture backgroundTexture;
       private TerrainTexture rTexture;
          private TerrainTexture bTexture;
             private TerrainTexture gTexture;
             //
             //the rgb texture naming is due to the blend map
             //
            
   public TerrainTexturePack(TerrainTexture backgroundTexture,TerrainTexture rTexture,TerrainTexture gTexture,TerrainTexture bTexture){
       this.backgroundTexture=backgroundTexture;
       this.bTexture=bTexture;
       this.rTexture=rTexture;
       this.gTexture=gTexture;
   }
   public TerrainTexture getBackgroundTexture(){
       return backgroundTexture;
   }
   
   public TerrainTexture getrTexture(){
       return rTexture;
   }
   public TerrainTexture getbTexture(){
       return bTexture;
   }
   public TerrainTexture getgTexture(){
       return gTexture;
   }
   
}
