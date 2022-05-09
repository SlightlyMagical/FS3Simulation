package gui.controller;

import be.Categories.GeneralInfo;
import be.Citizen;
import gui.SceneManager;
import gui.model.CitizenModel;
import gui.model.DialogHandler;
import gui.model.Messages;
import gui.model.ModelManager;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;

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

    private CitizenModel citizenModel;
    private Citizen currentCitizen;

    private boolean savedChanges = false;

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
    }


    public void goBack(ActionEvent actionEvent) {
        if(!savedChanges){
            if( DialogHandler.confirmationAlert(Messages.UNSAVED_CHANGES)){
                SceneManager.showStudentScene();
            }
        }else{
            SceneManager.showStudentScene();
        }
    }


    public void onSave(ActionEvent actionEvent) {
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

        savedChanges = true;
    }

    public void setUpGeneralInfo(){
        HashMap<String, GeneralInfo> generalInfoHashMap = currentCitizen.getGeneralInfo();

        // Mestring
        tipMestring.setText(generalInfoHashMap.get("Mestring").getDescription());
        textMestring.setText(generalInfoHashMap.get("Mestring").getText());

        // Motivation
        tipMotivation.setText(generalInfoHashMap.get("Motivation").getDescription());
        textMotivation.setText(generalInfoHashMap.get("Motivation").getText());

        // Ressourcer
        tipRessourcer.setText(generalInfoHashMap.get("Ressourcer").getDescription());
        textRessourcer.setText(generalInfoHashMap.get("Ressourcer").getText());

        // Roller
        tipRoller.setText(generalInfoHashMap.get("Roller").getDescription());
        textRoller.setText(generalInfoHashMap.get("Roller").getText());

        // Vaner
        tipVaner.setText(generalInfoHashMap.get("Vaner").getDescription());
        textVaner.setText(generalInfoHashMap.get("Vaner").getText());

        // Job
        tipJob.setText(generalInfoHashMap.get("Uddannelse og job").getDescription());
        textJob.setText(generalInfoHashMap.get("Uddannelse og job").getText());

        // Livshistorie
        tipLiv.setText(generalInfoHashMap.get("Livshistorie").getDescription());
        textLiv.setText(generalInfoHashMap.get("Livshistorie").getText());

        // Helbredsoplysninger
        tipHelbred.setText(generalInfoHashMap.get("Helbredsoplysninger").getDescription());
        textHelbred.setText(generalInfoHashMap.get("Helbredsoplysninger").getText());

        // Hjælpemidler
        tipHjaelp.setText(generalInfoHashMap.get("Hjælpemidler").getDescription());
        textHjaelp.setText(generalInfoHashMap.get("Hjælpemidler").getText());

        // Boligens indretning
        tipBolig.setText(generalInfoHashMap.get("Boligens indretning").getDescription());
        textBolig.setText(generalInfoHashMap.get("Boligens indretning").getText());

        // Netværk
        tipNetvaerk.setText(generalInfoHashMap.get("Netværk").getDescription());
        textNetvaerk.setText(generalInfoHashMap.get("Netværk").getText());
    }

    public void handleTabChange(Event event) {
    }

    public void handleLogout(ActionEvent actionEvent) {
        SceneManager.logout();
    }
}
