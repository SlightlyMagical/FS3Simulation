package dal.dao;

import be.Categories.FunctionalAbility;
import be.Categories.GeneralInfo;
import be.Categories.HealthCondition;
import be.Categories.InfoTemplates;
import be.Citizen;
import be.Usertypes.Student;
import be.enums.Status;
import dal.DBConnector;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CitizenDAO implements ICitizenDAO{

    DBConnector dbConnector;

    public CitizenDAO() throws IOException {
        dbConnector = new DBConnector();
    }

    @Override
    public ArrayList<Citizen> getCitizens(int userID) {
        ArrayList<Citizen> citizens = new ArrayList<>();
        try(Connection connection = dbConnector.getConnection()){
            String sql1 = "SELECT * FROM StudentCitizen LEFT JOIN Citizen ON StudentCitizen.CitizenID = Citizen.CitizenID WHERE UserID = (?);";
            PreparedStatement ps1 = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
            ps1.setInt(1, userID);
            ResultSet rs = ps1.executeQuery();

            while (rs.next()){
                int id = rs.getInt("CitizenID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                Citizen citizen = new Citizen(id, firstName, lastName);
                citizens.add(citizen);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return citizens;
    }

    @Override
    public ArrayList<Citizen> getGeneralInfo(ArrayList<Citizen> citizens) {
        try (Connection connection = dbConnector.getConnection()) {
            for (Citizen c : citizens) {
                HashMap<String, GeneralInfo> generalInfo = InfoTemplates.getGeneralInfoHashMap();
                String sql = "SELECT * FROM CitizenInfo LEFT JOIN GeneralInfo ON CitizenInfo.InfoID = GeneralInfo.InfoID WHERE CitizenID = (?);";
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, c.getId());
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    String name = rs.getString("InfoName");
                    String infoText = rs.getString("InfoText");
                    generalInfo.get(name).setText(infoText);
                }
                c.setGeneralInfo(generalInfo);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return citizens;
    }

    @Override
    public ArrayList<Citizen> getHealthConditions(ArrayList<Citizen> citizens) {
        try (Connection connection = dbConnector.getConnection()) {
            for (Citizen c : citizens) {
                String sql = "SELECT * FROM CitizenCondition LEFT JOIN HealthCondition ON CitizenCondition.ConditionID = HealthCondition.ConditionID WHERE CitizenID = (?);";
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, c.getId());
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("ConditionID");
                    int catID = rs.getInt("ConditionCatID");
                    String name = rs.getString("ConditionName");
                    int status = rs.getInt("ConditionStatus");
                    String conditionNote = rs.getString("ConditionNote");
                    String conditionAssessment = rs.getString("ConditionAssessment");
                    String conditionExpectation = rs.getString("ConditionExpectation");
                    String conditionObservation = rs.getString("ConditionObservation");
                    HealthCondition healthCondition = new HealthCondition(id, catID, name);
                    switch (status) {
                        case -1 -> healthCondition.setStatus(null);
                        case 1 -> healthCondition.setStatus(Status.ACTIVE);
                        case 2 -> healthCondition.setStatus(Status.POTENTIAL);
                        case 3 -> healthCondition.setStatus(Status.NOT_RELEVANT);
                    }
                    healthCondition.setProfessionalNote(conditionNote);
                    healthCondition.setCurrentAssessment(conditionAssessment);
                    healthCondition.setExpectedLevel(conditionExpectation);
                    healthCondition.setObservations(conditionObservation);
                    c.addHealthCondition(healthCondition);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return citizens;
    }

    @Override
    public ArrayList<Citizen> getFunctionalAbilities(ArrayList<Citizen> citizens) {
        try (Connection connection = dbConnector.getConnection()) {
            for (Citizen c : citizens) {
                String sql = "SELECT * FROM CitizenAbility LEFT JOIN FunctionalAbility ON CitizenAbility.AbilityID = FunctionalAbility.AbilityID WHERE CitizenID = (?);";
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, c.getId());
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("AbilityID");
                    int catID = rs.getInt("AbilityCatID");
                    String name = rs.getString("AbilityName");
                    int status = rs.getInt("AbilityStatus");

                    FunctionalAbility functionalAbility = new FunctionalAbility(id, catID, name);
                    switch (status){
                        case -1 -> functionalAbility.setStatus(null);
                        case 1 -> functionalAbility.setStatus(Status.ACTIVE);
                        case 2 -> functionalAbility.setStatus(Status.NOT_RELEVANT);
                    }
                    functionalAbility.setCurrentLevel(rs.getInt("CurrentLevel"));
                    functionalAbility.setExpectedLevel(rs.getInt("ExpectedLevel"));
                    functionalAbility.setProfessionalNote(rs.getString("AbilityNote"));
                    functionalAbility.setTaskExecution(rs.getString("CitizenExecution"));
                    functionalAbility.setExecutionLimitation(rs.getString("CitizenLimitation"));
                    functionalAbility.setCitizenGoal(rs.getString("CitizenGoal"));

                    c.addFunctionalAbility(functionalAbility);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return citizens;
    }

    @Override
    public void updatePatientGeneralInfo(Citizen selectedPatient) {
        try (Connection connection = dbConnector.getConnection()) {
            String sql = "DELETE FROM CitizenInfo WHERE CitizenID = (?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, selectedPatient.getId());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        for (Map.Entry<String, GeneralInfo> entry : selectedPatient.getGeneralInfo().entrySet()) {
            String key = entry.getKey();
            GeneralInfo value = entry.getValue();
            try (Connection connection = dbConnector.getConnection()) {
                String sql2 = "INSERT INTO CitizenInfo (CitizenID, InfoID, InfoText) VALUES (?, ?, ?);";
                PreparedStatement ps2 = connection.prepareStatement(sql2);
                ps2.setInt(1, selectedPatient.getId());
                ps2.setInt(2, value.getID());
                ps2.setString(3, value.getText());
                ps2.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public boolean saveHealthConditions(ArrayList<HealthCondition> healthConditions, int citizenID) {
        try (Connection connection = dbConnector.getConnection()) {
            for (HealthCondition healthCondition : healthConditions) {
                String sql = "DELETE FROM CitizenCondition WHERE CitizenID = (?) AND ConditionID = (?);";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, citizenID);
                ps.setInt(2, healthCondition.getId());
                ps.executeUpdate();

                String sql2 = "INSERT INTO CitizenCondition (CitizenID, ConditionID, ConditionStatus, ConditionNote, ConditionAssessment, ConditionExpectation, ConditionObservation) VALUES (?, ?, ?, ?, ?, ?, ?);";
                PreparedStatement ps2 = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
                ps2.setInt(1, citizenID);
                ps2.setInt(2, healthCondition.getId());
                if (healthCondition.getStatus() == null)
                    ps2.setInt(3, -1);
                else {
                    switch (healthCondition.getStatus()) {
                        case ACTIVE -> ps2.setInt(3, 1);
                        case POTENTIAL -> ps2.setInt(3, 2);
                        case NOT_RELEVANT -> ps2.setInt(3, 3);
                    }
                }
                ps2.setString(4, healthCondition.getProfessionalNote());
                ps2.setString(5, healthCondition.getCurrentAssessment());
                ps2.setString(6, healthCondition.getExpectedLevel());
                ps2.setString(7, healthCondition.getObservations());

                ps2.executeUpdate();
            }
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveFunctionalAbilities(ArrayList<FunctionalAbility> functionalAbilities, int citizenID) {
        try (Connection connection = dbConnector.getConnection()) {
            for (FunctionalAbility functionalAbility : functionalAbilities) {
                String sql = "DELETE  FROM CitizenAbility WHERE CitizenID = (?) AND AbilityID = (?);";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, citizenID);
                ps.setInt(2, functionalAbility.getId());
                ps.executeUpdate();

                String sql2 = "INSERT INTO CitizenAbility (CitizenID, AbilityID, AbilityStatus, CurrentLevel, ExpectedLevel, AbilityNote, CitizenExecution, CitizenLimitation, CitizenGoal, AbilityObservation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
                PreparedStatement ps2 = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
                ps2.setInt(1, citizenID);
                ps2.setInt(2, functionalAbility.getId());
                if (functionalAbility.getStatus() == null)
                    ps2.setInt(3, -1);
                else {
                    switch (functionalAbility.getStatus()) {
                        case ACTIVE -> ps2.setInt(3, 1);
                        case NOT_RELEVANT -> ps2.setInt(3, 2);
                    }
                }
                ps2.setInt(4, functionalAbility.getCurrentLevel());
                ps2.setInt(5, functionalAbility.getExpectedLevel());
                ps2.setString(6, functionalAbility.getProfessionalNote());
                ps2.setString(7, functionalAbility.getTaskExecution());
                ps2.setString(8, functionalAbility.getExecutionLimitation());
                ps2.setString(9, functionalAbility.getCitizenGoal());
                ps2.setString(10, functionalAbility.getObservation());

                ps2.executeUpdate();
            }
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<Citizen> getCitizensOfSchool(int schoolID) {
        ArrayList<Citizen> citizens = new ArrayList<>();
        try(Connection connection = dbConnector.getConnection()){
            String sql = "SELECT * FROM Citizen WHERE SchoolID = (?);";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, schoolID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int citizenID = rs.getInt("CitizenID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                boolean isTemplate = rs.getBoolean("IsTemplate");
                int teacherID = rs.getInt("TeacherID");
                Citizen citizen = new Citizen(citizenID, firstName, lastName);
                citizen.setIsTemplate(isTemplate);
                citizen.setTeacherID(teacherID);
                citizen.setSchoolID(schoolID);
                citizens.add(citizen);
            }

            for (Citizen c : citizens) {
                String sql2 = "SELECT * FROM StudentCitizen LEFT JOIN Users ON StudentCitizen.UserID = Users.UserID WHERE CitizenID = (?);";
                PreparedStatement ps2 = connection.prepareStatement(sql2);
                ps2.setInt(1, c.getId());

                ResultSet rs2 = ps2.executeQuery();

                while (rs2.next()){
                    int id = rs2.getInt("UserID");
                    String username = rs2.getString("Username");
                    String password = rs2.getString("Password");

                    Student student = new Student(id, username, schoolID, 3);
                    student.setPassword(password);

                    c.getAssignedStudents().add(student);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return citizens;
    }

    @Override
    public void createNewCitizen(Citizen citizen, int schoolID) {
        try (Connection connection = dbConnector.getConnection()) {
            String sql = "INSERT INTO Citizen (SchoolID, FirstName, LastName, IsTemplate, TeacherID) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, schoolID);
            ps.setString(2, citizen.getFirstName());
            ps.setString(3, citizen.getLastName());
            ps.setBoolean(4, citizen.isTemplate());
            ps.setInt(5, citizen.getTeacherID());
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                    citizen.setId(rs.getInt(1));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Citizen createCitizenCopy(Citizen citizen, boolean isTemplate, int teacherID) {
        Citizen newCitizen = null;
        try (Connection connection = dbConnector.getConnection()){
            String sql = "INSERT INTO Citizen (SchoolID, FirstName, LastName, IsTemplate, TeacherID) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, citizen.getSchoolID());
            ps.setString(2, citizen.getFirstName());
            ps.setString(3, citizen.getLastName());
            ps.setBoolean(4, isTemplate);
            ps.setInt(5, teacherID);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            newCitizen = new Citizen(rs.getInt(1), citizen.getFirstName(), citizen.getLastName());
            newCitizen.setSchoolID(citizen.getSchoolID());
            newCitizen.setTeacherID(teacherID);
            newCitizen.setIsTemplate(isTemplate);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return newCitizen;
    }

    @Override
    public void copyGeneralInfo(int originalID, int newID) {
        try (Connection connection = dbConnector.getConnection()){
            String sql1 = "SELECT * FROM CitizenInfo WHERE CitizenID = (?);";
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            ps1.setInt(1, originalID);
            ResultSet rs1 = ps1.executeQuery();

            while (rs1.next()){
                String sql2 = "INSERT INTO CitizenInfo (CitizenID, InfoID, InfoText) VALUES (?, ?, ?)";
                PreparedStatement ps2 = connection.prepareStatement(sql2);
                ps2.setInt(1, newID);
                ps2.setInt(2, rs1.getInt("InfoID"));
                ps2.setString(3, rs1.getString("InfoText"));
                ps2.executeUpdate();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void copyHealthConditions(int originalID, int newID) {
        try (Connection connection = dbConnector.getConnection()){
            String sql1 = "SELECT * FROM CitizenCondition WHERE CitizenID = (?);";
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            ps1.setInt(1, originalID);
            ResultSet rs1 = ps1.executeQuery();

            while (rs1.next()){
                String sql2 = "INSERT INTO CitizenCondition (CitizenID, ConditionID, ConditionStatus, ConditionNote, ConditionAssessment, ConditionExpectation, ConditionObservation) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps2 = connection.prepareStatement(sql2);
                ps2.setInt(1, newID);
                ps2.setInt(2, rs1.getInt("ConditionID"));
                ps2.setInt(3, rs1.getInt("ConditionStatus"));
                ps2.setString(4, rs1.getString("ConditionNote"));
                ps2.setString(5, rs1.getString("ConditionAssessment"));
                ps2.setString(6, rs1.getString("ConditionExpectation"));
                ps2.setString(7, rs1.getString("ConditionObservation"));
                ps2.executeUpdate();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void copyFunctionalAbilities(int originalID, int newID) {
        try (Connection connection = dbConnector.getConnection()){
            String sql1 = "SELECT * FROM CitizenAbility WHERE CitizenID = (?);";
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            ps1.setInt(1, originalID);
            ResultSet rs1 = ps1.executeQuery();

            while (rs1.next()){
                String sql2 = "INSERT INTO CitizenAbility (CitizenID, AbilityID, AbilityStatus, CurrentLevel, ExpectedLevel, AbilityNote, " +
                        "CitizenExecution, CitizenLimitation, CitizenGoal, AbilityObservation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps2 = connection.prepareStatement(sql2);
                ps2.setInt(1, newID);
                ps2.setInt(2, rs1.getInt("AbilityID"));
                ps2.setInt(3, rs1.getInt("AbilityStatus"));
                ps2.setInt(4, rs1.getInt("CurrentLevel"));
                ps2.setInt(5, rs1.getInt("ExpectedLevel"));
                ps2.setString(6, rs1.getString("AbilityNote"));
                ps2.setString(7, rs1.getString("CitizenExecution"));
                ps2.setString(8, rs1.getString("CitizenLimitation"));
                ps2.setString(9, rs1.getString("CitizenGoal"));
                ps2.setString(10, rs1.getString("AbilityObservation"));
                ps2.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeCitizenName(Citizen citizen) {
        try (Connection connection = dbConnector.getConnection()){
            String sql = "UPDATE Citizen SET FirstName = (?), LastName = (?) WHERE CitizenID = (?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, citizen.getFirstName());
            ps.setString(2, citizen.getLastName());
            ps.setInt(3, citizen.getId());
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeAssignedStudents(Citizen citizen) {
        try (Connection connection = dbConnector.getConnection()){
            String sql = "DELETE FROM StudentCitizen WHERE CitizenID = (?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, citizen.getId());
            ps.executeUpdate();

            for (Student s : citizen.getAssignedStudents()) {
                String sql2 = "INSERT INTO StudentCitizen (CitizenID, UserID) VALUES (?, ?);";
                PreparedStatement ps2 = connection.prepareStatement(sql2);
                ps2.setInt(1, citizen.getId());
                ps2.setInt(2, s.getId());
                ps2.executeUpdate();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteCitizen(Citizen citizen){
        try (Connection connection = dbConnector.getConnection()){
            String sql = "DELETE FROM Citizen WHERE CitizenID = (?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, citizen.getId());
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
