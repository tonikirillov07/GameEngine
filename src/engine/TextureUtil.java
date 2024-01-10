package engine;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.FileInputStream;

import static org.lwjgl.opengl.GL11.GL_LINEAR;
import static org.lwjgl.opengl.GL11.GL_NEAREST;

public class TextureUtil {
    public static final int NEAREST = GL_NEAREST;
    public static final int LINEAR = GL_LINEAR;

    public static int createTextureInteger(String path, int filter){
        try {
            Texture texture = getTexture(path, filter);
            checkIsTextureNormal(texture);

            assert texture != null;
            return texture.getTextureID();
        }catch (Exception e){
            ShowExceptions.showException(e);
        }

        return -1;
    }

    public static Texture createTexture(String path, int filter){
        try {
            Texture texture = getTexture(path, filter);
            checkIsTextureNormal(texture);

            return texture;
        }catch (Exception e){
            ShowExceptions.showException(e);
        }

        return null;
    }

    private static Texture getTexture(String path, int filter){
        try {
            return TextureLoader.getTexture("PNG", new FileInputStream(path), filter);
        }catch (Exception e){
            ShowExceptions.showException(e);
        }

        return null;
    }

    private static void checkIsTextureNormal(Texture texture){
        if(!isNormalTexture(texture)){
            ShowExceptions.showException(new Exception("The texture has the wrong resolution! It may not display correctly. Texture size: " + texture.getImageWidth() + "x" + texture.getImageHeight()));
        }
    }

    private static boolean isNormalTexture(Texture texture){
        return texture.getImageWidth() == texture.getImageHeight();
    }
}
