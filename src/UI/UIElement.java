package UI;

import engine.RotationUtil;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

public class UIElement {
    private final Texture texture;
    private final int textureId;
    private final Vector3f position;
    private final RotationUtil rotationUtil;
    private final boolean renderInCenter;

    public UIElement(Texture texture, Vector3f position, RotationUtil rotationUtil, boolean renderInCenter) {
        this.texture = texture;
        this.position = position;
        this.rotationUtil = rotationUtil;
        this.renderInCenter = renderInCenter;

        textureId = texture.getTextureID();
    }

    public int getTextureId() {
        return textureId;
    }

    public boolean isRenderInCenter() {
        return renderInCenter;
    }

    public Vector3f getPosition() {
        return position;
    }

    public RotationUtil getRotationUtil() {
        return rotationUtil;
    }

    public Texture getTexture() {
        return texture;
    }
}
