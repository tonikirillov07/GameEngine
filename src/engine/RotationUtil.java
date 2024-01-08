package engine;

public class RotationUtil {
    private float angle, x, y, z;
    public static final RotationUtil ZERO  = new RotationUtil(0,0,0,0);

    public RotationUtil(float angle, float x, float y, float z) {
        this.angle = angle;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getAngle() {
        return angle;
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
}
