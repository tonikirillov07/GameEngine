package player;


import engine.Constants;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;

public class Player extends Constants {
    private float x, y, z, rotationX, rotationY, rotationZ, speed = 10f;
    private byte direction = 0;

    public void move(){
        direction = isKeyDown(Keyboard.KEY_W) ? MOVE_FORWARD: isKeyDown(Keyboard.KEY_S) ?
                MOVE_BACKWARD: isKeyDown(Keyboard.KEY_A) ? MOVE_LEFT: isKeyDown(Keyboard.KEY_D) ? MOVE_RIGHT: DONT_MOVE;


    }

    private boolean isKeyDown(int keyCode){
        return Keyboard.isKeyDown(keyCode);
    }

    public void cameraApply(){
        glRotatef(-getRotationX(), 1,0,0);
        glRotatef(-getRotationZ(), 0,0,1);
        glTranslatef(-getX(), -getY(), -getZ());
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
