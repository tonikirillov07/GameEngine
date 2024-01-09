package engine;

import gameWorld.Environment;
import gameWorld.Level;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import player.Player;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class Window extends Constants {
    private Render render;
    private float currentTime, passedTime, deltaTime, fps;
    private Level level;
    private TrueTypeFont trueTypeFont;
    private Player player;
    private Environment environment;

    public void run(){
        try {
            Display.setDisplayMode(new DisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT));
            Display.setTitle(TITLE);
            Display.setResizable(IS_RESIZABLE);
            Display.setVSyncEnabled(USE_VSYNC);
            Display.create();

            Display.swapBuffers();

            checkError();

            Mouse.create();
            Keyboard.create();

            Mouse.setGrabbed(GRAB_MOUSE);

            Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
            trueTypeFont = new TrueTypeFont(awtFont, false);

            initGLSettings();
            windowResize(getWidth(), getHeight());

            trueTypeFont = new TrueTypeFont(new UnicodeFont(new Font("Arial", Font.BOLD, 24)).getFont(), true);

            render = new Render();

            player = new Player();

            level = new Level(render);
            level.createLevel(15, 15, 0.4f);

            environment = new Environment();
            environment.enableFog(true);
            environment.initFogDefault();

            update();
        }catch (Exception e){
            ShowExceptions.showException(e);
        }
    }

    private void checkError(){
        int error = GL11.glGetError();
        if(error != 0) ShowExceptions.showException(new Exception("An exception occurred. Error code: " + error));
    }

    public void dispose(){
        Keyboard.destroy();
        Mouse.destroy();

        Display.destroy();
        System.exit(0);
    }

    private void update(){
        while(isOpen()){
            currentTime = System.nanoTime();
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            player.move(getDeltaTime());
            setupCamera();

            render.renderAll();

            //trueTypeFont.drawString(0.100f, 0.50f, "THE LIGHTWEIGHT JAVA GAMES LIBRARY", new org.newdawn.slick.Color(255, 0, 0));

            listenInput();

            if(LIMIT_FPS) Display.sync(SYNC_WITH_FPS);

            Thread.yield();

            passedTime = System.nanoTime();
            deltaTime = (passedTime - currentTime) / 1_000_000_000;
            fps = 1 / deltaTime;

            Display.setTitle(TITLE + " (fps: " + getFps() +")");
            Display.update(true);
        }

        dispose();
    }

    private void listenInput() {
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) dispose();

        if(Display.wasResized()){
            windowResize(Display.getWidth(), Display.getHeight());
        }
    }

    private boolean isOpen(){
        return !Display.isCloseRequested();
    }

    public int[] getSize(){
        return new int[]{Display.getWidth(), Display.getHeight()};
    }

    private void initGLSettings(){
        glEnable(GL11.GL_DEPTH_TEST);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_CULL_FACE);
        glClearDepth(1);

        glLoadIdentity();
        glMatrixMode(GL_PROJECTION);
        glFrustum(-1,1,-1,1,2,8);

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        glEnable(GL_LIGHTING);
        glEnable(GL_LIGHT0);
        glEnable(GL_COLOR_MATERIAL);

        GL11.glClearColor(BACKGROUND_COLOR[0], BACKGROUND_COLOR[1], BACKGROUND_COLOR[2], BACKGROUND_COLOR[3]);
    }

    public float getDeltaTime() {
        return deltaTime;
    }

    public float getFps() {
        return AdvancedMath.roundNumber(fps, 2);
    }

    public int getWidth(){
        return getSize()[0];
    }

    public int getHeight(){
        return getSize()[1];
    }

    private void windowResize(int x, int y){
        glViewport(0,0,x,y);
        float ratioXtoY = ((float) x / y);
        float size = 0.1f;
        glLoadIdentity();
        glFrustum(-ratioXtoY*size, ratioXtoY * size, -size, size, size * 2, 100);
    }

    private void setupCamera() {
        GL11.glMatrixMode(GL_PROJECTION);
        GL11.glLoadIdentity();

        glRotatef(player.getRotationY(), 0,1,0);
        glRotatef(player.getRotationX(), 1,0,0);
        glTranslatef(player.getX(),player.getY(),player.getZ());

        GLU.gluPerspective(70.0F, (float) getWidth() / getHeight(), 0.05F, 1000.0F);
        GL11.glMatrixMode(GL_MODELVIEW);
    }
}
