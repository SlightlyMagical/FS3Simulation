package gui.model;

import be.Usertypes.User;
import bll.BLLManager;
import bll.IBLLManager;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;

public class UserModel {
    IBLLManager bllManager;

    User currentUser;

    public UserModel() throws IOException {
        bllManager = new BLLManager();
    }

    public User login(String username, String password) throws SQLServerException {
        currentUser = bllManager.login(username, password);
        return currentUser;
    }

    public boolean createUser(User user, String password) {
       return bllManager.createUser(user, password);
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
