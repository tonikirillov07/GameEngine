package engine;

import UI.UIElement;
import UI.UIRenderer;
import gameWorld.Environment;
import gameWorld.Level;
import models.Cube;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import player.Camera;
import player.Player;

import java.awt.*;
import java.util.Objects;

import static org.lwjgl.opengl.GL11.*;

public class Window extends Constants {
    private Render render;
    private float currentTime, passedTime, deltaTime, fps;
    private Level level;
    private TrueTypeFont trueTypeFont;
    private Player player;
    private Camera camera;
    private Environment environment;
    private UIRenderer uiRenderer;
    private UIElement targetTexture;
    private Cube cube;

    public void run(){
        try {
            Display.setDisplayMode(new DisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT));
            Display.setTitle(TITLE);
            Display.setResizable(IS_RESIZABLE);
            Display.setVSyncEnabled(USE_VSYNC);
            Display.create();

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

            level = new Level(render);
            //level.createLevel(16, 16, 2, 0.2f);

            cube = new Cube(0.4f, TextureUtil.createTextureId("textures/dirt.png", TextureUtil.LINEAR), new Vector3f(0,0,0), RotationUtil.ZERO, WHITE);
            render.loadModel(cube);

            camera = new Camera(CAMERA_DEFAULT_POSITION, new Vector3f(0,0,0), this);
            player = new Player(camera);

            environment = new Environment();
            environment.enableFog(true);
            environment.initFogDefault();

            uiRenderer = new UIRenderer(this);
            targetTexture = new UIElement(Objects.requireNonNull(TextureUtil.createTexture("textures/target.png", TextureUtil.LINEAR)), new Vector2f(0,0), RotationUtil.ZERO, true);

            uiRenderer.loadUiElement(targetTexture);

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

            camera.updateCamera();
            player.move(getDeltaTime());

            render.renderAll();
            uiRenderer.renderAll();

            //trueTypeFont.drawString(0.100f, 0.50f, "THE LIGHTWEIGHT JAVA GAMES LIBRARY", new org.newdawn.slick.Color(255, 0, 0));

            listenInput();

            Display.setTitle(TITLE + " (fps: " + getFps() +"). Position: (" + player.getX() + ", " + player.getY() + ", " + player.getZ() + ")");
            Display.update(true);
            if(LIMIT_FPS) Display.sync(SYNC_WITH_FPS);

            Thread.yield();
            calculateDeltaTimeAndFps();
        }

        dispose();
    }

    private void calculateDeltaTimeAndFps() {
        passedTime = System.nanoTime();
        deltaTime = (passedTime - currentTime) / 1_000_000_000;
        fps = 1 / deltaTime;
    }

    private void listenInput() {
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) dispose();

        if(Keyboard.isKeyDown(Keyboard.KEY_P)){
            System.out.println(camera.getPosition());
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_R)){
            player.setRotationX(0);
            player.setRotationY(0);

            camera.move(CAMERA_DEFAULT_POSITION);

            player.setX(CAMERA_DEFAULT_POSITION.getX());
            player.setY(CAMERA_DEFAULT_POSITION.getY());
            player.setZ(CAMERA_DEFAULT_POSITION.getZ());
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
        glClearDepth(1);

        GL11.glLoadIdentity();
        GL11.glOrtho(0, getWidth(), 0, getHeight(), 1, -1);
        glFrustum(-1, 1, -1, 1, 2, 800);

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        glEnable(GL_LIGHTING);
        glEnable(GL_LIGHT0);
        glEnable(GL_COLOR_MATERIAL);

        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);

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
        if (ratioXtoY > 1) {
            size *= ratioXtoY;
        }

        glLoadIdentity();
        glFrustum(-ratioXtoY * size, ratioXtoY * size, -size, size, size * 2, 800);
    }
}
