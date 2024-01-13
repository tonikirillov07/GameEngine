package gameWorld;

import engine.Constants;
import engine.Render;
import engine.RotationUtil;
import engine.textures.TexturesDictionary;
import models.Cube;
import org.lwjgl.util.vector.Vector3f;

import java.util.Random;

public class Level extends Constants {
    private final Render render;
    private final TexturesDictionary texturesDictionary;

    public Level(Render render, TexturesDictionary texturesDictionary) {
        this.render = render;
        this.texturesDictionary = texturesDictionary;
    }

    public void createLevel(int width, int length, int height, float cubeSize){
        for (int x = 0; x < width; x++) {
            for (int z = 0; z < length; z++) {
                for (int y = 0; y < height; y++) {
                    render.loadModel(new Cube(cubeSize, new Random().nextInt(1,3) == 1 ? texturesDictionary.getGrassTexture(): texturesDictionary.getDirtTexture(), new Vector3f((x * cubeSize), -y * cubeSize, -z * cubeSize), RotationUtil.ZERO, WHITE));
                }
            }
        }
    }
}
