package gui.controller;

import be.Categories.GeneralInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;

public class GeneralInfoTemplateController {
    @FXML
    private TitledPane titledPane;
    @FXML
    private Label lblText;


    public void setInfo(GeneralInfo generalInfo){
        titledPane.setText(generalInfo.getName());
        lblText.setText(generalInfo.getText());
    }
}
