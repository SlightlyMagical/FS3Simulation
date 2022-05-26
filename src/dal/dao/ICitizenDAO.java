package dal.dao;

import be.Categories.FunctionalAbility;
import be.Categories.HealthCondition;
import be.Citizen;

import java.util.ArrayList;

public interface ICitizenDAO {

    /**
     * Requests all citizens of the user with the given ID from the database.
     * @return a list of citizens
     */
    ArrayList<Citizen> getCitizens(int userID);

    /**
     * Requests all "general info" of each citizen of the provided list and saves it to the citizen
     */
    void getGeneralInfo(ArrayList<Citizen> citizens);

    /**
     * Requests all "health conditions" of each citizen of the provided list and saves it to the citizen
     */
    void getHealthConditions(ArrayList<Citizen> citizens);

    /**
     * Requests all "functional abilities" of each citizen of the provided list and saves it to the citizen
     */
    void getFunctionalAbilities(ArrayList<Citizen> citizens);

    /**
     * Updates the saved "general info" of the citizen in the database
     */
    void updatePatientGeneralInfo(Citizen selectedPatient);

    /**
     * Saves all "health conditions" of the provided list to the database
     * @return true if saved correctly
     */
    boolean saveHealthConditions(ArrayList<HealthCondition> healthConditions, int citizenID);

    /**
     * Saves all "functional abilities" of the provided list to the database
     * @return true if saved correctly
     */
    boolean saveFunctionalAbilities(ArrayList<FunctionalAbility> functionalAbilities, int citizenID);

    /**
     * Requests all citizens connected to the school with the given ID
     * @return a list of the citizens
     */
    ArrayList<Citizen> getCitizensOfSchool(int schoolID);

    /**
     * Creates a new citizen in the database
     */
    void createNewCitizen(Citizen citizen, int schoolID);

    /**
     * Creates a copy of the given citizen in the database
     * @return the copy of the citizen
     */
    Citizen createCitizenCopy(Citizen citizen, boolean isTemplate, int teacherID);

    /**
     * Copies the general info of the original citizen to the new citizen in the database
     */
    void copyGeneralInfo(int originalID, int newID);

    /**
     * Copies the health conditions of the original citizen to the new citizen in the database
     */
    void copyHealthConditions(int originalID, int newID);

    /**
     * Copies the functional abilities of the original citizen to the new citizen in the database
     */
    void copyFunctionalAbilities(int originalID, int newID);

    /**
     * Updates the name of the given citizen in the database
     */
    void changeCitizenName(Citizen citizen);

    /**
     * Deletes the given citizen in the database
     */
    void deleteCitizen(Citizen citizen);

    /**
     * Changes in the database which students are assigned to the given citizen
     */
    void changeAssignedStudents(Citizen citizen);
}
