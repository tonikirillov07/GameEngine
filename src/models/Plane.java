package models;

import engine.RotationUtil;
import org.lwjgl.util.Color;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.*;

public class Plane extends Model implements IModels{
    public Plane(float scale, int texture, Vector3f position, RotationUtil rotation, Color color) {
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
        glTexCoord3f(0,0,1);
        glVertex3f(0,0, scale);
        glTexCoord3f(1,0,1);
        glVertex3f(scale,0, scale);
        glTexCoord3f(1,0,0);
        glVertex3f(scale,0,0);
        glEnd();
    }
}
