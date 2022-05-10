package bll;

import be.Categories.HealthCondition;
import be.Citizen;
import be.Usertypes.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public interface IBLLManager {

    User login(String username, String password) throws SQLServerException;

    ArrayList<Citizen> getAllCitizens(int userID);

    void updateCitizenGeneralInfo(Citizen selectedPatient);

    boolean saveHealthCondition(HealthCondition healthCondition, int citizenID);

}
