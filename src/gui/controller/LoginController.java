package gui.controller;

import be.Usertypes.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import gui.SceneManager;
import gui.model.DialogHandler;
import gui.model.Messages;
import gui.model.ModelManager;
import gui.model.UserModel;
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

    @FXML
    private void handleLogin() {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        if(username.isEmpty() || password.isEmpty()) {
            DialogHandler.informationAlert(Messages.INVALID_LOGIN);
            return;
        }
        try{
            User user = userModel.login(username, password);
            if (user == null)
                DialogHandler.informationAlert(Messages.INVALID_LOGIN);
            else{
                switch (user.getUserType()){
                    case 1 -> System.out.println("admin login");
                    case 2 -> System.out.println("teacher login");
                    case 3 -> {
                        ModelManager.getInstance().getCitizenModel().getCitizensFromDatabase();
                        SceneManager.showStudentScene();
                    }
                }
            }
        } catch (SQLServerException | IOException ignored) {
        }


    }

}
