/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.List;
import models.RawModel;
import renderEngine.Loader;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import org.lwjgl.util.vector.Matrix4f;
import toolbox.Maths;
/**
 *
 * @author L
 */
public class GuiRenderer {
    private final RawModel quad;
    private GuiShader shader;
    
    public GuiRenderer(Loader loader){
    
        float[] positions={-1,1,-1,-1,1,1,1,-1};
        quad=loader.loadToVAO(positions);
        shader = new GuiShader();
    }
    
    public void render(List<GuiTexture>guis){
        shader.start();
        glBindVertexArray(quad.getVaoID());
        glEnableVertexAttribArray(0);
        //render
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);
        glDisable(GL_DEPTH_TEST);
        for(GuiTexture gui:guis){
            glActiveTexture(GL_TEXTURE0);
            glBindTexture(GL_TEXTURE_2D,gui.getTexture());
            Matrix4f matrix = Maths.createTransformationMatrix(gui.getPosition(), gui.getScale());
             shader.loadTransformation(matrix);
            glDrawArrays(GL_TRIANGLE_STRIP,0,quad.getVertexCount());
        }
        glEnable(GL_DEPTH_TEST);
        glDisable(GL_BLEND);
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);
        shader.start();
    }
    
    public void cleanUp(){
        shader.cleanUp();
    }
    
}
