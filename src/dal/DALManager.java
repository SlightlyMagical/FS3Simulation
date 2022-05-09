package dal;

import be.Citizen;
import be.Usertypes.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.dao.CitizenDAO;
import dal.dao.ICitizenDAO;
import dal.dao.IUserDAO;
import dal.dao.UserDAO;

import java.io.IOException;
import java.util.ArrayList;

public class DALManager implements IDALManager{
    IUserDAO userDAO;
    ICitizenDAO citizenDAO;
    public DALManager() throws IOException {
        userDAO = new UserDAO();
        citizenDAO = new CitizenDAO();
    }

    @Override
    public User login(String username, String password) throws SQLServerException {
        return userDAO.checkLogin(username, password);
    }

    @Override
    public ArrayList<Citizen> getAllCitizens(int userID) {
        ArrayList<Citizen> citizens = citizenDAO.getCitizens(userID);
        citizens = citizenDAO.getGeneralInfo(citizens);
        citizens = citizenDAO.getHealthConditions(citizens);
        citizens = citizenDAO.getFunctionalAbilities(citizens);
        return citizens;
    }


    @Override
    public void updatePatientGeneralInfo(Citizen selectedPatient) {
        citizenDAO.updatePatientGeneralInfo(selectedPatient);
    }
}
