package dal;

import be.Usertypes.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.dao.UserDAO;

import java.io.IOException;

public class DALManager implements IDALManager{
    UserDAO userDAO;

    public DALManager() throws IOException {
        userDAO = new UserDAO();
    }

    @Override
    public User login(String username, String password) throws SQLServerException {
        return userDAO.checkLogin(username, password);
    }
}
