package gameWorld;

import engine.Render;
import engine.RotationUtil;
import engine.TextureUtil;
import models.Cube;
import org.lwjgl.util.Color;
import org.lwjgl.util.vector.Vector3f;

public class Level {
    private Render render;

    public Level(Render render) {
        this.render = render;
    }

    public void createLevel(int width, int length){
        int texture = TextureUtil.loadTexture("textures/crate.png", TextureUtil.LINEAR);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                render.loadModel(new Cube(0.4f, texture, new Vector3f(i * 0.4f, 0, j * 0.4f), RotationUtil.ZERO, new Color(255,255,255)));
            }
        }
    }
}
