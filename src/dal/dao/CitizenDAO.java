package dal.dao;

import be.Categories.FunctionalAbility;
import be.Categories.GeneralInfo;
import be.Categories.HealthCondition;
import be.Citizen;
import be.Usertypes.User;
import be.enums.Status;
import dal.DBConnector;
import javafx.collections.FXCollections;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CitizenDAO implements ICitizenDAO{

    DBConnector dbConnector;

    public CitizenDAO() throws IOException {
        dbConnector = new DBConnector();
    }


    @Override
    public List<Citizen> getCitizens(User user) {
        List<Citizen> citizens = FXCollections.observableArrayList();
        try(Connection connection = dbConnector.getConnection()){
            String sql1 = "SELECT * FROM StudentCitizen LEFT JOIN Citizen ON StudentCitizen.CitizenID = Citizen.CitizenID WHERE StudentID = (?);";
            PreparedStatement ps1 = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
            ps1.setInt(1, user.getId());
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
    public List<Citizen> getGeneralInfo(List<Citizen> citizens) {
        try (Connection connection = dbConnector.getConnection()) {
            for (Citizen c : citizens) {
                String sql = "SELECT * FROM CitizenInfo LEFT JOIN GeneralInfo ON CitizenInfo.InfoID = GeneralInfo.InfoID WHERE CitizenID = (?);";
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, c.getId());
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    String name = rs.getString("InfoName");
                    String infoText = rs.getString("InfoText");
                    GeneralInfo generalInfo = new GeneralInfo(name);
                    generalInfo.setText(infoText);
                    c.addGeneralInfo(generalInfo);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return citizens;
    }


    @Override
    public List<Citizen> getHealthConditions(List<Citizen> citizens) {
        try (Connection connection = dbConnector.getConnection()) {
            for (Citizen c : citizens) {
                String sql = "SELECT * FROM CitizenCondition LEFT JOIN HealthCondition ON CitizenCondition.ConditionID = HealthCondition.ConditionID WHERE CitizenID = (?);";
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    String name = rs.getString("ConditionName");
                    int status = rs.getInt("ConditionStatus");
                    String conditionNote = rs.getString("ConditionNote");
                    String conditionAssessment = rs.getString("ConditionAssessment");
                    String conditionExpectation = rs.getString("ConditionExpectation");
                    HealthCondition healthCondition = new HealthCondition(name, conditionNote);
                    switch (status) {
                        case 1 -> healthCondition.setStatus(Status.ACTIVE);
                        case 2 -> healthCondition.setStatus(Status.POTENTIAL);
                    }
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
    public List<Citizen> getFunctionalAbilities(List<Citizen> citizens) {
        try (Connection connection = dbConnector.getConnection()) {
            for (Citizen c : citizens) {
                String sql = "SELECT * FROM CitizenAbility LEFT JOIN FunctionalAbility ON CitizenAbility.AbilityID = FunctionalAbility.AbilityID WHERE CitizenID = (?);";
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
        for (Map.Entry<String, GeneralInfo> entry : selectedPatient.getGeneralInfo().entrySet()) {
            String key = entry.getKey();
            GeneralInfo value = entry.getValue();
            try (Connection connection = dbConnector.getConnection()) {
                String sql = "INSERT INTO CitizenInfo (CitizenID, InfoID, InfoText) VALUES (?, ?, ?);";
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, selectedPatient.getId());
                ps.setInt(2, value.getID());
                ps.setString(3, value.getText());
                int affectedRows = ps.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

        }

}
