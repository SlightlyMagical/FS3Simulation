package gui.model;

import be.Categories.FunctionalAbility;
import be.Categories.HealthCondition;
import be.Citizen;
import bll.BLLManager;
import bll.IBLLManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;

public class CitizenModel {
    IBLLManager bllManager;

    private final ObservableList<Citizen> citizens;
    private final ObservableList<HealthCondition> notRelevantHealth;
    private final ObservableList<FunctionalAbility> notRelevantFunctions;

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

    public boolean saveHealthConditions(ArrayList<HealthCondition> healthConditions){
        return bllManager.saveHealthConditions(healthConditions, currentCitizen.getId());
    }

    public boolean saveFunctionalAbilities(ArrayList<FunctionalAbility> functionalAbilities) {
        return bllManager.saveFunctionalAbilities(functionalAbilities, currentCitizen.getId());
    }

    public ObservableList<HealthCondition> getNotRelevantHealth() {
        return notRelevantHealth;
    }

    public ObservableList<FunctionalAbility> getNotRelevantFunctions() {
        return notRelevantFunctions;
    }

    public void changeCitizenName(Citizen citizen){
        bllManager.changeCitizenName(citizen);
    }

    public void changeAssignedStudents(){
        bllManager.changeAssignedStudents(currentCitizen);
    }

    public void deleteCitizen(Citizen citizen){
        bllManager.deleteCitizen(citizen);
    }
}

