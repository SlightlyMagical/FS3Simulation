package gui.controller;

import be.Usertypes.Student;
import gui.model.CitizenModel;
import gui.model.ModelManager;
import gui.model.TeacherModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AssignStudentsController implements Initializable {
    @FXML
    private TableView<Student> tvAllStudents;
    @FXML
    private TableColumn<String, Student> tcAllStudents;
    @FXML
    private TableView<Student> tvAssignedStudents;
    @FXML
    private TableColumn<String, Student> tcAssignedStudents;

    private ObservableList<Student> allStudents;
    private ObservableList<Student> assignedStudents;

    private CitizenModel citizenModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            TeacherModel teacherModel = ModelManager.getInstance().getTeacherModel();
            citizenModel = ModelManager.getInstance().getCitizenModel();

            allStudents = FXCollections.observableArrayList(teacherModel.getStudents());
            assignedStudents = FXCollections.observableArrayList(citizenModel.getCurrentCitizen().getAssignedStudents());

            ArrayList<Student> studentsToRemove = new ArrayList<>();
            for (Student s : assignedStudents){
                for (Student student : allStudents){
                    if (s.getId() == student.getId())
                        studentsToRemove.add(student);
                }
            }
            allStudents.removeAll(studentsToRemove);

            tcAllStudents.setCellValueFactory(new PropertyValueFactory<>("username"));
            tvAllStudents.setItems(allStudents);

            tcAssignedStudents.setCellValueFactory(new PropertyValueFactory<>("username"));
            tvAssignedStudents.setItems(assignedStudents);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Moves the selected student from list of all students to the list of assigned students
     */
    public void handleAssign(ActionEvent actionEvent) {
        Student selection = tvAllStudents.getSelectionModel().getSelectedItem();
        if (selection != null){
            allStudents.remove(selection);
            assignedStudents.add(selection);
        }
    }

    /**
     * Moves the selected student from list of assigned students to the list of all students
     */
    public void handleUnassign(ActionEvent actionEvent) {
        Student selection = tvAssignedStudents.getSelectionModel().getSelectedItem();
        if (selection != null){
            assignedStudents.remove(selection);
            allStudents.add(selection);
        }
    }

    /**
     * Closes the window without saving any changes
     */
    public void handleCancel(ActionEvent actionEvent) {
        ((Stage)(tvAllStudents.getScene().getWindow())).close();
    }

    /**
     * Saves the assigned students to the citizen and closes with window
     */
    public void handleConfirm(ActionEvent actionEvent) {
        citizenModel.getCurrentCitizen().getAssignedStudents().clear();
        citizenModel.getCurrentCitizen().getAssignedStudents().addAll(assignedStudents);
        citizenModel.changeAssignedStudents();
        ((Stage)(tvAllStudents.getScene().getWindow())).close();
    }


}
