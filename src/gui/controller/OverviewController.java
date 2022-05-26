package gui.controller;

import be.Categories.FunctionalAbility;
import be.Categories.GeneralInfo;
import be.Categories.HealthCondition;
import be.Categories.InfoTemplates;
import be.enums.Status;
import gui.SceneManager;
import gui.model.CitizenModel;
import gui.model.ModelManager;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class OverviewController implements Initializable {
    public VBox vboxGeneral;
    public VBox vboxHealth;
    public VBox vboxFunction;

    private CitizenModel citizenModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.citizenModel = ModelManager.getInstance().getCitizenModel();
            generateGeneralInfo();
            generateHealthConditions();
            generateFunctionalAbilities();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Uses the "general info template" to create a pane for each category containing information
     * and adds it to the pane of general info
     */
    private void generateGeneralInfo() throws IOException {
        HashMap<String, GeneralInfo> hashMap = citizenModel.getCurrentCitizen().getGeneralInfo();
        for (GeneralInfo g : InfoTemplates.getGeneralInfoArrayList()){
            GeneralInfo generalInfo = hashMap.get(g.getName());
            if (generalInfo.getText() != null && !generalInfo.getText().isBlank()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(SceneManager.class.getResource("view/GeneralInfoTemplate.fxml"));
                vboxGeneral.getChildren().add(fxmlLoader.load());
                GeneralInfoTemplateController controller = fxmlLoader.getController();
                controller.setInfo(generalInfo);
            }
        }
    }

    /**
     * Uses the "health condition template" to create a pane for each category with the Active or Potential status
     * and adds it to the pane of health conditions
     */
    private void generateHealthConditions() throws IOException {
        HashMap<String, HealthCondition> hashMap = citizenModel.getCurrentCitizen().getHealthConditions();
        for (HealthCondition h : InfoTemplates.getHealthConditionArrayList()){
            HealthCondition healthCondition = hashMap.get(h.getName());
            if (healthCondition.getStatus() == Status.ACTIVE || healthCondition.getStatus() == Status.POTENTIAL){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(SceneManager.class.getResource("view/HealthTemplate.fxml"));
                vboxHealth.getChildren().add(fxmlLoader.load());
                HealthTemplateController controller = fxmlLoader.getController();
                controller.setInfo(healthCondition);
            }
        }
    }

    /**
     * Uses the "functional ability template" to create a pane for each category with the Active status
     * and adds it to the pane of functional abilities
     */
    private void generateFunctionalAbilities() throws IOException {
        HashMap<String, FunctionalAbility> hashMap = citizenModel.getCurrentCitizen().getFunctionalAbilities();
        for (FunctionalAbility f : InfoTemplates.getFunctionalAbilityArrayList()){
            FunctionalAbility functionalAbility = hashMap.get(f.getName());
            if (functionalAbility.getStatus() == Status.ACTIVE){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(SceneManager.class.getResource("view/FunctionTemplate.fxml"));
                vboxFunction.getChildren().add(fxmlLoader.load());
                FunctionTemplateController controller = fxmlLoader.getController();
                controller.setInfo(functionalAbility);
            }
        }
    }
}
