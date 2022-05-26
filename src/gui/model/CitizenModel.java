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

    /**
     * Passes the citizen whose general info is being updated to the logic layer
     */
    public void updatePatientGeneralInfo(Citizen selectedPatient) {
        bllManager.updateCitizenGeneralInfo(selectedPatient);
    }

    /**
     * Requests all citizens of the curren user from the database
     */
    public void getCitizensFromDatabase() throws IOException {
        citizens.clear();
        citizens.addAll(bllManager.getAllCitizens(ModelManager.getInstance().getUserModel().currentUser.getId()));
    }

    /**
     * Passes a list of health conditions to be saved and the ID of the citizen to the logic layer
     */
    public boolean saveHealthConditions(ArrayList<HealthCondition> healthConditions){
        return bllManager.saveHealthConditions(healthConditions, currentCitizen.getId());
    }

    /**
     * Passes a list of functional abilities to be saved and the ID of the citizen to the logic layer
     */
    public boolean saveFunctionalAbilities(ArrayList<FunctionalAbility> functionalAbilities) {
        return bllManager.saveFunctionalAbilities(functionalAbilities, currentCitizen.getId());
    }

    public ObservableList<HealthCondition> getNotRelevantHealth() {
        return notRelevantHealth;
    }

    public ObservableList<FunctionalAbility> getNotRelevantFunctions() {
        return notRelevantFunctions;
    }

    /**
     * Passes the citizen whose name is being updated to the logic layer
     */
    public void changeCitizenName(Citizen citizen){
        bllManager.changeCitizenName(citizen);
    }

    /**
     * Passes the citizen whose assigned students is being updated to the logic layer
     */
    public void changeAssignedStudents(){
        bllManager.changeAssignedStudents(currentCitizen);
    }

    /**
     * Passes the citizen to be deleted to the logic layer
     */
    public void deleteCitizen(Citizen citizen){
        bllManager.deleteCitizen(citizen);
    }
}

