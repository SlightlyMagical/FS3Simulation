package gui.controller;

import be.Categories.FunctionalAbility;
import be.Categories.GeneralInfo;
import be.Categories.HealthCondition;
import be.Categories.InfoTemplates;
import be.enums.Status;
import gui.SceneManager;
import gui.model.CitizenModel;
import gui.model.DialogHandler;
import gui.model.Messages;
import gui.model.ModelManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    public TextArea txtHealthProfNote;
    public TextArea txtHealthCurrentAssessment;
    public ComboBox<String> cbHealthExpectedLevel;
    public VBox healthCat1;
    public VBox healthCat2;
    public VBox healthCat3;
    public VBox healthCat4;
    public VBox healthCat5;
    public VBox healthCat6;
    public VBox healthCat7;
    public VBox healthCat8;
    public VBox healthCat9;
    public VBox healthCat10;
    public VBox healthCat11;
    public VBox healthCat12;
    public VBox functionCat1;
    public VBox functionCat2;
    public VBox functionCat3;
    public VBox functionCat4;
    public VBox functionCat5;
    public ComboBox<String> cbFunctionNniveau;
    public ComboBox<String> cbFunctionFniveau;
    public TextArea txtFunctionNote;
    public ComboBox<String> cbFunctionExecution;
    public ComboBox<String> cbFunctionLimitation;
    public TextArea txtFunctionWishes;
    public TextArea txtFunctionObservation;
    public Label lblFunctionCategory;

    private Label lastLabelHealth;
    private Label lastLabelFunction;

    private CitizenModel citizenModel;

    private HealthCondition selectedHealthCondition;
    private FunctionalAbility selectedFunctionalAbility;

    private boolean infoUnsavedChanges = false;
    private boolean healthUnsavedChanges = false;
    private boolean abilityUnsavedChanges = false;

    public CitizenController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.citizenModel = ModelManager.getInstance().getCitizenModel();

        } catch (IOException e) {
            e.printStackTrace();
        }
        setUpGeneralInfo();
        setUpHealthConditions();
        setUpFunctionalAbilities();
    }

    private void setUpHealthConditions() {
        cbHealthSaveAs.getItems().addAll("Aktivt", "Potentielt");
        cbHealthExpectedLevel.getItems().addAll("Mindskes", "Forbliver uændret", "Forsvinder");
        healthTextFormatter(txtHealthProfNote);
        healthTextFormatter(txtHealthCurrentAssessment);
        for (HealthCondition h : InfoTemplates.getHealthConditionArrayList()) {
            HealthCondition value = citizenModel.getCurrentCitizen().getHealthConditions().get(h.getName());
            if (value.getStatus() != Status.NOT_RELEVANT) {
                Label label = new Label(value.getName());
                label.setOnMouseClicked(event -> {
                    if (healthUnsavedChanges)
                        if (!DialogHandler.confirmationAlert(Messages.UNSAVED_CHANGES))
                            return;

                    if (lastLabelHealth != null)
                        lastLabelHealth.setStyle("-fx-font-weight: normal");
                    lastLabelHealth = label;
                    label.setStyle("-fx-font-weight: bold");
                    showConditionDetails(value);
                    healthUnsavedChanges = false;
                });
                switch (value.getCatID()) {
                    case 1 -> healthCat1.getChildren().add(label);
                    case 2 -> healthCat2.getChildren().add(label);
                    case 3 -> healthCat3.getChildren().add(label);
                    case 4 -> healthCat4.getChildren().add(label);
                    case 5 -> healthCat5.getChildren().add(label);
                    case 6 -> healthCat6.getChildren().add(label);
                    case 7 -> healthCat7.getChildren().add(label);
                    case 8 -> healthCat8.getChildren().add(label);
                    case 9 -> healthCat9.getChildren().add(label);
                    case 10 -> healthCat10.getChildren().add(label);
                    case 11 -> healthCat11.getChildren().add(label);
                    case 12 -> healthCat12.getChildren().add(label);
                }
            }
        }
    }

    private void showConditionDetails(HealthCondition healthCondition) {
        this.selectedHealthCondition = healthCondition;
        lblHealthCategory.setText(healthCondition.getName());
        Status status = healthCondition.getStatus();
        if (status == null)
            return;
        if (status == Status.ACTIVE)
            cbHealthSaveAs.getSelectionModel().select(0);
        else if (status == Status.POTENTIAL)
            cbHealthSaveAs.getSelectionModel().select(1);
        onHealthSaveAsSelection();

        txtHealthProfNote.setText(healthCondition.getProfessionalNote());
        txtHealthCurrentAssessment.setText(healthCondition.getCurrentAssessment());
        cbHealthExpectedLevel.getSelectionModel().select(healthCondition.getExpectedLevel());
    }

    private void setUpFunctionalAbilities() {
        cbFunctionNniveau.getItems().addAll("0", "1", "2", "3", "4");
        cbFunctionNniveau.setCellFactory(listView -> new StringImageCell());
        cbFunctionNniveau.setButtonCell(new StringImageCell());

        cbFunctionFniveau.getItems().addAll("0", "1", "2", "3", "4");
        cbFunctionFniveau.setCellFactory(listView -> new StringImageCell());
        cbFunctionFniveau.setButtonCell(new StringImageCell());

        cbFunctionExecution.getItems().addAll("Udfører selv", "Udfører dele selv", "Udfører ikke selv", "Ikke relevant");

        cbFunctionLimitation.getItems().addAll("Oplever ikke begrænsninger", "Oplever begrænsninger");

        for (FunctionalAbility f : InfoTemplates.getFunctionalAbilityArrayList()){
            FunctionalAbility value = citizenModel.getCurrentCitizen().getFunctionalAbilities().get(f.getName());
            if (value.getStatus() != Status.NOT_RELEVANT){
                Label label = new Label(value.getName());
                label.setStyle("-fx-wrap-text: true");
                label.setOnMouseClicked(event -> {
                    if (healthUnsavedChanges)
                        if (!DialogHandler.confirmationAlert(Messages.UNSAVED_CHANGES))
                            return;

                    if (lastLabelFunction != null)
                        lastLabelFunction.setStyle("-fx-font-weight: normal; -fx-wrap-text: true");
                    lastLabelFunction = label;
                    label.setStyle("-fx-font-weight: bold; -fx-wrap-text: true");
                    showAbilityDetails(value);
                    abilityUnsavedChanges = false;
                });
                switch (value.getCatID()) {
                    case 1 -> functionCat1.getChildren().add(label);
                    case 2 -> functionCat2.getChildren().add(label);
                    case 3 -> functionCat3.getChildren().add(label);
                    case 4 -> functionCat4.getChildren().add(label);
                    case 5 -> functionCat5.getChildren().add(label);
                }
            }
        }
    }

    private void showAbilityDetails(FunctionalAbility functionalAbility) {
        this.selectedFunctionalAbility = functionalAbility;
        lblFunctionCategory.setText(functionalAbility.getName());
        Status status = functionalAbility.getStatus();
        if (status == null)
            return;

        cbFunctionNniveau.getSelectionModel().select(functionalAbility.getCurrentLevel());
        cbFunctionFniveau.getSelectionModel().select(functionalAbility.getExpectedLevel());
        txtFunctionNote.setText(functionalAbility.getProfessionalNote());
        cbFunctionExecution.getSelectionModel().select(functionalAbility.getTaskExecution());
        cbFunctionLimitation.getSelectionModel().select(functionalAbility.getExecutionLimitation());
        txtFunctionWishes.setText(functionalAbility.getCitizenGoal());
        txtFunctionObservation.setText(functionalAbility.getObservation());

    }

    private boolean checkIfSaved() {
        if (infoUnsavedChanges || healthUnsavedChanges || abilityUnsavedChanges)
            return DialogHandler.confirmationAlert(Messages.UNSAVED_CHANGES);

        return true;
    }

    private void infoTextFormatter(TextArea textArea) {
        textArea.setTextFormatter(new TextFormatter<String>(change -> {
            infoUnsavedChanges = true;
            return change;
        }));
    }

    private void healthTextFormatter(TextArea textArea) {
        textArea.setTextFormatter(new TextFormatter<String>(change -> {
            healthUnsavedChanges = true;
            return change;
        }));
    }

    public void goBack(ActionEvent actionEvent) {
        if (checkIfSaved())
            SceneManager.showStudentScene();
    }

    public void saveGeneralInfo(ActionEvent actionEvent) {
        HashMap<String, GeneralInfo> generalInfoHashMap = citizenModel.getCurrentCitizen().getGeneralInfo();

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
        citizenModel.getCurrentCitizen().setGeneralInfo(generalInfoHashMap);

        citizenModel.updatePatientGeneralInfo(citizenModel.getCurrentCitizen());

        infoUnsavedChanges = false;

        DialogHandler.informationAlert(Messages.SAVE_SUCCESSFUL);
    }

    public void setUpGeneralInfo() {
        HashMap<String, GeneralInfo> generalInfoHashMap = citizenModel.getCurrentCitizen().getGeneralInfo();

        // Mestring
        tipMestring.setText(generalInfoHashMap.get("Mestring").getDescription());
        textMestring.setText(generalInfoHashMap.get("Mestring").getText());
        infoTextFormatter(textMestring);

        // Motivation
        tipMotivation.setText(generalInfoHashMap.get("Motivation").getDescription());
        textMotivation.setText(generalInfoHashMap.get("Motivation").getText());
        infoTextFormatter(textMotivation);

        // Ressourcer
        tipRessourcer.setText(generalInfoHashMap.get("Ressourcer").getDescription());
        textRessourcer.setText(generalInfoHashMap.get("Ressourcer").getText());

        // Roller
        tipRoller.setText(generalInfoHashMap.get("Roller").getDescription());
        textRoller.setText(generalInfoHashMap.get("Roller").getText());
        infoTextFormatter(textRoller);

        // Vaner
        tipVaner.setText(generalInfoHashMap.get("Vaner").getDescription());
        textVaner.setText(generalInfoHashMap.get("Vaner").getText());
        infoTextFormatter(textVaner);

        // Job
        tipJob.setText(generalInfoHashMap.get("Uddannelse og job").getDescription());
        textJob.setText(generalInfoHashMap.get("Uddannelse og job").getText());
        infoTextFormatter(textJob);

        // Livshistorie
        tipLiv.setText(generalInfoHashMap.get("Livshistorie").getDescription());
        textLiv.setText(generalInfoHashMap.get("Livshistorie").getText());
        infoTextFormatter(textLiv);

        // Helbredsoplysninger
        tipHelbred.setText(generalInfoHashMap.get("Helbredsoplysninger").getDescription());
        textHelbred.setText(generalInfoHashMap.get("Helbredsoplysninger").getText());
        infoTextFormatter(textHelbred);

        // Hjælpemidler
        tipHjaelp.setText(generalInfoHashMap.get("Hjælpemidler").getDescription());
        textHjaelp.setText(generalInfoHashMap.get("Hjælpemidler").getText());
        infoTextFormatter(textHjaelp);

        // Boligens indretning
        tipBolig.setText(generalInfoHashMap.get("Boligens indretning").getDescription());
        textBolig.setText(generalInfoHashMap.get("Boligens indretning").getText());
        infoTextFormatter(textBolig);

        // Netværk
        tipNetvaerk.setText(generalInfoHashMap.get("Netværk").getDescription());
        textNetvaerk.setText(generalInfoHashMap.get("Netværk").getText());
        infoTextFormatter(textNetvaerk);
    }

    public void handleLogout(ActionEvent actionEvent) {
        if (checkIfSaved())
            SceneManager.logout();
    }

    public void saveHealthCondition(ActionEvent actionEvent) {
        HealthCondition healthCondition = selectedHealthCondition;
        if (cbHealthSaveAs.getSelectionModel().getSelectedIndex() == 0)
            healthCondition.setStatus(Status.ACTIVE);
        else
            healthCondition.setStatus(Status.POTENTIAL);
        healthCondition.setProfessionalNote(txtHealthProfNote.getText());
        healthCondition.setCurrentAssessment(txtHealthCurrentAssessment.getText());
        healthCondition.setExpectedLevel(cbHealthExpectedLevel.getSelectionModel().getSelectedItem());
        if (citizenModel.saveHealthCondition(healthCondition)) {
            healthUnsavedChanges = false;
            DialogHandler.informationAlert(Messages.SAVE_SUCCESSFUL);
        } else
            DialogHandler.informationAlert(Messages.SAVE_UNSUCCESSFUL);

    }

    @FXML
    private void onHealthSaveAsSelection() {
        healthSaveButton.setDisable(false);
        healthUnsavedChanges = true;
        int index = cbHealthSaveAs.getSelectionModel().getSelectedIndex();
        switch (index) {
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

    @FXML
    private void onHealthExpectedChanged() {
        healthUnsavedChanges = true;
    }

    @FXML
    private void saveFunctionalAbility() {
        FunctionalAbility functionalAbility = selectedFunctionalAbility;
        functionalAbility.setStatus(Status.ACTIVE);
        functionalAbility.setCurrentLevel(cbFunctionNniveau.getSelectionModel().getSelectedIndex());
        functionalAbility.setExpectedLevel(cbFunctionFniveau.getSelectionModel().getSelectedIndex());
        functionalAbility.setProfessionalNote(txtFunctionNote.getText());
        functionalAbility.setTaskExecution(cbFunctionExecution.getSelectionModel().getSelectedItem());
        functionalAbility.setExecutionLimitation(cbFunctionLimitation.getSelectionModel().getSelectedItem());
        functionalAbility.setCitizenGoal(txtFunctionWishes.getText());
        if (citizenModel.saveFunctionalAbility(functionalAbility)) {
            abilityUnsavedChanges = false;
            DialogHandler.informationAlert(Messages.SAVE_SUCCESSFUL);
        } else
            DialogHandler.informationAlert(Messages.SAVE_UNSUCCESSFUL);
    }


    static class StringImageCell extends ListCell<String> {
        Label label;
        static HashMap<String, Image> pictures = new HashMap<>();

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (item == null || empty) {
                setItem(null);
                setGraphic(null);
            } else {
                setText(item);
                ImageView image = getImageView(item);
                label = new Label("", image);
                setGraphic(label);
            }
        }

        private static ImageView getImageView(String imageName) {
            ImageView imageView = null;
            switch (imageName) {
                case "0", "1", "2", "3", "4" -> {
                    if (!pictures.containsKey(imageName)) {
                        pictures.put(imageName, new Image(imageName + ".png"));
                    }
                    imageView = new ImageView(pictures.get(imageName));
                    imageView.setFitWidth(100);
                    imageView.setFitHeight(100);
                }
                default -> imageName = null;
            }
            return imageView;
        }
    }

}
