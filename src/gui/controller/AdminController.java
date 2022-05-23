package gui.controller;

import be.School;
import be.Usertypes.Admin;
import be.Usertypes.Student;
import be.Usertypes.Teacher;
import gui.SceneManager;
import gui.model.AdminModel;
import gui.model.ModelManager;
import gui.model.UserModel;
import gui.model.util.DialogHandler;
import gui.model.util.Messages;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    public TableView<Admin> tvAdminUsername;
    public TableColumn<String, Admin> tcAdmin;
    public TableView<School> tvSchoolName;
    public TableColumn<String, School> tcSchool;
    public TableView<Student> tvStudentlist;
    public TableColumn<String,Student> tcStudentUsername;
    public TableColumn<String,Student> tcStudentPassword;
    public TableView<Teacher> tvTeacherUsername;
    public TableColumn<String, Teacher> tcTeacher;

    private AdminModel adminModel;
    private UserModel userModel;

    private School selectedSchool;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            adminModel = ModelManager.getInstance().getAdminModel();
            userModel = ModelManager.getInstance().getUserModel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tcAdmin.setCellValueFactory(new PropertyValueFactory<>("username"));
        tvAdminUsername.setItems(adminModel.getAdmins());

        tcSchool.setCellValueFactory(new PropertyValueFactory<>("name"));
        tvSchoolName.setItems(adminModel.getSchools());

        tcStudentUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        tcStudentPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        tcTeacher.setCellValueFactory(new PropertyValueFactory<>("username"));
    }

    public void handleAdminCreate(ActionEvent actionEvent) {
        SceneManager.showNewUserWindow(1);
    }

    public void handleAdminDelete(ActionEvent actionEvent) {
        Admin selected = tvAdminUsername.getSelectionModel().getSelectedItem();
        if (selected != null)
            if (selected.getId() == userModel.getCurrentUser().getId())
                DialogHandler.informationAlert("Du kan ikke slette dit eget login");
            else if (DialogHandler.confirmationAlert(Messages.CONFIRM_DELETE)) {
                adminModel.deleteUser(selected.getId());
                adminModel.getAdmins().remove(selected);
            }
    }

    public void handleSchoolCreate(ActionEvent actionEvent) {
        String name = DialogHandler.inputDialog("Indtast navn for den nye skole:");
        if (!name.isBlank())
            if (!adminModel.createNewSchool(new School(name, -1)))
                DialogHandler.informationAlert("Der eksisterer allerede en skole med dette navn");
    }

    public void handleSchoolDelete(ActionEvent actionEvent) {
        School selected = tvSchoolName.getSelectionModel().getSelectedItem();
        if (selected != null)
            if (DialogHandler.confirmationAlert(Messages.CONFIRM_DELETE))
                adminModel.deleteSchool(selected);
    }

    public void handleStudentCreate(ActionEvent actionEvent) {
        if (selectedSchool == null)
            DialogHandler.informationAlert(Messages.SELECT_SCHOOL);
        else
            SceneManager.showNewUserWindow(3);
    }

    public void handleStudentDelete(ActionEvent actionEvent) {
        Student selected = tvStudentlist.getSelectionModel().getSelectedItem();
        if (selected != null)
            if (DialogHandler.confirmationAlert(Messages.CONFIRM_DELETE)) {
                adminModel.deleteUser(selected.getId());
                adminModel.getCurrentSchool().getStudents().remove(selected);
            }
    }

    public void handleTeacherCreate(ActionEvent actionEvent) {
        if (selectedSchool == null)
            DialogHandler.informationAlert(Messages.SELECT_SCHOOL);
        else
            SceneManager.showNewUserWindow(2);
    }

    public void handleTeacherDelete(ActionEvent actionEvent) {
        Teacher selected = tvTeacherUsername.getSelectionModel().getSelectedItem();
        if (selected != null)
            if (DialogHandler.confirmationAlert(Messages.CONFIRM_DELETE)) {
                adminModel.deleteUser(selected.getId());
                adminModel.getCurrentSchool().getTeachers().remove(selected);
            }
    }

    public void logout(ActionEvent actionEvent) {
        SceneManager.logout();
    }

    public void handleSchoolSelect(MouseEvent mouseEvent) {
        selectedSchool = tvSchoolName.getSelectionModel().getSelectedItem();
        tvStudentlist.setItems(selectedSchool.getStudents());
        tvTeacherUsername.setItems(selectedSchool.getTeachers());

        adminModel.setCurrentSchool(selectedSchool);
    }
}

