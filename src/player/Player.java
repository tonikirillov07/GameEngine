package player;

import engine.Constants;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Player extends Constants {
    private float x, y, z, rotationX, rotationY, speed = 10f;
    private Camera camera;

    public Player(Camera camera) {
        this.camera = camera;

        x = CAMERA_DEFAULT_POSITION.getX();
        y = CAMERA_DEFAULT_POSITION.getY();
        z = CAMERA_DEFAULT_POSITION.getZ();
    }

    public void move(float deltaTime) {
        byte direction = isKeyDown(Keyboard.KEY_W) ? MOVE_FORWARD : isKeyDown(Keyboard.KEY_S) ?
                MOVE_BACKWARD : isKeyDown(Keyboard.KEY_A) ? MOVE_LEFT : isKeyDown(Keyboard.KEY_D) ? MOVE_RIGHT : DONT_MOVE;

        Vector3f moveDirection = new Vector3f();

        switch (direction) {
            case MOVE_FORWARD -> {
                z -= (speed * deltaTime);
                camera.move(new Vector3f(x,y,z));
            }
            case MOVE_BACKWARD -> {
                z += speed * deltaTime;
                camera.move(new Vector3f(x,y,z));
            }
            case MOVE_LEFT -> {
                x -= speed * deltaTime;
                camera.move(new Vector3f(x,y,z));
            }
            case MOVE_RIGHT -> {
                x += speed * deltaTime;
                camera.move(new Vector3f(x,y,z));
            }
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            rotationY += 0.2f;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            rotationY -= 0.2f;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            if(rotationX < ROTATE_X_LIMIT_ANGLE) rotationX += 0.2f * SENSIVITY; else rotationX = ROTATE_X_LIMIT_ANGLE;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            if(rotationX > -ROTATE_X_LIMIT_ANGLE) rotationX -= 0.2f * SENSIVITY; else rotationX = -ROTATE_X_LIMIT_ANGLE;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
            y += speed * deltaTime;
            camera.move(new Vector3f(x,y,z));
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
            y -= speed * deltaTime;
            camera.move(new Vector3f(x,y,z));
        }

        float dx = Mouse.getDX();
        float dy = Mouse.getDY();

        camera.rotateY(rotationY);
        camera.rotateX(rotationX);

        /*
        camera.rotateX(dx * SENSIVITY);
        camera.rotateY(dy * SENSIVITY);

         */
    }

    private boolean isKeyDown(int keyCode) {
        return Keyboard.isKeyDown(keyCode);
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

    public void setRotationX(float rotationX) {
        this.rotationX = rotationX;
    }

    public void setRotationY(float rotationY) {
        this.rotationY = rotationY;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }
}
