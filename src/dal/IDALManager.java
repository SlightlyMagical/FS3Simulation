package dal;

import be.Citizen;
import be.Usertypes.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.util.ArrayList;

public interface IDALManager {
    User login(String username, String password) throws SQLServerException;

    ArrayList<Citizen> getAllCitizens(int userID);

    void updatePatientGeneralInfo(Citizen selectedPatient);
}
