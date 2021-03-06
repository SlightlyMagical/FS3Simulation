package gui.controller;

import be.Citizen;
import gui.model.ModelManager;
import gui.model.util.DialogHandler;
import gui.model.util.Messages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class NewCitizenController {
    @FXML
    private Label lblTitle;
    @FXML
    private Button btnConfirm;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;

    private Citizen citizen;

    /**
     * Closes the current window
     */
    public void handleCancel(ActionEvent actionEvent) {
        ((Stage) txtFirstName.getScene().getWindow()).close();
    }

    /**
     * Creates a new citizen with the given name or updates the name of the current citizen if editing.
     * Then closes the window
     */
    public void handleConfirm(ActionEvent actionEvent) {
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        if (firstName.isBlank() || lastName.isBlank())
            DialogHandler.informationAlert(Messages.EMPTY_FIELDS);
        else {
            if (citizen == null) {
                try {
                    Citizen citizen = new Citizen(-1, firstName, lastName);
                    ModelManager.getInstance().getTeacherModel().createNewCitizen(citizen);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                try {
                    citizen.setFirstName(firstName);
                    citizen.setLastName(lastName);
                    ModelManager.getInstance().getCitizenModel().changeCitizenName(citizen);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            ((Stage) txtFirstName.getScene().getWindow()).close();
        }
    }

    /**
     * Sets whether a new citizen is being created or an existing one is being edited
     */
    public void setEdit(Citizen citizen) {
        if (citizen != null) {
            lblTitle.setText("Indtast nyt navn");
            btnConfirm.setText("Bekr??ft");
            this.citizen = citizen;
        }
    }
}
