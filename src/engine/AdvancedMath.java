package engine;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
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
