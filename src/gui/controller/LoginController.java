package gui.controller;

import be.Usertypes.Teacher;
import be.Usertypes.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import gui.SceneManager;
import gui.model.ModelManager;
import gui.model.UserModel;
import gui.model.util.DialogHandler;
import gui.model.util.Messages;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;

    private UserModel userModel;

    public LoginController() throws IOException {
        userModel = ModelManager.getInstance().getUserModel();
    }

    /**
     * Attempts to log in with the given inputs, request which scene to show next from the scene manager.
     * Displays a warning to the user if the login credentials are wrong
     */
    @FXML
    private void handleLogin() throws SQLServerException, IOException {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        if (username.isEmpty() || password.isEmpty()) {
            DialogHandler.informationAlert(Messages.INVALID_LOGIN);
            return;
        }
        User user = userModel.login(username.toLowerCase(), password);
        if (user == null)
            DialogHandler.informationAlert(Messages.INVALID_LOGIN);
        else {
            switch (user.getUserType()) {
                case 1 -> {
                    ModelManager.getInstance().getAdminModel().getSchoolsFromDatabase();
                    SceneManager.showAdminScene();
                }
                case 2 -> {
                    ModelManager.getInstance().getTeacherModel().setCurrentTeacher((Teacher) user);
                    SceneManager.showTeacherScene();
                }
                case 3 -> {
                    ModelManager.getInstance().getCitizenModel().getCitizensFromDatabase();
                    SceneManager.showStudentScene();
                }
            }
        }
    }
}
