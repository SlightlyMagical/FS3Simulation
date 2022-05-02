package bll;

import be.Usertypes.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public interface IBLLManager {

    User login(String username, String password) throws SQLServerException;
}
