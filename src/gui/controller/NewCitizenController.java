package gui.controller;

import be.Citizen;
import gui.model.ModelManager;
import gui.model.util.DialogHandler;
import gui.model.util.Messages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class NewCitizenController {
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;

    public void handleCancel(ActionEvent actionEvent) {
        ((Stage)txtFirstName.getScene().getWindow()).close();
    }

    public void handleCreate(ActionEvent actionEvent) {
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        if (!firstName.isBlank() && !lastName.isBlank()){
            try{
                Citizen citizen = new Citizen(-1, firstName, lastName);
                ModelManager.getInstance().getTeacherModel().createNewCitizen(citizen);
                ((Stage)txtFirstName.getScene().getWindow()).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            DialogHandler.informationAlert(Messages.EMPTY_FIELDS);
    }
}
