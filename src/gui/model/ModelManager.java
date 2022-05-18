package gui.model;

import java.io.IOException;

public class ModelManager {
    private static ModelManager instance;
    private final UserModel userModel;
    private final CitizenModel citizenModel;
    private final TeacherModel teacherModel;

    public ModelManager() throws IOException {
        this.userModel = new UserModel();
        this.citizenModel = new CitizenModel();
        this.teacherModel = new TeacherModel();
    }

    public static ModelManager getInstance() throws IOException {
        if (instance == null)
            instance = new ModelManager();
        return instance;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public CitizenModel getCitizenModel() {
        return citizenModel;
    }

    public TeacherModel getTeacherModel() {
        return teacherModel;
    }
}
