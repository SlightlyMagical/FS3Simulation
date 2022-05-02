package gui.model;

import java.io.IOException;

public class ModelManager {
    private static ModelManager instance;
    private final UserModel userModel;

    public ModelManager() throws IOException {
        userModel = new UserModel();
    }

    public static ModelManager getInstance() throws IOException {
        if (instance == null)
            instance = new ModelManager();
        return instance;
    }

    public UserModel getUserModel() {
        return userModel;
    }
}
