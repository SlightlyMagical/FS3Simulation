package dal.dao;

import be.Usertypes.Admin;
import be.Usertypes.Student;
import be.Usertypes.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.util.ArrayList;

public interface IUserDAO {
    User checkLogin(String username, String password) throws SQLServerException;

    ArrayList<Student> getAllStudents(int schoolID);

    boolean createUser(User user, String password);

    void deleteUser(int userID);

    ArrayList<Admin> getAdmins();
}
