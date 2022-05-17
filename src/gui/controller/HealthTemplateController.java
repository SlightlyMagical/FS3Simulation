package gui.controller;

import be.Categories.HealthCondition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;

public class HealthTemplateController {
    @FXML
    private TitledPane titledPane;
    @FXML
    private Label lblNote;
    @FXML
    private Label lblAssessment;
    @FXML
    private Label lblExpectedLevel;
    @FXML
    private Label lblObservations;

    public void setInfo(HealthCondition healthCondition){
        titledPane.setText(healthCondition.getName());
        lblNote.setText(healthCondition.getProfessionalNote());
        lblAssessment.setText(healthCondition.getCurrentAssessment());
        lblExpectedLevel.setText(healthCondition.getExpectedLevel());
        lblObservations.setText(healthCondition.getObservations());
    }
}
