package gui.model;

import be.Categories.GeneralInfo;
import be.Citizen;
import be.Usertypes.User;
import bll.BLLManager;
import bll.IBLLManager;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CitizenModel {
    IBLLManager bllManager;

    ObservableList<Citizen> allPatients = FXCollections.observableArrayList();
    // Static variable reference of single_instance
    // of type Singleton
    private static CitizenModel single_instance = null;


    private CitizenModel() throws IOException
    {
        bllManager = new BLLManager();
    }

    public static CitizenModel getInstance() {
        if (single_instance == null) {
            try {
                single_instance = new CitizenModel();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return single_instance;
    }

    public ObservableList<Citizen> getAllPatients(int schoolID) {
        allPatients.addAll(bllManager.getAllPatients(schoolID));
        return allPatients;
    }

    public void updatePatientGeneralInfo(Citizen selectedPatient) {
        bllManager.updatePatientGeneralInfo(selectedPatient);
    }
}
