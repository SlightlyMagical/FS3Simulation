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

    /**
     * Passes the login credentials to the logic layer
     * @return the user
     */
    public User login(String username, String password) throws SQLServerException {
        currentUser = bllManager.login(username, password);
        return currentUser;
    }

    /**
     * Passes the new user and password to the logic layer
     * @return true if user was created successfully, false if the username is already taken
     */
    public boolean createUser(User user, String password) {
       return bllManager.createUser(user, password);
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
