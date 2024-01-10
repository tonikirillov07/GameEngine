package gameWorld;

import engine.Constants;
import engine.Render;
import engine.RotationUtil;
import engine.TextureUtil;
import models.Cube;
import gameWorld.noise.SimplexNoise;
import org.lwjgl.util.vector.Vector3f;

import java.util.Random;

public class Level extends Constants {
    private final Render render;

    public Level(Render render) {
        this.render = render;
    }

    public void createLevel(int width, int length, int height, float cubeSize){
        int textureGrass = TextureUtil.createTextureId("textures/grass.png", TextureUtil.LINEAR);
        int textureDirt = TextureUtil.createTextureId("textures/dirt.png", TextureUtil.LINEAR);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                for (int k = 0; k < height; k++) {
                    render.loadModel(new Cube(cubeSize, new Random().nextInt(1,3) == 1 ? textureGrass: textureDirt, new Vector3f((i * cubeSize), -k * cubeSize, -j * cubeSize), RotationUtil.ZERO, WHITE));
                }
            }
        }
    }
}
