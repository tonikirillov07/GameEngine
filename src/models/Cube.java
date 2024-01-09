package models;

import engine.RotationUtil;
import org.lwjgl.util.Color;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.*;

public class Cube extends Model implements IModels {

    public Cube(float scale, int texture, Vector3f position, RotationUtil rotation, Color color) {
        super(scale, texture, position, rotation, color);
    }

    @Override
    public void render() {
        applyTransformAndTextureBind();
        applyColor();

        float scale = getScale();

        //Front
        glNormal3f(0,0,-1);
        glBegin(GL_QUADS);
        glTexCoord3f(0, 0, 0);
        glVertex3f(0,0,0);
        glTexCoord3f(0, 1,0);
        glVertex3f(0, scale,0);
        glTexCoord3f(1, 1, 0);
        glVertex3f(scale, scale, 0);
        glTexCoord3f(1, 0, 0);
        glVertex3f(scale,0, 0);
        glEnd();

        //Back
        glBegin(GL_QUADS);
        glNormal3f(0,0,1);
        glTexCoord3f(0, 0, 1);
        glVertex3f(0,0, scale);
        glTexCoord3f(0, 1, 1);
        glVertex3f(0, scale, scale);
        glTexCoord3f(1, 1, 1);
        glVertex3f(scale, scale, scale);
        glTexCoord3f(1, 0, 1);
        glVertex3f(scale,0, scale);
        glEnd();

        //Top
        glBegin(GL_QUADS);
        glNormal3f(0,1,0);
        glTexCoord3f(0, 0, 0);
        glVertex3f(0,scale, 0);
        glTexCoord3f(0, 1, 0);
        glVertex3f(0, scale, scale);
        glTexCoord3f(1, 1, 0);
        glVertex3f(scale, scale, scale);
        glTexCoord3f(1, 0, 0);
        glVertex3f(scale,scale, 0);
        glEnd();

        //Bottom
        glBegin(GL_QUADS);
        glNormal3f(0,-1,0);
        glTexCoord3f(0, 0, 0);
        glVertex3f(0,0, 0);
        glTexCoord3f(0, 1, 0);
        glVertex3f(0, 0, scale);
        glTexCoord3f(1, 1, 0);
        glVertex3f(scale, 0, scale);
        glTexCoord3f(1, 0, 0);
        glVertex3f(scale,0, 0);
        glEnd();

        //Left
        glBegin(GL_QUADS);
        glNormal3f(-1,0,0);
        glTexCoord3f(0, 0, 0);
        glVertex3f(0,0, 0);
        glTexCoord3f(0, 1, 0);
        glVertex3f(0, scale, 0);
        glTexCoord3f(1, 1, 0);
        glVertex3f(0, scale, scale);
        glTexCoord3f(1, 0, 0);
        glVertex3f(0,0, scale);
        glEnd();

        //Right
        glBegin(GL_QUADS);
        glNormal3f(1,0,0);
        glTexCoord3f(0, 0, 0);
        glVertex3f(scale,0, 0);
        glTexCoord3f(0, 1, 0);
        glVertex3f(scale, scale, 0);
        glTexCoord3f(1, 1, 0);
        glVertex3f(scale, scale, scale);
        glTexCoord3f(1, 0, 0);
        glVertex3f(scale,0, scale);
        glEnd();

    }
}
