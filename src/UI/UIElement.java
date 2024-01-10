package UI;

import engine.RotationUtil;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

public class UIElement {
    private final Texture texture;
    private final int textureId;
    private final Vector2f position;
    private final RotationUtil rotationUtil;
    private final boolean renderInCenter;

    public UIElement(Texture texture, Vector2f position, RotationUtil rotationUtil, boolean renderInCenter) {
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

    public Vector2f getPosition() {
        return position;
    }

    public RotationUtil getRotationUtil() {
        return rotationUtil;
    }

    public Texture getTexture() {
        return texture;
    }
}
