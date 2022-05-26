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

    /**
     * Requests the scene manager to show the login scene
     */
    public void handleLogout(ActionEvent actionEvent) {
        SceneManager.showLoginScene();
    }

    /**
     * Requests the scene manager to show the window for creating new citizen
     */
    public void handleCreate(ActionEvent actionEvent) {
        SceneManager.showNewCitizenWindow(null);
    }

    /**
     * Updates the selected citizen in citizen model, then requests the scene manager to show the citizen scene
     */
    public void handleEdit(ActionEvent actionEvent) {
        Citizen selectedCitizen = tvBank.getSelectionModel().getSelectedItem();
        if (selectedCitizen != null) {
            citizenModel.setCurrentCitizen(selectedCitizen);
            SceneManager.showCitizenOverview();
        }
        else
            DialogHandler.informationAlert(Messages.NO_CITIZEN_SELECTED);
    }

    /**
     * Checks if the currently logged in teacher is the one who created the citizen, and prompts for confirmation of
     * the deletion. Passes the delete request on in the system if yes
     */
    public void handleDelete(ActionEvent actionEvent) {
        Citizen selectedCitizen = tvBank.getSelectionModel().getSelectedItem();
        if (selectedCitizen != null) {
            if (selectedCitizen.getTeacherID() == teacherModel.getCurrentTeacher().getId()) {
                if (DialogHandler.confirmationAlert(Messages.CONFIRM_DELETE)) {
                    citizenModel.deleteCitizen(selectedCitizen);
                    teacherModel.getTemplateCitizens().remove(selectedCitizen);
                }
            } else
                DialogHandler.informationAlert("Du har ikke ret til at slette denne borger");
        } else
            DialogHandler.informationAlert(Messages.NO_CITIZEN_SELECTED);
    }

    /**
     * Creates a copy of the selected citizen - both in the citizen bank
     */
    public void handleCreateCopy(ActionEvent actionEvent) {
        Citizen selectedCitizen = tvBank.getSelectionModel().getSelectedItem();
        if (selectedCitizen != null) {
            teacherModel.createCitizenCopy(selectedCitizen, true);
        }
        else
            DialogHandler.informationAlert(Messages.NO_CITIZEN_SELECTED);
    }

    /**
     * Creates a copy of the selected citizen - the original in the citizen bank and the copy in "assigned to students"
     */
    public void handleAssignToStudent(ActionEvent actionEvent) {
        Citizen selectedCitizen = tvBank.getSelectionModel().getSelectedItem();
        if (selectedCitizen != null) {
            teacherModel.createCitizenCopy(selectedCitizen, false);
        }
        else
            DialogHandler.informationAlert(Messages.NO_CITIZEN_SELECTED);
    }

    /**
     * Updates the selected citizen in citizen model, then requests the scene manager to show the citizen scene
     */
    public void handleAssignedEdit(ActionEvent actionEvent) {
        Citizen selectedCitizen = tvAssigned.getSelectionModel().getSelectedItem();
        if (selectedCitizen != null) {
            citizenModel.setCurrentCitizen(selectedCitizen);
            SceneManager.showCitizenOverview();
        }
        else
            DialogHandler.informationAlert(Messages.NO_CITIZEN_SELECTED);
    }

    /**
     * Prompts for confirmation, then deletes the selected citizen
     */
    public void handleAssignedDelete(ActionEvent actionEvent) {
        Citizen selectedCitizen = tvAssigned.getSelectionModel().getSelectedItem();
        if (DialogHandler.confirmationAlert(Messages.CONFIRM_DELETE)){
            citizenModel.deleteCitizen(selectedCitizen);
            teacherModel.getStudentCitizens().remove(selectedCitizen);
        }
    }

    /**
     * Creates a copy of the selected citizen - the original from "assigned to students" and the copy in the citizen bank
     */
    public void handleAssignedCopy(ActionEvent actionEvent) {
        Citizen selectedCitizen = tvAssigned.getSelectionModel().getSelectedItem();
        if (selectedCitizen != null) {
            teacherModel.createCitizenCopy(selectedCitizen, true);
        }
        else
            DialogHandler.informationAlert(Messages.NO_CITIZEN_SELECTED);
    }

    /**
     * Updates the currently selected citizen in citizen model and request the scene manager to show the
     * "assign to students" window
     */
    public void handleAssignedStudents(ActionEvent actionEvent) {
        Citizen selectedCitizen = tvAssigned.getSelectionModel().getSelectedItem();
        if (selectedCitizen != null) {
            citizenModel.setCurrentCitizen(selectedCitizen);
            SceneManager.showAssignStudentsWindow();
        }
    }

    /**
     * Checks if fields are filled out and if passwords matches, warns user if not.
     * Creates a new student with the input information otherwise and passes it to be created in the database.
     * Displays a warning to the user if the username is already taken.
     * Clears the input fields on a successful creation
     */
    public void handleStudentCreateUser(ActionEvent actionEvent) {
        String username = txtUsername.getText().toLowerCase().trim();
        String password = txtPassword.getText().trim();
        String repeatPassword = txtRepeatpassword.getText().trim();

        if (username.isBlank() || password.isBlank() || repeatPassword.isBlank()) {
            DialogHandler.informationAlert(Messages.EMPTY_FIELDS);
        }
        else if (!password.equals(repeatPassword)) {
            DialogHandler.informationAlert(Messages.PASSWORD_NOT_MATCHING);
        }
        else {
            Student student = new Student(-1, username, teacherModel.getCurrentTeacher().getSchoolID(), 3);
            student.setPassword(password);
            try {
                if (ModelManager.getInstance().getUserModel().createUser(student, password)) {
                    DialogHandler.informationAlert(Messages.USER_CREATION_SUCCESSFUL);
                    teacherModel.getStudents().add(student);
                    txtUsername.clear();
                    txtPassword.clear();
                    txtRepeatpassword.clear();
                }
                else {
                    DialogHandler.informationAlert(Messages.USERNAME_TAKEN);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Updates the assigned students table when a citizen is selected
     */
    public void showAssignedStudents(MouseEvent mouseEvent) {
        tvAssignedStudent.setItems(tvAssigned.getSelectionModel().getSelectedItem().getAssignedStudents());
    }
}
