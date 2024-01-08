package models;

import engine.RotationUtil;
import org.lwjgl.util.vector.Vector3f;

public interface IModels {
    void render();
    void rotate(RotationUtil rotationUtil);
    void loadIdentity();
}
