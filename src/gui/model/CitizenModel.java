package gui.model;

import be.Categories.FunctionalAbility;
import be.Categories.HealthCondition;
import be.Citizen;
import bll.BLLManager;
import bll.IBLLManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class CitizenModel {
    IBLLManager bllManager;

    private ObservableList<Citizen> citizens;
    private ObservableList<HealthCondition> notRelevantHealth;
    private ObservableList<FunctionalAbility> notRelevantFunctions;

    private Citizen currentCitizen;

    public CitizenModel() throws IOException {
        bllManager = new BLLManager();
        citizens = FXCollections.observableArrayList();
        notRelevantHealth = FXCollections.observableArrayList();
        notRelevantFunctions = FXCollections.observableArrayList();
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
        citizens.clear();
        citizens.addAll(bllManager.getAllCitizens(ModelManager.getInstance().getUserModel().currentUser.getId()));
    }

    public boolean saveHealthCondition(HealthCondition healthCondition) {
        if (bllManager.saveHealthCondition(healthCondition, currentCitizen.getId())){
            currentCitizen.addHealthCondition(healthCondition);
            return true;
        }
        return false;
    }

    public boolean saveFunctionalAbility(FunctionalAbility functionalAbility) {
        if (bllManager.saveFunctionalAbility(functionalAbility, currentCitizen.getId())){
            currentCitizen.addFunctionalAbility(functionalAbility);
            return true;
        }
        return false;
    }

    public ObservableList<HealthCondition> getNotRelevantHealth() {
        return notRelevantHealth;
    }

    public ObservableList<FunctionalAbility> getNotRelevantFunctions() {
        return notRelevantFunctions;
    }

}

