package GUI;

import Model.Model;

/**
 * Created by Гога on 19.04.2016.
 */
public class Association {
    private String name;
    private Model model;

    public Association(String name, Model model) {
        this.name = name;
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
