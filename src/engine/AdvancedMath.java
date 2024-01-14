package engine;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector3f;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.text.DecimalFormat;

public abstract class AdvancedMath {
    public static float roundNumber(float number, int accuracy){
        try {
            DecimalFormat decimalFormat = new DecimalFormat("##." + "#".repeat(Math.max(0, accuracy)));

            return Float.parseFloat(decimalFormat.format(number));
        }catch (NumberFormatException e){
            return 0;
        }
    }

    public static @NotNull FloatBuffer createFloatBuffer(float @NotNull [] array) {
        FloatBuffer buffer = ByteBuffer.allocateDirect(array.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        buffer.put(array).flip();
        return buffer;
    }

    public static @NotNull FloatBuffer reserveData(int length) {
        return BufferUtils.createFloatBuffer(length);
    }

    public static float distance(@NotNull Vector3f vector1, @NotNull Vector3f vector2){
        float x1 = vector1.getX();
        float y1 = vector1.getY();
        float z1 = vector1.getZ();

        float x2 = vector2.getX();
        float y2 = vector2.getY();
        float z2 = vector2.getZ();

        return (float) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1, 2));
    }

    @Contract("_, _ -> new")
    public static @NotNull Vector3f getDirection(float pitch, float yaw){
        double pitchRadians = Math.toRadians(pitch);
        double yawRadians = Math.toRadians(yaw);

        double xDirection = Math.cos(pitchRadians) * Math.sin(yawRadians);
        double yDirection = Math.sin(pitchRadians);
        double zDirection = Math.cos(pitchRadians) * Math.cos(yawRadians);

        return new Vector3f((float) xDirection, (float) yDirection, (float) zDirection);
    }

}
