package engine;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;

import static org.lwjgl.opengl.GL11.GL_LINEAR;
import static org.lwjgl.opengl.GL11.GL_NEAREST;

public class TextureUtil {
    public static final int NEAREST = GL_NEAREST;
    public static final int LINEAR = GL_LINEAR;

    public static int loadTexture(String path, int filter){
        try {
            Texture texture = TextureLoader.getTexture("PNG", new FileInputStream(path), filter);

            if(!isNormalTexture(texture)){
                ShowExceptions.showException(new Exception("The texture has the wrong resolution! It may not display correctly. Texture size: " + texture.getImageWidth() + "x" + texture.getImageHeight()));
            }

            return texture.getTextureID();
        }catch (Exception e){
            ShowExceptions.showException(e);
        }

        return -1;
    }

    private static boolean isNormalTexture(Texture texture){
        return texture.getImageWidth() == texture.getImageHeight();
    }
}
