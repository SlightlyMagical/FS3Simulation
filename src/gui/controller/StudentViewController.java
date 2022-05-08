package gui.controller;

import be.Citizen;
import gui.SceneManager;
import gui.model.CitizenModel;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentViewController implements Initializable {
    public TableView<Citizen> patientTableView;
    public TableColumn<Citizen, String> patientsName;
    public TableColumn<Citizen, String> patientLastName;
    private CitizenModel citizenModel = CitizenModel.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        patientTableView.setItems(citizenModel.getAllPatients(1));
        patientsName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        patientLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    }

    public void selectPatient(MouseEvent mouseEvent) {
        if(patientTableView.getSelectionModel().getSelectedItem() != null) {
            SceneManager.showCitizenOverview(patientTableView.getSelectionModel().getSelectedItem());
        }
    }
}
