package bll;

import be.Categories.FunctionalAbility;
import be.Categories.HealthCondition;
import be.Citizen;
import be.School;
import be.Usertypes.Admin;
import be.Usertypes.Student;
import be.Usertypes.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.DALManager;
import dal.IDALManager;

import java.io.IOException;
import java.util.ArrayList;

public class BLLManager implements IBLLManager{
    IDALManager dalManager;
    public BLLManager() throws IOException {
        dalManager = new DALManager();
    }

    @Override
    public User login(String username, String password) throws SQLServerException {
        return dalManager.login(username, password);
    }

    @Override
    public ArrayList<Citizen> getAllCitizens(int userID) {
        return dalManager.getAllCitizens(userID);
    }

    @Override
    public void updateCitizenGeneralInfo(Citizen selectedPatient) {
        dalManager.updatePatientGeneralInfo(selectedPatient);
    }

    @Override
    public boolean saveHealthConditions(ArrayList<HealthCondition> healthConditions, int citizenID){
        return dalManager.saveHealthConditions(healthConditions, citizenID);
    }

    @Override
    public boolean saveFunctionalAbilities(ArrayList<FunctionalAbility> functionalAbilities, int citizenID) {
        return dalManager.saveFunctionalAbilities(functionalAbilities, citizenID);
    }

    @Override
    public ArrayList<Citizen> getCitizensOfSchool(int schoolID) {
        return dalManager.getCitizensOfSchool(schoolID);
    }

    @Override
    public ArrayList<Student> getAllStudents(int schoolID) {
        return dalManager.getAllStudents(schoolID);
    }

    @Override
    public void createNewCitizen(Citizen citizen, int schoolID) {
        dalManager.createNewCitizen(citizen, schoolID);
    }

    @Override
    public Citizen createCitizenCopy(Citizen citizen, boolean isTemplate, int teacherID) {
        return dalManager.createCitizenCopy(citizen, isTemplate, teacherID);
    }

    @Override
    public void changeCitizenName(Citizen citizen) {
        dalManager.changeCitizenName(citizen);
    }

    @Override
    public void changeAssignedStudents(Citizen citizen) {
        dalManager.changeAssignedStudents(citizen);
    }

    @Override
    public void deleteCitizen(Citizen citizen) {
        dalManager.deleteCitizen(citizen);
    }

    @Override
    public boolean createUser(User user, String password) {
        return dalManager.createUser(user, password);
    }

    @Override
    public void deleteUser(int userID) {
        dalManager.deleteUser(userID);
    }

    @Override
    public ArrayList<Admin> getAdmins() {
        return dalManager.getAdmins();
    }

    @Override
    public boolean createSchool(School school) {
        return dalManager.createSchool(school);
    }

    @Override
    public void deleteSchool(int schoolID) {
        dalManager.deleteSchool(schoolID);
    }

    @Override
    public ArrayList<School> getSchools() {
        return dalManager.getSchools();
    }
}
