import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.lwjgl.opengl.GL11.*;

public class Test {

    private static Texture texture; // переменная для хранения текстуры
    private static int screenWidth = 800; // ширина экрана
    private static int screenHeight = 600; // высота экрана

    public static void main(String[] args) {
        try {
            Display.setDisplayMode(new DisplayMode(screenWidth, screenHeight));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        // Инициализация OpenGL
        GL11.glLoadIdentity();
        GL11.glOrtho(0, screenWidth, 0, screenHeight, 1, -1);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        glFrustum(-1,1,-1,1,2,800);

        // Загрузка текстуры
        try {
            texture = TextureLoader.getTexture("PNG", new FileInputStream("textures/target.png"));
        } catch (IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (!Display.isCloseRequested()) {
            // Очистка экрана
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            int textureWidth = texture.getTextureWidth();
            int textureHeight = texture.getTextureHeight();

            GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
            GL11.glBegin(GL11.GL_QUADS);
            GL11.glTexCoord2f(0, 0);
            GL11.glVertex2f((screenWidth - textureWidth) / 2, (screenHeight - textureHeight) / 2);
            GL11.glTexCoord2f(1, 0);
            GL11.glVertex2f((screenWidth + textureWidth) / 2, (screenHeight - textureHeight) / 2);
            GL11.glTexCoord2f(1, 1);
            GL11.glVertex2f((screenWidth + textureWidth) / 2, (screenHeight + textureHeight) / 2);
            GL11.glTexCoord2f(0, 1);
            GL11.glVertex2f((screenWidth - textureWidth) / 2, (screenHeight + textureHeight) / 2);
            GL11.glEnd();

            Display.update();
            Display.sync(60);
        }

        Display.destroy();
    }
}