package gui.controller;

import be.Categories.GeneralInfo;
import be.Citizen;
import gui.SceneManager;
import gui.model.CitizenModel;
import gui.model.DialogHandler;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;

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
    Citizen selectedPatient ;

    private CitizenModel citizenModel = CitizenModel.getInstance();

    private boolean savedChanges = false;


    public void setCitizen(Citizen selectedItem) {
        this.selectedPatient = selectedItem;
        setUpTooltips();
    }

    public void setUpTooltips(){
        HashMap<String, GeneralInfo> patientHashmap = this.selectedPatient.getGeneralInfo();

        // Mestring
        tipMestring.setText(patientHashmap.get("Mestring").getDescription());
        //Mestring Text area
        textMestring.setText(patientHashmap.get("Mestring").getText());

        // Motivation
        tipMotivation.setText(patientHashmap.get("Motivation").getDescription());
        //Motivation Text area
        textMotivation.setText(patientHashmap.get("Motivation").getText());

        // Ressourcer
        tipRessourcer.setText(patientHashmap.get("Ressourcer").getDescription());
        // Ressourcer Text area
        textRessourcer.setText(patientHashmap.get("Ressourcer").getText());

        // Roller
        tipRoller.setText(patientHashmap.get("Roller").getDescription());
        // Roller Text area
        textRoller.setText(patientHashmap.get("Roller").getText());

        // Vaner
        tipVaner.setText(patientHashmap.get("Vaner").getDescription());
        // Vaner Text area
        textVaner.setText(patientHashmap.get("Vaner").getText());

        // Job
        tipJob.setText(patientHashmap.get("Uddannelse og job").getDescription());
        // Job Text area
        textJob.setText(patientHashmap.get("Uddannelse og job").getText());

        // Livshistorie
        tipLiv.setText(patientHashmap.get("Livshistorie").getDescription());
        // Livshistorie Text area
        textLiv.setText(patientHashmap.get("Livshistorie").getText());

        // Helbredsoplysninger
        tipHelbred.setText(patientHashmap.get("Helbredsoplysninger").getDescription());
        // Helbredsoplysninger Text area
        textHelbred.setText(patientHashmap.get("Helbredsoplysninger").getText());

        // Hjælpemidler
        tipHjaelp.setText(patientHashmap.get("Hjælpemidler").getDescription());
        // Hjælpemidler Text area
        textHjaelp.setText(patientHashmap.get("Hjælpemidler").getText());

        // Boligens indretning
        tipBolig.setText(patientHashmap.get("Boligens indretning").getDescription());
        // Boligens indretning Text area
        textBolig.setText(patientHashmap.get("Boligens indretning").getText());

        // Netværk
        tipNetvaerk.setText(patientHashmap.get("Netværk").getDescription());
        // Netværk Text area
        textNetvaerk.setText(patientHashmap.get("Netværk").getText());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onSave(ActionEvent actionEvent) {
        HashMap<String, GeneralInfo> patientHashmap = this.selectedPatient.getGeneralInfo();

        //Get specific info from hashmap (Motivation, Mestring ...)
        GeneralInfo newMestringInfo = patientHashmap.get("Mestring");
        //Set Text from text box
        newMestringInfo.setText(textMestring.getText());
        //Replace existing General info with new one
        patientHashmap.put("Mestring", newMestringInfo);
        //Repeat.

        //Get specific info from hashmap (Motivation, Mestring ...)
        GeneralInfo newMotivationInfo = patientHashmap.get("Motivation");
        //Set Text from text box
        newMotivationInfo.setText(textMotivation.getText());
        //Replace existing General info with new one
        patientHashmap.put("Motivation", newMotivationInfo);
        //Repeat.


        GeneralInfo newRessourcerInfo = patientHashmap.get("Ressourcer");
        newRessourcerInfo.setText(textRessourcer.getText());
        patientHashmap.put("Ressourcer", newRessourcerInfo);

        GeneralInfo newRollerInfo = patientHashmap.get("Roller");
        newRollerInfo.setText(textRoller.getText());
        patientHashmap.put("Roller", newRollerInfo);

        GeneralInfo newVanerInfo = patientHashmap.get("Vaner");
        newVanerInfo.setText(textVaner.getText());
        patientHashmap.put("Vaner", newVanerInfo);

        GeneralInfo newJobInfo = patientHashmap.get("Uddannelse og job");
        newJobInfo.setText(textJob.getText());
        patientHashmap.put("Uddannelse og job", newJobInfo);

        GeneralInfo newLivInfo = patientHashmap.get("Livshistorie");
        newLivInfo.setText(textLiv.getText());
        patientHashmap.put("Livshistorie", newLivInfo);

        GeneralInfo newHelbredInfo = patientHashmap.get("Helbredsoplysninger");
        newHelbredInfo.setText(textHelbred.getText());
        patientHashmap.put("Helbredsoplysninger", newHelbredInfo);

        GeneralInfo newHjaelpInfo = patientHashmap.get("Hjælpemidler");
        newHjaelpInfo.setText(textHjaelp.getText());
        patientHashmap.put("Hjælpemidler", newHjaelpInfo);

        GeneralInfo newBoligInfo = patientHashmap.get("Boligens indretning");
        newBoligInfo.setText(textBolig.getText());
        patientHashmap.put("Boligens indretning", newBoligInfo);

        GeneralInfo newNetvaerkInfo = patientHashmap.get("Netværk");
        newNetvaerkInfo.setText(textNetvaerk.getText());
        patientHashmap.put("Netværk", newNetvaerkInfo);

        //
        this.selectedPatient.setGeneralInfo(patientHashmap);

        citizenModel.updatePatientGeneralInfo(this.selectedPatient);

        savedChanges = true;

    }

    public void goBack(ActionEvent actionEvent) {
        if(!savedChanges){
           if( DialogHandler.confirmationAlert("Ændringerne er ikke gemt, ønsker du at fortsætte?")){
               SceneManager.showStudentScene();
           }
        }else{
            SceneManager.showStudentScene();
        }
    }
}
