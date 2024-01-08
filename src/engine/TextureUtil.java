package engine;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.FileInputStream;

import static org.lwjgl.opengl.GL11.GL_LINEAR;
import static org.lwjgl.opengl.GL11.GL_NEAREST;

public class TextureUtil {
    public static final int NEAREST = GL_NEAREST;
    public static final int LINEAR = GL_LINEAR;

    public static int loadTexture(String path, int filter){
        try {
            Texture texture = TextureLoader.getTexture("PNG", new FileInputStream(path), filter);

            return texture.getTextureID();
        }catch (Exception e){
            ShowExceptions.showException(e);
        }

        return -1;
    }
}
