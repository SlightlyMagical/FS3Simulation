package bll;

import be.Usertypes.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.DALManager;
import dal.IDALManager;

import java.io.IOException;

public class BLLManager implements IBLLManager{
    IDALManager dalManager;
    public BLLManager() throws IOException {
        dalManager = new DALManager();
    }

    @Override
    public User login(String username, String password) throws SQLServerException {
        //Insert password hashing
        return dalManager.login(username, password);
    }
}
