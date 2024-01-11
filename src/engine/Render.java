package engine;

import models.IModels;

import java.util.ArrayList;
import java.util.List;

public class Render {
    private final List<IModels> models = new ArrayList<>();

    public void loadModel(IModels model){
        models.add(model);
    }

    public void renderAll(){
        for(IModels iModel : models){
            iModel.render();
        }
    }

    public void renderOne(IModels model){
        model.render();
    }

    public List<IModels> getModels() {
        return models;
    }
}
