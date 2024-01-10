package UI;

import engine.Window;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class UIRenderer {
    private List<UIElement> uiElements = new ArrayList<>();
    private Window window;

    public UIRenderer(Window window) {
        this.window = window;
    }

    public void renderAll(){
        for (UIElement uiElement : uiElements) {
            if(!uiElement.isRenderInCenter()) {
                glLoadIdentity();
                glTranslatef(uiElement.getPosition().x, uiElement.getPosition().y, 0);
                glRotatef(uiElement.getRotationUtil().getAngle(), uiElement.getRotationUtil().getX(), uiElement.getRotationUtil().getY(), uiElement.getRotationUtil().getZ());
            }

            float textureWidth = uiElement.getTexture().getTextureWidth();
            float textureHeight = uiElement.getTexture().getTextureWidth();

            glBindTexture(GL_TEXTURE_2D, uiElement.getTextureId());
            GL11.glBegin(GL11.GL_QUADS);
            GL11.glTexCoord2f(0, 0);
            GL11.glVertex2f((window.getWidth() - textureWidth) / 2, (window.getHeight() - textureHeight) / 2);
            GL11.glTexCoord2f(1, 0);
            GL11.glVertex2f((window.getWidth() + textureWidth) / 2, (window.getHeight() - textureHeight) / 2);
            GL11.glTexCoord2f(1, 1);
            GL11.glVertex2f((window.getWidth() + textureWidth) / 2, (window.getHeight() + textureHeight) / 2);
            GL11.glTexCoord2f(0, 1);
            GL11.glVertex2f((window.getWidth() - textureWidth) / 2, (window.getHeight() + textureHeight) / 2);
            GL11.glEnd();;
        }
    }

    public void loadUiElement(UIElement uiElement){
        uiElements.add(uiElement);
    }
}
