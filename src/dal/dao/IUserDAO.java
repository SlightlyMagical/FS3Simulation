package dal.dao;

import be.Usertypes.User;

public interface IUserDAO {
    User checkLogin(String username, String password);
}
