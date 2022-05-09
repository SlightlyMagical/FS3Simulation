package gui.controller;

import be.Categories.GeneralInfo;
import be.Citizen;
import gui.SceneManager;
import gui.model.CitizenModel;
import gui.model.DialogHandler;
import gui.model.Messages;
import gui.model.ModelManager;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class CitizenController implements Initializable {

    public Tooltip tipMotivation;
    public TextArea textMotivation;
    public Tooltip tipMestring;
    public TextArea textMestring;
    public Tooltip tipRessourcer;
    public TextArea textRessourcer;
    public Tooltip tipRoller;
    public TextArea textRoller;
    public Tooltip tipVaner;
    public TextArea textVaner;
    public Tooltip tipJob;
    public TextArea textJob;
    public Tooltip tipLiv;
    public TextArea textLiv;
    public Tooltip tipHelbred;
    public TextArea textHelbred;
    public Tooltip tipHjaelp;
    public TextArea textHjaelp;
    public Tooltip tipBolig;
    public TextArea textBolig;
    public Tooltip tipNetvaerk;
    public TextArea textNetvaerk;
    public ComboBox<String> cbHealthSaveAs;
    public VBox healthPotentialBox;
    public VBox healthExtendedBox;
    public Button healthSaveButton;
    public Label lblHealthCategory;
    public Label lblHealthSubCategory;
    public TextArea txtHealthProfNote;
    public TextArea txtHealthCurrentAssessment;
    public ComboBox<String> cbHealthExpectedLevel;

    private CitizenModel citizenModel;
    private Citizen currentCitizen;

    private boolean infoUnsavedChanges = false;
    private boolean healthUnsavedChanges = false;
    private boolean abilityUnsavedChanges = false;

    public CitizenController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.citizenModel = ModelManager.getInstance().getCitizenModel();
            this.currentCitizen = citizenModel.getCurrentCitizen();

        } catch (IOException e) {
            e.printStackTrace();
        }
        setUpGeneralInfo();
        cbHealthSaveAs.getItems().addAll("Aktivt", "Potentielt");
        cbHealthExpectedLevel.getItems().addAll("Mindskes", "Forbliver uændret", "Forsvinder");
    }

    private boolean checkIfSaved(){
        if (infoUnsavedChanges || healthUnsavedChanges || abilityUnsavedChanges)
            return DialogHandler.confirmationAlert(Messages.UNSAVED_CHANGES);

        return true;
    }

    private void addTextFormatter(TextArea textArea){
        textArea.setTextFormatter(new TextFormatter<String>(change -> {
            infoUnsavedChanges = true;
            return change ;
        }));
    }

    public void goBack(ActionEvent actionEvent) {
        if (checkIfSaved())
            SceneManager.showStudentScene();
    }

    public void saveGeneralInfo(ActionEvent actionEvent) {
        HashMap<String, GeneralInfo> generalInfoHashMap = currentCitizen.getGeneralInfo();

        //Get specific info from hashmap (Motivation, Mestring ...)
        GeneralInfo newMestringInfo = generalInfoHashMap.get("Mestring");
        //Set Text from text box
        newMestringInfo.setText(textMestring.getText());
        //Replace existing General info with new one
        generalInfoHashMap.put("Mestring", newMestringInfo);
        //Repeat.

        //Get specific info from hashmap (Motivation, Mestring ...)
        GeneralInfo newMotivationInfo = generalInfoHashMap.get("Motivation");
        //Set Text from text box
        newMotivationInfo.setText(textMotivation.getText());
        //Replace existing General info with new one
        generalInfoHashMap.put("Motivation", newMotivationInfo);
        //Repeat.


        GeneralInfo newRessourcerInfo = generalInfoHashMap.get("Ressourcer");
        newRessourcerInfo.setText(textRessourcer.getText());
        generalInfoHashMap.put("Ressourcer", newRessourcerInfo);

        GeneralInfo newRollerInfo = generalInfoHashMap.get("Roller");
        newRollerInfo.setText(textRoller.getText());
        generalInfoHashMap.put("Roller", newRollerInfo);

        GeneralInfo newVanerInfo = generalInfoHashMap.get("Vaner");
        newVanerInfo.setText(textVaner.getText());
        generalInfoHashMap.put("Vaner", newVanerInfo);

        GeneralInfo newJobInfo = generalInfoHashMap.get("Uddannelse og job");
        newJobInfo.setText(textJob.getText());
        generalInfoHashMap.put("Uddannelse og job", newJobInfo);

        GeneralInfo newLivInfo = generalInfoHashMap.get("Livshistorie");
        newLivInfo.setText(textLiv.getText());
        generalInfoHashMap.put("Livshistorie", newLivInfo);

        GeneralInfo newHelbredInfo = generalInfoHashMap.get("Helbredsoplysninger");
        newHelbredInfo.setText(textHelbred.getText());
        generalInfoHashMap.put("Helbredsoplysninger", newHelbredInfo);

        GeneralInfo newHjaelpInfo = generalInfoHashMap.get("Hjælpemidler");
        newHjaelpInfo.setText(textHjaelp.getText());
        generalInfoHashMap.put("Hjælpemidler", newHjaelpInfo);

        GeneralInfo newBoligInfo = generalInfoHashMap.get("Boligens indretning");
        newBoligInfo.setText(textBolig.getText());
        generalInfoHashMap.put("Boligens indretning", newBoligInfo);

        GeneralInfo newNetvaerkInfo = generalInfoHashMap.get("Netværk");
        newNetvaerkInfo.setText(textNetvaerk.getText());
        generalInfoHashMap.put("Netværk", newNetvaerkInfo);

        //
        currentCitizen.setGeneralInfo(generalInfoHashMap);

        citizenModel.updatePatientGeneralInfo(currentCitizen);

        infoUnsavedChanges = false;
    }

    public void setUpGeneralInfo(){
        HashMap<String, GeneralInfo> generalInfoHashMap = currentCitizen.getGeneralInfo();

        // Mestring
        tipMestring.setText(generalInfoHashMap.get("Mestring").getDescription());
        textMestring.setText(generalInfoHashMap.get("Mestring").getText());
        addTextFormatter(textMestring);

        // Motivation
        tipMotivation.setText(generalInfoHashMap.get("Motivation").getDescription());
        textMotivation.setText(generalInfoHashMap.get("Motivation").getText());
        addTextFormatter(textMotivation);

        // Ressourcer
        tipRessourcer.setText(generalInfoHashMap.get("Ressourcer").getDescription());
        textRessourcer.setText(generalInfoHashMap.get("Ressourcer").getText());

        // Roller
        tipRoller.setText(generalInfoHashMap.get("Roller").getDescription());
        textRoller.setText(generalInfoHashMap.get("Roller").getText());
        addTextFormatter(textRoller);

        // Vaner
        tipVaner.setText(generalInfoHashMap.get("Vaner").getDescription());
        textVaner.setText(generalInfoHashMap.get("Vaner").getText());
        addTextFormatter(textVaner);

        // Job
        tipJob.setText(generalInfoHashMap.get("Uddannelse og job").getDescription());
        textJob.setText(generalInfoHashMap.get("Uddannelse og job").getText());
        addTextFormatter(textJob);

        // Livshistorie
        tipLiv.setText(generalInfoHashMap.get("Livshistorie").getDescription());
        textLiv.setText(generalInfoHashMap.get("Livshistorie").getText());
        addTextFormatter(textLiv);

        // Helbredsoplysninger
        tipHelbred.setText(generalInfoHashMap.get("Helbredsoplysninger").getDescription());
        textHelbred.setText(generalInfoHashMap.get("Helbredsoplysninger").getText());
        addTextFormatter(textHelbred);

        // Hjælpemidler
        tipHjaelp.setText(generalInfoHashMap.get("Hjælpemidler").getDescription());
        textHjaelp.setText(generalInfoHashMap.get("Hjælpemidler").getText());
        addTextFormatter(textHjaelp);

        // Boligens indretning
        tipBolig.setText(generalInfoHashMap.get("Boligens indretning").getDescription());
        textBolig.setText(generalInfoHashMap.get("Boligens indretning").getText());
        addTextFormatter(textBolig);

        // Netværk
        tipNetvaerk.setText(generalInfoHashMap.get("Netværk").getDescription());
        textNetvaerk.setText(generalInfoHashMap.get("Netværk").getText());
        addTextFormatter(textNetvaerk);
    }

    public void handleLogout(ActionEvent actionEvent) {
        if (checkIfSaved())
            SceneManager.logout();
    }

    public void saveHealthCondition(ActionEvent actionEvent) {
        resetHealthFields();
        healthUnsavedChanges = false;
    }

    public void onHealthSaveAsSelection(ActionEvent actionEvent) {
        healthSaveButton.setDisable(false);
        int index = cbHealthSaveAs.getSelectionModel().getSelectedIndex();
        switch (index){
            case 0 -> {
                healthPotentialBox.setDisable(false);
                healthExtendedBox.setDisable(false);
            }
            case 1 -> {
                healthPotentialBox.setDisable(false);
                healthExtendedBox.setDisable(true);
            }
        }
    }

    private void resetHealthFields(){
        cbHealthSaveAs.getSelectionModel().clearSelection();
        cbHealthExpectedLevel.getSelectionModel().clearSelection();
        healthSaveButton.setDisable(true);
        healthPotentialBox.setDisable(true);
        healthExtendedBox.setDisable(true);
        lblHealthCategory.setText("");
        lblHealthSubCategory.setText("");
        txtHealthProfNote.clear();
        txtHealthCurrentAssessment.clear();
    }

}
