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

        for (int x = 0; x < width; x++) {
            for (int z = 0; z < length; z++) {
                for (int y = 0; y < height; y++) {
                    render.loadModel(new Cube(cubeSize, new Random().nextInt(1,3) == 1 ? textureGrass: textureDirt, new Vector3f((x * cubeSize), -y * cubeSize, -z * cubeSize), RotationUtil.ZERO, WHITE));
                }
            }
        }
    }
}
