package engine.textures;

import engine.Constants;

public class TexturesDictionary extends Constants {
    public int grassTexture = 0;
    public int dirtTexture = 0;
    public int logOakTexture = 0;

    public void loadTextures(){
        grassTexture = TextureUtil.createTextureId(TEXTURES_FOLDER + "grass.png", TextureUtil.LINEAR);
        dirtTexture = TextureUtil.createTextureId(TEXTURES_FOLDER + "dirt.png", TextureUtil.LINEAR);
        logOakTexture = TextureUtil.createTextureId(TEXTURES_FOLDER + "log_oak.png", TextureUtil.LINEAR);
    }

    public int getGrassTexture() {
        return grassTexture;
    }

    public int getDirtTexture() {
        return dirtTexture;
    }

    public int getLogOakTexture() {
        return logOakTexture;
    }
}
