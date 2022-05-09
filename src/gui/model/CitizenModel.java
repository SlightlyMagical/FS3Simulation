package gui.model;

import be.Citizen;
import bll.BLLManager;
import bll.IBLLManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class CitizenModel {
    IBLLManager bllManager;

    private ObservableList<Citizen> citizens;
    private Citizen currentCitizen;

    public CitizenModel() throws IOException {
        citizens = FXCollections.observableArrayList();
        bllManager = new BLLManager();
    }

    public ObservableList<Citizen> getCitizens() {
        return citizens;
    }

    public Citizen getCurrentCitizen() {
        return currentCitizen;
    }

    public void setCurrentCitizen(Citizen currentCitizen) {
        this.currentCitizen = currentCitizen;
    }
    public void updatePatientGeneralInfo(Citizen selectedPatient) {
        bllManager.updateCitizenGeneralInfo(selectedPatient);
    }

    public void getCitizensFromDatabase() throws IOException {
        citizens.addAll(bllManager.getAllCitizens(ModelManager.getInstance().getUserModel().currentUser.getId()));
    }
}

