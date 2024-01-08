package engine;

import gameWorld.Level;
import models.Rectangle;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.*;

public class Window extends Constants {
    private Render render;
    private float currentTime, passedTime, deltaTime, fps;
    private Level level;

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

            render = new Render();

            Rectangle rectangle = new Rectangle(0.6f, 0, new Vector3f(0,0,0), RotationUtil.ZERO, new Color(255,0,0));
            render.loadModel(rectangle);

            level = new Level(render);
            level.createLevel(15, 15);

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

            render.renderAll();

            listenKeyboardInput();

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

    private void listenKeyboardInput() {
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) dispose();

        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            render.getModels().get(0).rotate(new RotationUtil(30f, 1, 1, 1));
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
}
