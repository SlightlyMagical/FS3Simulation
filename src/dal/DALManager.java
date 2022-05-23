package dal;

import be.Categories.FunctionalAbility;
import be.Categories.HealthCondition;
import be.Citizen;
import be.School;
import be.Usertypes.Admin;
import be.Usertypes.Student;
import be.Usertypes.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.dao.*;

import java.io.IOException;
import java.util.ArrayList;

public class DALManager implements IDALManager{
    IUserDAO userDAO;
    ICitizenDAO citizenDAO;
    ISchoolDAO schoolDAO;


    public DALManager() throws IOException {
        userDAO = new UserDAO();
        citizenDAO = new CitizenDAO();
        schoolDAO = new SchoolDAO();
    }

    @Override
    public User login(String username, String password) throws SQLServerException {
        return userDAO.checkLogin(username, password);
    }

    @Override
    public ArrayList<Citizen> getAllCitizens(int userID) {
        ArrayList<Citizen> citizens = citizenDAO.getCitizens(userID);
        citizenDAO.getGeneralInfo(citizens);
        citizenDAO.getHealthConditions(citizens);
        citizenDAO.getFunctionalAbilities(citizens);
        return citizens;
    }


    @Override
    public void updatePatientGeneralInfo(Citizen selectedPatient) {
        citizenDAO.updatePatientGeneralInfo(selectedPatient);
    }

    @Override
    public boolean saveHealthConditions(ArrayList<HealthCondition> healthConditions, int citizenID){
        return citizenDAO.saveHealthConditions(healthConditions, citizenID);
    }

    @Override
    public boolean saveFunctionalAbilities(ArrayList<FunctionalAbility> functionalAbilities, int citizenID) {
        return citizenDAO.saveFunctionalAbilities(functionalAbilities, citizenID);
    }

    @Override
    public ArrayList<Citizen> getCitizensOfSchool(int schoolID) {
        ArrayList<Citizen> citizens = citizenDAO.getCitizensOfSchool(schoolID);
        citizenDAO.getGeneralInfo(citizens);
        citizenDAO.getHealthConditions(citizens);
        citizenDAO.getFunctionalAbilities(citizens);
        return citizens;
    }

    @Override
    public ArrayList<Student> getAllStudents(int schoolID) {
        return userDAO.getAllStudents(schoolID);
    }

    @Override
    public void createNewCitizen(Citizen citizen, int schoolID) {
        citizenDAO.createNewCitizen(citizen, schoolID);
    }

    @Override
    public Citizen createCitizenCopy(Citizen citizen, boolean isTemplate, int teacherID) {
        Citizen newCitizen = citizenDAO.createCitizenCopy(citizen, isTemplate, teacherID);
        citizenDAO.copyGeneralInfo(citizen.getId(), newCitizen.getId());
        citizenDAO.copyHealthConditions(citizen.getId(), newCitizen.getId());
        citizenDAO.copyFunctionalAbilities(citizen.getId(), newCitizen.getId());

        ArrayList<Citizen> citizens = new ArrayList<>();
        citizens.add(newCitizen);
        citizenDAO.getGeneralInfo(citizens);
        citizenDAO.getHealthConditions(citizens);
        citizenDAO.getFunctionalAbilities(citizens);

        return newCitizen;
    }

    @Override
    public void changeCitizenName(Citizen citizen) {
        citizenDAO.changeCitizenName(citizen);
    }

    @Override
    public void changeAssignedStudents(Citizen citizen) {
        citizenDAO.changeAssignedStudents(citizen);
    }

    @Override
    public void deleteCitizen(Citizen citizen) {
        citizenDAO.deleteCitizen(citizen);
    }

    @Override
    public boolean createUser(User user, String password) {
        return userDAO.createUser(user, password);
    }

    @Override
    public void deleteUser(int userID) {
        userDAO.deleteUser(userID);
    }

    @Override
    public ArrayList<Admin> getAdmins() {
        return userDAO.getAdmins();
    }

    @Override
    public boolean createSchool(School school) {
        return schoolDAO.createSchool(school);
    }

    @Override
    public void deleteSchool(int schoolID) {
        schoolDAO.deleteSchool(schoolID);
    }

    @Override
    public ArrayList<School> getSchools() {
        return schoolDAO.getAllSchools();
    }


}
