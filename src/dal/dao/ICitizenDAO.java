package dal.dao;

import be.Citizen;
import be.Usertypes.User;

import java.util.List;

public interface ICitizenDAO {

    List<Citizen> getCitizens(User user);

    List<Citizen> getGeneralInfo(List<Citizen> citizens);

    List<Citizen> getHealthConditions(List<Citizen> citizens);

    List<Citizen> getFunctionalAbilities(List<Citizen> citizens);


}
