package dal.dao;

import be.Categories.FunctionalAbility;
import be.Categories.HealthCondition;
import be.Citizen;

import java.util.ArrayList;

public interface ICitizenDAO {

    ArrayList<Citizen> getCitizens(int userID);

    ArrayList<Citizen> getGeneralInfo(ArrayList<Citizen> citizens);

    ArrayList<Citizen> getHealthConditions(ArrayList<Citizen> citizens);

    ArrayList<Citizen> getFunctionalAbilities(ArrayList<Citizen> citizens);

    void updatePatientGeneralInfo(Citizen selectedPatient);

    boolean saveHealthConditions(ArrayList<HealthCondition> healthConditions, int citizenID);

    boolean saveFunctionalAbilities(ArrayList<FunctionalAbility> functionalAbilities, int citizenID);

    ArrayList<Citizen> getCitizensOfSchool(int schoolID);

    void createNewCitizen(Citizen citizen, int schoolID);

    Citizen createCitizenCopy(Citizen citizen, boolean isTemplate, int teacherID);

    void copyGeneralInfo(int originalID, int newID);

    void copyHealthConditions(int originalID, int newID);

    void copyFunctionalAbilities(int originalID, int newID);

    void changeCitizenName(Citizen citizen);

    void deleteCitizen(Citizen citizen);

    void changeAssignedStudents(Citizen citizen);
}
