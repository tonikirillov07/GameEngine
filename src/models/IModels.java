package models;

import org.lwjgl.util.vector.Vector3f;

public interface IModels {
    void render();
    void rotateY(float angle);
    void rotateX(float angle);
    void rotateZ(float angle);
    void move(Vector3f position);
    void loadIdentity();
}
