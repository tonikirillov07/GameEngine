package player;

import engine.AdvancedMath;
import engine.Constants;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector;
import org.lwjgl.util.vector.Vector3f;

public class FreeCamera extends Constants {
    private float rotationX;
    private float rotationY;
    private final Camera camera;
    private final float speed = 5f;
    private float deltaTime;

    public FreeCamera(@NotNull Camera camera) {
        this.camera = camera;

        camera.setPosition(CAMERA_DEFAULT_POSITION);
    }

    public void move(float deltaTime) {
        byte direction = isKeyDown(Keyboard.KEY_W) ? MOVE_FORWARD : isKeyDown(Keyboard.KEY_S) ?
                MOVE_BACKWARD : isKeyDown(Keyboard.KEY_A) ? MOVE_LEFT : isKeyDown(Keyboard.KEY_D) ? MOVE_RIGHT : DONT_MOVE;

        this.deltaTime = deltaTime;

        switch (direction) {
            case MOVE_FORWARD -> moveForwardOrBackward((byte) 1);
            case MOVE_BACKWARD -> moveForwardOrBackward((byte) -1);
            case MOVE_LEFT -> moveLeftOrRight((byte) 1);
            case MOVE_RIGHT -> moveLeftOrRight((byte) -1);
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
            camera.translate(new Vector3f(0,-speed * deltaTime, 0));
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
            camera.translate(new Vector3f(0, speed * deltaTime, 0));
        }

        float dx = Mouse.getDX();
        float dy = Mouse.getDY();

        rotationY += dy * SENSIVITY * deltaTime;
        rotationX += dx * -SENSIVITY * deltaTime;

        if(rotationY < -ROTATE_Y_LIMIT_ANGLE) rotationY = -ROTATE_Y_LIMIT_ANGLE; else if(rotationY > ROTATE_Y_LIMIT_ANGLE) rotationY = ROTATE_Y_LIMIT_ANGLE;

        camera.rotateY(rotationX);
        camera.rotateX(rotationY);
    }

    private void moveForwardOrBackward(byte direction){
        camera.translate(new Vector3f(0,0, direction * speed * deltaTime));
    }

    private void moveLeftOrRight(byte direction){
        camera.translate(new Vector3f(direction * speed * deltaTime,0,0));
    }

    private boolean isKeyDown(int keyCode) {
        return Keyboard.isKeyDown(keyCode);
    }

    public void setRotationX(float rotationX) {
        this.rotationX = rotationX;
    }

    public void setRotationY(float rotationY) {
        this.rotationY = rotationY;
    }

    public Vector3f getPosition(){
        return camera.getPosition();
    }

    public void resetTransform(){
        setRotationX(0);
        setRotationY(0);

        camera.setPosition(Constants.CAMERA_DEFAULT_POSITION);
        camera.translate(Constants.CAMERA_DEFAULT_POSITION);

    }
}

