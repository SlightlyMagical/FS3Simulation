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

    /**
     * Closes the current window
     */
    public void handleCancel(ActionEvent actionEvent) {
        ((Stage)(lblUserType.getScene().getWindow())).close();
    }

    /**
     * Checks if fields are filled out and if passwords matches, warns user if not.
     * Creates a new user with the input information otherwise and passes it to be created in the database.
     * Displays a warning to the user if the username is already taken.
     * Closes the window on a successful creation
     */
    public void handleCreateNew(ActionEvent actionEvent) {
        String username = txtUsername.getText().toLowerCase().trim();
        String password = txtChoosePassword.getText().trim();
        String repeatPassword = txtConfirmPassword.getText().trim();

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

    /**
     * Sets the type of the user being created
     */
    public void setType(int userType){
        this.userType = userType;
        switch (userType){
            case 1 -> lblUserType.setText("admin");
            case 2 -> lblUserType.setText("lærer");
            case 3 -> lblUserType.setText("elev");
        }
    }
}
