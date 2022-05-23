package dal.dao;

import be.School;
import be.Usertypes.Student;
import be.Usertypes.Teacher;
import dal.DBConnector;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class SchoolDAO implements ISchoolDAO{
    DBConnector dbConnector;

    public SchoolDAO() throws IOException {
        dbConnector = new DBConnector();
    }

    @Override
    public boolean createSchool(School school) {
        try (Connection connection = dbConnector.getConnection()){
            String sql = "SELECT * FROM School WHERE SchoolName = (?) ";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, school.getName());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            }

            String sql2 = "INSERT INTO School (SchoolName) VALUES (?)";
            PreparedStatement ps2 = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
            ps2.setString(1, school.getName());

            int affectedRows = ps2.executeUpdate();

            if(affectedRows == 1) {
                ResultSet rs2 = ps2.getGeneratedKeys();
                if (rs2.next()) {
                    school.setId(rs2.getInt(1));
                    return true;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public void deleteSchool(int schoolID) {
        try(Connection connection = dbConnector.getConnection()){
            String sql = "DELETE FROM School WHERE SchoolID = (?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, schoolID);
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public ArrayList<School> getAllSchools() {
        ArrayList<School> schools = new ArrayList<>();
        try(Connection connection = dbConnector.getConnection()){
            String sql = "SELECT * FROM School;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                int schoolID = rs.getInt("SchoolID");
                String name = rs.getString("SchoolName");
                School school = new School(name, schoolID);
                schools.add(school);

                String sql2 = "SELECT * FROM Users WHERE SchoolID = (?);";
                PreparedStatement ps2 = connection.prepareStatement(sql2);
                ps2.setInt(1, schoolID);
                ResultSet rs2 = ps2.executeQuery();

                while (rs2.next()){
                    int userID = rs2.getInt("UserID");
                    String userName = rs2.getString("UserName");
                    int userType = rs2.getInt("UserType");

                    switch (userType){
                        case 2 -> school.getTeachers().add(new Teacher(userID, userName, schoolID, userType));
                        case 3 -> {
                            Student student = new Student(userID, userName, schoolID, userType);
                            student.setPassword(rs2.getString("Password"));
                            school.getStudents().add(student);
                        }
                    }
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return schools;
    }
}
