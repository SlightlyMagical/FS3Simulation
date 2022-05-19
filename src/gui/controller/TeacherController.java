package gui.controller;

import be.Citizen;
import be.Usertypes.Student;
import gui.SceneManager;
import gui.model.CitizenModel;
import gui.model.ModelManager;
import gui.model.TeacherModel;
import gui.model.util.DialogHandler;
import gui.model.util.Messages;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherController implements Initializable {
    public TableView<Citizen> tvBank;
    public TableColumn<String, Citizen> tcBankFirstName;
    public TableColumn<String, Citizen> tcBankLastName;
    public TableView<Citizen> tvAssigned;
    public TableColumn<String, Citizen> tcAssignedFirstName;
    public TableColumn<String, Citizen> tcAssignedLastName;
    public TableView<Student> tvAssignedStudent;
    public TableColumn<String, Student> tcAssignedStudentUser;
    public TableColumn<String, Student> tcAssignedStudentPassword;
    public TableView<Student> tvStudentView;
    public TableColumn<String, Student> tcStudentUser;
    public TableColumn<String, Student> tcStudentPassword;
    public TextField txtUsername;
    public PasswordField txtPassword;
    public PasswordField txtRepeatpassword;

    private TeacherModel teacherModel;
    private CitizenModel citizenModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.teacherModel = ModelManager.getInstance().getTeacherModel();
            this.citizenModel = ModelManager.getInstance().getCitizenModel();
            tcBankFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            tcBankLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            tvBank.setItems(teacherModel.getTemplateCitizens());

            tcAssignedFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            tcAssignedLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            tvAssigned.setItems(teacherModel.getStudentCitizens());

            tcStudentUser.setCellValueFactory(new PropertyValueFactory<>("username"));
            tcStudentPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
            tvStudentView.setItems(teacherModel.getStudents());

            tcAssignedStudentUser.setCellValueFactory(new PropertyValueFactory<>("username"));
            tcAssignedStudentPassword.setCellValueFactory(new PropertyValueFactory<>("password"));


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void handleLogout(ActionEvent actionEvent) {
        SceneManager.logout();
    }

    public void handleCreate(ActionEvent actionEvent) {
        SceneManager.showNewCitizenWindow(null);
    }

    public void handleEdit(ActionEvent actionEvent) {
        Citizen selectedCitizen = tvBank.getSelectionModel().getSelectedItem();
        if (selectedCitizen != null) {
            citizenModel.setCurrentCitizen(selectedCitizen);
            SceneManager.showCitizenOverview();
        }
        else
            DialogHandler.informationAlert(Messages.NO_CITIZEN_SELECTED);
    }

    public void handleDelete(ActionEvent actionEvent) {
    }

    public void handleCreateCopy(ActionEvent actionEvent) {
        Citizen selectedCitizen = tvBank.getSelectionModel().getSelectedItem();
        if (selectedCitizen != null) {
            teacherModel.createCitizenCopy(selectedCitizen, true);
        }
        else
            DialogHandler.informationAlert(Messages.NO_CITIZEN_SELECTED);
    }

    public void handleAssignToStudent(ActionEvent actionEvent) {
        Citizen selectedCitizen = tvBank.getSelectionModel().getSelectedItem();
        if (selectedCitizen != null) {
            teacherModel.createCitizenCopy(selectedCitizen, false);
        }
        else
            DialogHandler.informationAlert(Messages.NO_CITIZEN_SELECTED);
    }

    public void handleAssignedEdit(ActionEvent actionEvent) {
        Citizen selectedCitizen = tvAssigned.getSelectionModel().getSelectedItem();
        if (selectedCitizen != null) {
            citizenModel.setCurrentCitizen(selectedCitizen);
            SceneManager.showCitizenOverview();
        }
        else
            DialogHandler.informationAlert(Messages.NO_CITIZEN_SELECTED);
    }

    public void handleAssignedDelete(ActionEvent actionEvent) {
    }

    public void handleAssignedCopy(ActionEvent actionEvent) {
        Citizen selectedCitizen = tvAssigned.getSelectionModel().getSelectedItem();
        if (selectedCitizen != null) {
            teacherModel.createCitizenCopy(selectedCitizen, true);
        }
        else
            DialogHandler.informationAlert(Messages.NO_CITIZEN_SELECTED);
    }

    public void handleAssignedStudents(ActionEvent actionEvent) {
        Citizen selectedCitizen = tvAssigned.getSelectionModel().getSelectedItem();
        if (selectedCitizen != null) {
            citizenModel.setCurrentCitizen(selectedCitizen);
            SceneManager.showAssignStudentsWindow();
        }
    }

    public void handleStudentCreateUser(ActionEvent actionEvent) {
    }


    public void showAssignedStudents(MouseEvent mouseEvent) {
        tvAssignedStudent.setItems(tvAssigned.getSelectionModel().getSelectedItem().getAssignedStudents());
    }
}
