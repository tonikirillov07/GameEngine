package gameWorld;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

import static engine.AdvancedMath.createFloatBuffer;
import static org.lwjgl.opengl.GL11.*;

public class Environment {
    public void enableFog(boolean enable){
        if(enable) glEnable(GL_FOG); else glDisable(GL_FOG);
    }

    public void initFogDefault(){
        glFogf(GL_FOG_DENSITY, 0.4f);
        glFog(GL_FOG_COLOR, createFloatBuffer(new float[]{1,1,1,1}));
        glFogi(GL11.GL_FOG_MODE, GL_EXP);
    }

    public void setFogColor(Color color){
        glFog(GL_FOG_COLOR, createFloatBuffer(new float[]{color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()}));
    }
}
