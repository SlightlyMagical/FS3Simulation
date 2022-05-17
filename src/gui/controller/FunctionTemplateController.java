package gui.controller;

import be.Categories.FunctionalAbility;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;

public class FunctionTemplateController {
    @FXML
    private TitledPane titledPane;
    @FXML
    private Label lblCurrentLevel;
    @FXML
    private Label lblExpectedLevel;
    @FXML
    private Label lblNote;
    @FXML
    private Label lblExecution;
    @FXML
    private Label lblLimitation;
    @FXML
    private Label lblGoals;
    @FXML
    private Label lblObservations;

    public void setInfo(FunctionalAbility functionalAbility) {
        titledPane.setText(functionalAbility.getName());
        lblCurrentLevel.setText(String.valueOf(functionalAbility.getCurrentLevel()));
        lblExpectedLevel.setText(String.valueOf(functionalAbility.getExpectedLevel()));
        lblNote.setText(functionalAbility.getProfessionalNote());
        lblExecution.setText(functionalAbility.getTaskExecution());
        lblLimitation.setText(functionalAbility.getExecutionLimitation());
        lblGoals.setText(functionalAbility.getCitizenGoal());
        lblObservations.setText(functionalAbility.getObservation());
    }
}
