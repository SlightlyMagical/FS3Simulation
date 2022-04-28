package gui.controller;

import gui.model.DialogHandler;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;

    private final static String INVALID_LOGIN = "Forkert brugernavn eller kodeord";

    @FXML
    private void handleLogin() {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        if(username.isEmpty() || password.isEmpty()) {
            DialogHandler.informationAlert(INVALID_LOGIN);
            return;
        }



    }

}
