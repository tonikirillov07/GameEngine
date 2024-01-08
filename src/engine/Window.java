package engine;

import gameWorld.Level;
import models.Cube;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;
import org.lwjgl.util.vector.Vector3f;
import player.Player;

import static org.lwjgl.opengl.GL11.*;

public class Window extends Constants {
    private Render render;
    private float currentTime, passedTime, deltaTime, fps, cubeAngleTemp;
    private Level level;
    private Player player;
    private Cube cube;

    public void run(){
        try {
            Display.setDisplayMode(new DisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT));
            Display.setTitle(TITLE);
            Display.setResizable(IS_RESIZABLE);
            Display.create();

            checkError();

            Mouse.create();
            Keyboard.create();

            Mouse.setGrabbed(GRAB_MOUSE);

            initGLSettings();
            windowResize(getWidth(), getHeight());

            render = new Render();

            level = new Level(render);
            level.createLevel(15, 15);

            player = new Player();

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

            glPushMatrix();
            player.cameraApply();
            render.renderAll();
            glPopMatrix();

            listenInput();

            Display.update(true);
            if(LIMIT_FPS) Display.sync(SYNC_WITH_FPS);

            Thread.yield();

            passedTime = System.nanoTime();
            deltaTime = (passedTime - currentTime) / 1_000_000_000;
            fps = 1 / deltaTime;

            Display.setTitle(TITLE + " (fps: " + getFps() +")");
        }

        dispose();
    }

    private void listenInput() {
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) dispose();

        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            render.getModels().get(0).rotate(new RotationUtil(30f, 1, 1, 1));
        }

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
        glCullFace(GL_BACK);

        glLoadIdentity();
        glMatrixMode(GL_PROJECTION);
        glFrustum(-1,1,-1,1,2,8);

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        GL11.glClearColor(BACKGROUND_COLOR[0], BACKGROUND_COLOR[1], BACKGROUND_COLOR[2], BACKGROUND_COLOR[3]);
    }

    public float getDeltaTime() {
        return deltaTime;
    }

    public float getFps() {
        return fps;
    }

    public int getWidth(){
        return getSize()[0];
    }

    public int getHeight(){
        return getSize()[1];
    }
    private void windowResize(int x, int y){
        glViewport(0,0,x,y);
        float k = ((float) x / y);
        float size = 0.1f;
        glLoadIdentity();
        glFrustum(-k*size, k * size, -size, size, size * 2, 100);
    }
}
