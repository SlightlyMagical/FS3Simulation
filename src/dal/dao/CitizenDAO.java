package dal.dao;

import be.Categories.FunctionalAbility;
import be.Categories.GeneralInfo;
import be.Categories.HealthCondition;
import be.Categories.InfoTemplates;
import be.Citizen;
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
                    HealthCondition healthCondition = new HealthCondition(id, catID, name);
                    switch (status) {
                        case 1 -> healthCondition.setStatus(Status.ACTIVE);
                        case 2 -> healthCondition.setStatus(Status.POTENTIAL);
                        case 3 -> healthCondition.setStatus(Status.NOT_RELEVANT);
                    }
                    if (conditionNote != null)
                        healthCondition.setProfessionalNote(conditionNote);

                    if (conditionAssessment != null)
                        healthCondition.setCurrentAssessment(conditionAssessment);

                    if (conditionExpectation != null)
                        healthCondition.setExpectedLevel(conditionExpectation);

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
                    String name = rs.getString("AbilityName");
                    int currentLevel = rs.getInt("CurrentLevel");
                    int expectedLevel = rs.getInt("ExpectedLevel");
                    String abilityNote = rs.getString("AbilityNote");
                    String citizenExecution = rs.getString("CitizenExecution");
                    boolean citizenLimitation = rs.getBoolean("CitizenLimitation");
                    String citizenGoal = rs.getString("CitizenGoal");
                    FunctionalAbility functionalAbility = new FunctionalAbility(name, Status.ACTIVE, currentLevel, expectedLevel, abilityNote,
                            citizenExecution, citizenLimitation, citizenGoal);
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
    public boolean saveHealthCondition(HealthCondition healthCondition, int citizenID) {
        try (Connection connection = dbConnector.getConnection()) {
            String sql = "DELETE FROM CitizenCondition WHERE CitizenID = (?) AND ConditionID = (?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, citizenID);
            ps.setInt(2, healthCondition.getId());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try (Connection connection = dbConnector.getConnection()) {
            String sql2 = "INSERT INTO CitizenCondition (CitizenID, ConditionID, ConditionStatus, ConditionNote, ConditionAssessment, ConditionExpectation) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement ps2 = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
            ps2.setInt(1, citizenID);
            ps2.setInt(2, healthCondition.getId());
            switch (healthCondition.getStatus()){
                case ACTIVE -> ps2.setInt(3, 1);
                case POTENTIAL -> ps2.setInt(3, 2);
                case NOT_RELEVANT -> ps2.setInt(3, 3);
            }
            ps2.setString(4, healthCondition.getProfessionalNote());
            ps2.setString(5, healthCondition.getCurrentAssessment());
            ps2.setString(6, healthCondition.getExpectedLevel());

            int affectedRows = ps2.executeUpdate();
            if (affectedRows == 1)
                return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
