package gui.controller;

import be.Categories.FunctionalAbility;
import be.Categories.HealthCondition;
import gui.model.CitizenModel;
import gui.model.ModelManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NotRelevantCatController implements Initializable {
    @FXML
    private TableView<HealthCondition> tvHealthCategories;
    @FXML
    private TableColumn<String, HealthCondition> tcHealthCategories;
    @FXML
    private TableView<FunctionalAbility> tvFunctionCategories;
    @FXML
    private TableColumn<String, FunctionalAbility> tcFunctionCategories;

    private CitizenModel citizenModel;

    /**
     * Changes the status of the selected health condition or functional ability from "Not relevant" to null and
     * passes the change to be saved in the data base
     */
    public void handleConfirm(ActionEvent actionEvent) {
        HealthCondition healthCondition = tvHealthCategories.getSelectionModel().getSelectedItem();
        FunctionalAbility functionalAbility = tvFunctionCategories.getSelectionModel().getSelectedItem();
        if(healthCondition != null){
            healthCondition.setStatus(null);
            citizenModel.getNotRelevantHealth().remove(healthCondition);
            ArrayList<HealthCondition> healthConditions = new ArrayList<>();
            healthConditions.add(healthCondition);
            citizenModel.saveHealthConditions(healthConditions);
        }
        else if (functionalAbility != null){
            functionalAbility.setStatus(null);
            citizenModel.getNotRelevantFunctions().remove(functionalAbility);
            ArrayList<FunctionalAbility> functionalAbilities = new ArrayList<>();
            functionalAbilities.add(functionalAbility);
            citizenModel.saveFunctionalAbilities(functionalAbilities);
        }

    }

    /**
     * Closes the current window
     */
    public void handleCancel(ActionEvent actionEvent) {
        ((Stage)(tvHealthCategories.getScene().getWindow())).close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenModel = ModelManager.getInstance().getCitizenModel();

            tcHealthCategories.setCellValueFactory(new PropertyValueFactory<>("name"));
            tvHealthCategories.setItems(citizenModel.getNotRelevantHealth());

            tcFunctionCategories.setCellValueFactory(new PropertyValueFactory<>("name"));
            tvFunctionCategories.setItems(citizenModel.getNotRelevantFunctions());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clears selection of other table when a health condition is selected
     */
    public void handleSelectHealth(MouseEvent mouseEvent) {
        tvFunctionCategories.getSelectionModel().clearSelection();
    }

    /**
     * Clears selection of other table when a functional ability is selected
     */
    public void handleSelectFunction(MouseEvent mouseEvent) {
        tvHealthCategories.getSelectionModel().clearSelection();
    }
}
