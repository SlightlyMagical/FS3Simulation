package dal.dao;

import be.Categories.HealthCondition;
import be.Citizen;

import be.Usertypes.User;

import java.util.ArrayList;
import java.util.List;

public interface ICitizenDAO {

    ArrayList<Citizen> getCitizens(int userID);

    ArrayList<Citizen> getGeneralInfo(ArrayList<Citizen> citizens);

    ArrayList<Citizen> getHealthConditions(ArrayList<Citizen> citizens);

    ArrayList<Citizen> getFunctionalAbilities(ArrayList<Citizen> citizens);

    void updatePatientGeneralInfo(Citizen selectedPatient);

    boolean saveHealthCondition(HealthCondition healthCondition, int citizenID);
}
