package engine;

public record RotationUtil(float angle, float x, float y, float z) {
    public static final RotationUtil ZERO = new RotationUtil(0, 0, 0, 0);

}
