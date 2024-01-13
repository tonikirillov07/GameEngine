package engine;

import models.IModels;

import java.util.ArrayList;
import java.util.List;

public class Render {
    private final List<IModels> models = new ArrayList<>();

    public void loadModel(IModels model){
        models.add(model);

        Logs.getLogger().info("Loaded model " + model.toString());
    }

    public void renderAll(){
        for(IModels iModel : models){
            renderOne(iModel);
        }
    }

    public void renderOne(@org.jetbrains.annotations.NotNull IModels model){
        model.render();
    }

    public List<IModels> getModels() {
        return models;
    }
}
