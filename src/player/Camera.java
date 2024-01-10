package player;

import engine.Constants;
import engine.RotationUtil;
import engine.Window;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glRotatef;

public class Camera extends Constants {
    private Vector3f position;
    private Vector3f rotation;
    private Window window;
    private RotationUtil rotationX, rotationY;

    public Camera(Vector3f position, Vector3f rotation, Window window) {
        this.position = position;
        this.rotation = rotation;
        this.window = window;

        rotationX = RotationUtil.ZERO;
        rotationY = RotationUtil.ZERO;
    }

    public void updateCamera() {
        GL11.glMatrixMode(GL_PROJECTION);
        GL11.glLoadIdentity();

        GLU.gluPerspective(70.0F, (float) window.getWidth() / window.getHeight(), 0.05F, 1000.0F);

        glRotatef(-rotationX.getAngle(), 1, 0, 0);
        glRotatef(-rotationY.getAngle(), 0, 1, 0);

        glTranslatef(-position.getX(), -position.getY(), -position.getZ());

        GL11.glMatrixMode(GL_MODELVIEW);
    }

    public void rotateX(float angle){
        rotationX = new RotationUtil(angle, 1,0,0);
    }

    public void rotateY(float angle){
        rotationY = new RotationUtil(angle, 1,0,0);
    }

    public void move(Vector3f position){
        this.position = position;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getRotation() {
        return rotation;
    }
}
