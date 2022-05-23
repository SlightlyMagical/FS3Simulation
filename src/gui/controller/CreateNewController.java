package gui.controller;

import gui.model.AdminModel;
import gui.model.ModelManager;
import gui.model.util.DialogHandler;
import gui.model.util.Messages;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateNewController {
    public TextField txtUsername;
    public PasswordField txtChoosePassword;
    public PasswordField txtConfirmPassword;
    public Label lblUserType;

    private int userType;

    private final AdminModel adminModel;

    public CreateNewController() throws IOException {
        adminModel = ModelManager.getInstance().getAdminModel();
    }

    public void handleCancel(ActionEvent actionEvent) {
        ((Stage)(lblUserType.getScene().getWindow())).close();
    }

    public void handleCreateNew(ActionEvent actionEvent) {
        String username = txtUsername.getText().toLowerCase().trim();
        String password = txtChoosePassword.getText().trim();
        String repeatPassword = txtChoosePassword.getText().trim();

        if (username.isEmpty() || password.isEmpty() || repeatPassword.isEmpty())
            DialogHandler.informationAlert(Messages.EMPTY_FIELDS);
        else if (!password.equals(repeatPassword))
            DialogHandler.informationAlert(Messages.PASSWORD_NOT_MATCHING);
        else {
            if (adminModel.createNewUser(username, password, userType))
                ((Stage) (lblUserType.getScene().getWindow())).close();
            else
                DialogHandler.informationAlert(Messages.USERNAME_TAKEN);
        }
    }

    public void setType(int userType){
        this.userType = userType;
        switch (userType){
            case 1 -> lblUserType.setText("admin");
            case 2 -> lblUserType.setText("lærer");
            case 3 -> lblUserType.setText("elev");
        }
    }
}
