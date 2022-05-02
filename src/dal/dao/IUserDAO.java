package dal.dao;

import be.Usertypes.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public interface IUserDAO {
    User checkLogin(String username, String password) throws SQLServerException;
}
