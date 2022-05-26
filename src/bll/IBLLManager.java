package bll;

import be.Categories.FunctionalAbility;
import be.Categories.HealthCondition;
import be.Citizen;
import be.School;
import be.Usertypes.Admin;
import be.Usertypes.Student;
import be.Usertypes.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.util.ArrayList;

public interface IBLLManager {

    /**
     * Passes the login credentials to the data layer.
     * Returns the user associated with the login if one exists
     */
    User login(String username, String password) throws SQLServerException;

    /**
     * Returns a list of all citizens of the given user from the data layer
     */
    ArrayList<Citizen> getAllCitizens(int userID);

    /**
     * Returns a list of all citizens of the given school from the data layer
     */
    ArrayList<Citizen> getCitizensOfSchool(int schoolID);

    /**
     * Returns a list of all students of the given school from the data layer
     */
    ArrayList<Student> getAllStudents(int schoolID);


    /**
     * Passes the citizen with updated "General info" to the data layer
     */
    void updateCitizenGeneralInfo(Citizen selectedPatient);

    /**
     * Passes a list of health conditions to be saved in the database to the data layer
     * @return true if saved correctly
     */
    boolean saveHealthConditions(ArrayList<HealthCondition> healthConditions, int citizenID);

    /**
     * Passes a list of functional abilities to be saved in the database to the data layer
     * @return true if saved correctly
     */
    boolean saveFunctionalAbilities(ArrayList<FunctionalAbility> functionalAbilities, int citizenID);


    /**
     * Passes a new citizen to be created in the database to the data layer
     */
    void createNewCitizen(Citizen citizen, int schoolID);

    /**
     * Passes a citizen to be copied to the data layer.
     * @Return the copy of the citizen
     */
    Citizen createCitizenCopy(Citizen citizen, boolean isTemplate, int teacherID);

    /**
     * Passes a citizen to the data layer to update their name in the database
     */
    void changeCitizenName(Citizen citizen);

    /**
     * Passes a citizen to the data layer to update the students assigned to it
     */
    void changeAssignedStudents(Citizen citizen);

    /**
     * Passes a citizen to the data layer to be deleted
     */
    void deleteCitizen(Citizen citizen);


    /**
     * Passes a new user and login credentials to be created in the database.
     * @Return true if created successfully
     */
    boolean createUser(User user, String password);

    /**
     * Passes the ID of a user to be deleted to the database
     */
    void deleteUser(int userID);

    /**
     * Returns a list of all admins from the data layer
     */
    ArrayList<Admin> getAdmins();


    /**
     * Passes a new school to the data layer to be created in the database
     */
    boolean createSchool(School school);

    /**
     * Passes the ID of a school to be deleted to the database
     */
    void deleteSchool(int schoolID);

    /**
     * Returns a list of all school from the data layer
     */
    ArrayList<School> getSchools();

}
