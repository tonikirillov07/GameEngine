package player;


import engine.Constants;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import static org.lwjgl.util.glu.GLU.gluLookAt;


public class Player extends Constants {
    private float x, y, z, rotationX, rotationY, rotationZ, speed = 10f;
    private byte direction = 0;

    public void move(float deltaTime){
        direction = isKeyDown(Keyboard.KEY_W) ? MOVE_FORWARD: isKeyDown(Keyboard.KEY_S) ?
                MOVE_BACKWARD: isKeyDown(Keyboard.KEY_A) ? MOVE_LEFT: isKeyDown(Keyboard.KEY_D) ? MOVE_RIGHT: DONT_MOVE;

        switch (direction){
            case MOVE_FORWARD -> x+=0.02f;
            case MOVE_BACKWARD -> x-=0.02f;
            case MOVE_LEFT -> z+=0.02f;
            case MOVE_RIGHT -> z-=0.02f;
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
            rotationY+=0.02f;
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
            rotationY-=0.02f;
        }

        rotationY += Mouse.getDY() * SENSIVITY * deltaTime;
        rotationX += Mouse.getDX() * SENSIVITY * deltaTime;
    }

    private boolean isKeyDown(int keyCode){
        return Keyboard.isKeyDown(keyCode);
    }

    public float getRotationZ() {
        return rotationZ;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getRotationX() {
        return rotationX;
    }

    public float getRotationY() {
        return rotationY;
    }
}
