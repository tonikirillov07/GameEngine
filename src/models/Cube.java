package models;

import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.*;

public class Cube implements IModels{
    private final float scale;
    private final int texture;

    public Cube(float scale, int texture) {
        this.scale = scale;
        this.texture = texture;
    }

    @Override
    public void render() {
        glBindTexture(GL_TEXTURE_2D, texture);

        //Front
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

    @Override
    public void rotateY(float angle) {
        glRotatef(angle, 0, 1, 0);
    }

    @Override
    public void rotateX(float angle) {
        glRotatef(angle, 1, 0, 0);
    }

    @Override
    public void rotateZ(float angle) {
        glRotatef(angle, 0, 0, 1);
    }

    @Override
    public void move(Vector3f position) {
        glTranslatef(position.x, position.y, position.z);
    }

    @Override
    public void loadIdentity() {
        glLoadIdentity();
    }
}
