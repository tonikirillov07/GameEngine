package models;

import engine.RotationUtil;
import org.lwjgl.util.Color;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.*;

public abstract class Model {
    private final float scale;
    private int texture;
    private Vector3f position;
    private RotationUtil rotation;
    private Color color;

    public Model(float scale, int texture, Vector3f position, RotationUtil rotation, Color color) {
        this.scale = scale;
        this.texture = texture;
        this.position = position;
        this.rotation = rotation;
        this.color = color;
    }

    public float getScale() {
        return scale;
    }

    public int getTexture() {
        return texture;
    }

    public Vector3f getPosition() {
        return position;
    }

    public RotationUtil getRotation() {
        return rotation;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void applyTransformAndTextureBind() {
        glBindTexture(GL_TEXTURE_2D, getTexture());

        glLoadIdentity();
        glTranslatef(getPosition().x, getPosition().y, getPosition().z);
        glRotatef(getRotation().angle(), getRotation().x(), getRotation().y(), getRotation().z());
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public void applyColor(){
        glColor3f(color.getRed(), color.getGreen(), color.getBlue());
    }

    public void setRotation(RotationUtil rotation) {
        this.rotation = rotation;
    }

    @Override
    public String toString() {
        return "Model{" +
                "scale=" + scale +
                ", texture=" + texture +
                ", position=" + position +
                ", rotation=" + rotation +
                ", color=" + color +
                '}';
    }
}
