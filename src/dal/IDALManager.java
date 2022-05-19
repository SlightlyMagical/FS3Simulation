package dal;

import be.Categories.FunctionalAbility;
import be.Categories.HealthCondition;
import be.Citizen;
import be.Usertypes.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.util.ArrayList;

public interface IDALManager {
    User login(String username, String password) throws SQLServerException;

    ArrayList<Citizen> getAllCitizens(int userID);

    void updatePatientGeneralInfo(Citizen selectedPatient);

    boolean saveHealthCondition(HealthCondition healthCondition, int citizenID);

    boolean saveFunctionalAbility(FunctionalAbility functionalAbility, int citizenID);

    ArrayList<Citizen> getCitizensOfSchool(int schoolID);

    void createNewCitizen(Citizen citizen, int schoolID);

    Citizen createCitizenCopy(Citizen citizen, boolean isTemplate, int teacherID);

    void changeCitizenName(Citizen citizen);
}
