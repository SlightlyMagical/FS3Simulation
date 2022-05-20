package dal.dao;

import be.Usertypes.Admin;
import be.Usertypes.Student;
import be.Usertypes.Teacher;
import be.Usertypes.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.DBConnector;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class UserDAO implements IUserDAO {
    DBConnector dbConnector;

    public UserDAO() throws IOException {
        dbConnector = new DBConnector();
    }

    @Override
    public User checkLogin(String username, String password) throws SQLServerException {
        User user = null;
        try(Connection connection = dbConnector.getConnection()){
            String sql = "SELECT * FROM Users WHERE Username = (?) AND Password = (?) COLLATE SQL_Latin1_General_CP1_CS_AS;";
            PreparedStatement ps =connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int id = rs.getInt("UserID");
                int schoolID = rs.getInt("SchoolID");
                int userType = rs.getInt("UserType");

                switch (userType) {
                    case 1 -> //Admin
                            user = new Admin(id, username, userType);
                    case 2 -> //Teacher
                            user = new Teacher(id, username, schoolID, userType);
                    case 3 -> //Student
                            user = new Student(id, username, schoolID, userType);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user;
    }

    @Override
    public ArrayList<Student> getAllStudents(int schoolID) {
        ArrayList<Student> students = new ArrayList<>();
        try(Connection connection = dbConnector.getConnection()){
            String sql = "SELECT * FROM Users WHERE SchoolID = (?) AND UserType = 3;";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, schoolID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("UserID");
                String username = rs.getString("Username");
                String password = rs.getString("Password");

                Student student = new Student(id, username, schoolID, 3);
                student.setPassword(password);

                students.add(student);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return students;
    }

    @Override
    public boolean createUser(User user, String password) {
        try (Connection connection = dbConnector.getConnection()){
            String sql = "SELECT Username FROM Users WHERE Username = (?) ";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            }

            String sql2 = "INSERT INTO Users (Username, Password, UserType, SchoolID) VALUES (?, ?, ?, ?)";
            PreparedStatement ps2 = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
            ps2.setString(1, user.getUsername());
            ps2.setString(2, password);
            ps2.setInt(3, user.getUserType());
            if (user.getSchoolID() == -1)
                ps2.setNull(4, Types.INTEGER);
            else
                ps2.setInt(4, user.getSchoolID());

            int affectedRows = ps2.executeUpdate();

            if(affectedRows == 1) {
                ResultSet rs2 = ps2.getGeneratedKeys();
                if (rs2.next()) {
                    user.setId(rs2.getInt(1));
                    return true;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }
}
