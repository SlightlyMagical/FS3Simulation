package dal;

import be.Categories.FunctionalAbility;
import be.Categories.HealthCondition;
import be.Citizen;
import be.School;
import be.Usertypes.Admin;
import be.Usertypes.Student;
import be.Usertypes.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.util.ArrayList;

public interface IDALManager {

    /**
     * Checks if a user with the given login credentials exists in the database
     * @return the user if existing, null if not
     */
    User login(String username, String password) throws SQLServerException;

    /**
     * Requests all citizens (and their information) of the user from the database
     * @return the list of citizens
     */
    ArrayList<Citizen> getAllCitizens(int userID);

    /**
     * Requests all citizens (and their information) of the school from the database
     * @return the list of citizens
     */
    ArrayList<Citizen> getCitizensOfSchool(int schoolID);

    /**
     * Requests all students of the school from the database
     * @return the list of students
     */
    ArrayList<Student> getAllStudents(int schoolID);

    /**
     * Updates the general info of the given citizen in the database
     */
    void updatePatientGeneralInfo(Citizen selectedPatient);

    /**
     * Saves the given list of health conditions to the database
     */
    boolean saveHealthConditions(ArrayList<HealthCondition> healthConditions, int citizenID);

    /**
     * Saves the given list of functional abilities to the database
     */
    boolean saveFunctionalAbilities(ArrayList<FunctionalAbility> functionalAbilities, int citizenID);


    /**
     * Creates the new citizen in the database
     */
    void createNewCitizen(Citizen citizen, int schoolID);

    /**
     * Creates a copy of the given citizen and all their info in the database
     * @return the copy citizen
     */
    Citizen createCitizenCopy(Citizen citizen, boolean isTemplate, int teacherID);

    /**
     * Updates the name of the citizen in the database
     */
    void changeCitizenName(Citizen citizen);

    /**
     * Updates in the database which students are assigned to the given citizen
     */
    void changeAssignedStudents(Citizen citizen);

    /**
     * Deletes the given citizen in the database
     */
    void deleteCitizen(Citizen citizen);


    /**
     * Creates a new use in the database
     * @return true if created correctly, false if the username is already taken
     */
    boolean createUser(User user, String password);

    /**
     * Deletes the user with the given ID from the database
     */
    void deleteUser(int userID);

    /**
     * Requests all admins from the database
     * @return the list of admins
     */
    ArrayList<Admin> getAdmins();


    /**
     * Creates the given school in the database
     * @return false if name is already in use, true if created correctly
     */
    boolean createSchool(School school);

    /**
     * Deletes the school with the given ID from the database
     */
    void deleteSchool(int schoolID);

    /**
     * Requests all schools as well as their teachers and students from the database
     * @return the list of schools
     */
    ArrayList<School> getSchools();
}
