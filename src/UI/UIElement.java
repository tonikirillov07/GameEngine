package UI;

import engine.RotationUtil;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

public class UIElement {
    private Texture texture;
    private int textureId;
    private Vector2f position;
    private RotationUtil rotationUtil;
    private boolean renderInCenter = true;

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
