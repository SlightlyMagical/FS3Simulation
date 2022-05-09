package gui.controller;

import be.Citizen;
import gui.SceneManager;
import gui.model.CitizenModel;
import gui.model.ModelManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentController implements Initializable {
    @FXML
    private TableView<Citizen> tvCitizens;
    @FXML
    private TableColumn<Citizen, String> tcFirstName;
    @FXML
    private TableColumn<Citizen, String> tcLastName;

    private CitizenModel citizenModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenModel = ModelManager.getInstance().getCitizenModel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.tcFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        this.tcLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        this.tvCitizens.setItems(citizenModel.getCitizens());

    }

    @FXML
    private void handleLogout() {
        SceneManager.logout();
    }

    @FXML
    private void handleShowDetails() {
        citizenModel.setCurrentCitizen(tvCitizens.getSelectionModel().getSelectedItem());
        SceneManager.showCitizenOverview();
    }


}
