package dal.dao;

import be.Usertypes.Admin;
import be.Usertypes.Student;
import be.Usertypes.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.util.ArrayList;

public interface IUserDAO {
    /**
     * Checks if a user with the given login credentials exists in the database
     * @return the user if existing, null if not
     */
    User checkLogin(String username, String password) throws SQLServerException;

    /**
     * Requests all students of the school with the given ID from the database
     * @return the list of students
     */
    ArrayList<Student> getAllStudents(int schoolID);

    /**
     * Creates a new use in the database
     * @return true if created correctly, false if the username is already taken
     */
    boolean createUser(User user, String password);

    /**
     * Deletes the user with the given ID from the database
     */
    void deleteUser(int userID);

    /**
     * Requests all admins from the database
     * @return the list of admins
     */
    ArrayList<Admin> getAdmins();
}
