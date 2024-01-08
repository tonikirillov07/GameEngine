package models;

import engine.RotationUtil;
import org.lwjgl.util.Color;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.*;

public class Rectangle extends Model implements IModels{
    public Rectangle(float scale, int texture, Vector3f position, RotationUtil rotation, Color color) {
        super(scale, texture, position, rotation, color);
    }

    @Override
    public void render() {
       applyTransformAndTextureBind();
       applyColor();

        float scale = getScale();

        glBegin(GL_QUADS);
        glTexCoord3f(0,0,0);
        glVertex3f(0,0,0);
        glTexCoord3f(0,1,0);
        glVertex3f(0, scale,0);
        glTexCoord3f(1,1,0);
        glVertex3f(scale, scale, 0);
        glTexCoord3f(1,0,0);
        glVertex3f(scale, 0, 0);
        glEnd();
    }

    @Override
    public void rotate(RotationUtil rotationUtil) {
        setRotation(rotationUtil);
    }

    @Override
    public void loadIdentity() {
        glLoadIdentity();
    }
}
