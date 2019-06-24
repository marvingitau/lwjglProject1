/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author L
 */
public class GuiTexture {
    private int texture;
    private Vector2f position;
    private Vector2f scale;
    
    
    public GuiTexture(int texture,Vector2f position,Vector2f scale){
        this.position=position;
        this.texture=texture;
        this.scale=scale;
    }
    
    public int getTexture(){
        return texture;
    }
    public Vector2f getScale(){
        return scale;
    }
    public Vector2f getPosition(){
        return position;
    }
}
