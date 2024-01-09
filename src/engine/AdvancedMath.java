package engine;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.text.DecimalFormat;

public class AdvancedMath {
    public static float roundNumber(float number, int accuracy){
        try {
            DecimalFormat decimalFormat = new DecimalFormat("##." + "#".repeat(Math.max(0, accuracy)));

            return Float.parseFloat(decimalFormat.format(number));
        }catch (NumberFormatException e){
            return 0;
        }
    }

    public static FloatBuffer createFloatBuffer(float[] array) {
        FloatBuffer buffer = ByteBuffer.allocateDirect(array.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        buffer.put(array).flip();
        return buffer;
    }
}
