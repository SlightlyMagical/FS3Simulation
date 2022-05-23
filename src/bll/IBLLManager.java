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

    User login(String username, String password) throws SQLServerException;


    ArrayList<Citizen> getAllCitizens(int userID);

    ArrayList<Citizen> getCitizensOfSchool(int schoolID);

    ArrayList<Student> getAllStudents(int schoolID);



    void updateCitizenGeneralInfo(Citizen selectedPatient);

    boolean saveHealthCondition(HealthCondition healthCondition, int citizenID);

    boolean saveFunctionalAbility(FunctionalAbility functionalAbility, int citizenID);



    void createNewCitizen(Citizen citizen, int schoolID);

    Citizen createCitizenCopy(Citizen citizen, boolean isTemplate, int teacherID);

    void changeCitizenName(Citizen citizen);

    void changeAssignedStudents(Citizen citizen);

    void deleteCitizen(Citizen citizen);



    boolean createUser(User user, String password);

    void deleteUser(int userID);

    ArrayList<Admin> getAdmins();



    boolean createSchool(School school);

    void deleteSchool(int schoolID);

    ArrayList<School> getSchools();

}
