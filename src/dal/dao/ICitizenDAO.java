package dal.dao;

import be.Categories.FunctionalAbility;
import be.Categories.HealthCondition;
import be.Citizen;
import be.Usertypes.Teacher;

import java.util.ArrayList;

public interface ICitizenDAO {

    ArrayList<Citizen> getCitizens(int userID);

    ArrayList<Citizen> getGeneralInfo(ArrayList<Citizen> citizens);

    ArrayList<Citizen> getHealthConditions(ArrayList<Citizen> citizens);

    ArrayList<Citizen> getFunctionalAbilities(ArrayList<Citizen> citizens);

    void updatePatientGeneralInfo(Citizen selectedPatient);

    boolean saveHealthCondition(HealthCondition healthCondition, int citizenID);

    boolean saveFunctionalAbility(FunctionalAbility functionalAbility, int citizenID);

    ArrayList<Citizen> getCitizensOfSchool(int schoolID);

    void createNewCitizen(Citizen citizen, int schoolID);
}
