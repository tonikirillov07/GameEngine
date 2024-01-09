package engine;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.Color;

import java.nio.FloatBuffer;

public abstract class Constants {
    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 800;
    public static final int SYNC_WITH_FPS = 120;
    public static final String TITLE = "Game Engine";
    public static final boolean IS_RESIZABLE = true;
    public static final boolean GRAB_MOUSE = false;
    public static final boolean LIMIT_FPS = true;
    public static final boolean USE_VSYNC = true;
    public static final float[] BACKGROUND_COLOR = new float[]{0.2f, 0.4f, 0.6f, 0f};

    public static final byte MOVE_FORWARD = 1;
    public static final byte MOVE_BACKWARD = -1;
    public static final byte MOVE_LEFT = 2;
    public static final byte MOVE_RIGHT = -2;
    public static final byte DONT_MOVE = 0;
    public static final float SENSIVITY = 0.3f;

    public static final Color WHITE = new Color(255,255,255);
}
