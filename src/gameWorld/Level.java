package gameWorld;

import engine.Constants;
import engine.Render;
import engine.RotationUtil;
import engine.TextureUtil;
import models.Cube;
import org.lwjgl.util.vector.Vector3f;

import java.util.Random;

public class Level extends Constants {
    private final Render render;

    public Level(Render render) {
        this.render = render;
    }

    public void createLevel(int width, int length, float cubeSize){
        int textureGrass = TextureUtil.loadTexture("textures/grass.png", TextureUtil.LINEAR);
        int textureDirt = TextureUtil.loadTexture("textures/dirt.png", TextureUtil.LINEAR);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                render.loadModel(new Cube(cubeSize, new Random().nextInt(1,3) == 1 ? textureGrass: textureDirt, new Vector3f(i * cubeSize, -1, -j * cubeSize), RotationUtil.ZERO, WHITE));
            }
        }
    }
}
