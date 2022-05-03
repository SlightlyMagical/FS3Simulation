package dal;

import be.Usertypes.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public interface IDALManager {

    User login(String username, String password) throws SQLServerException;
}