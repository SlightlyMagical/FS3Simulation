package gui.model;

import be.Citizen;
import be.Usertypes.Student;
import be.Usertypes.Teacher;
import bll.BLLManager;
import bll.IBLLManager;
import gui.SceneManager;
import gui.model.util.DialogHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;

public class TeacherModel {
    private final IBLLManager bllManager;

    private final ObservableList<Citizen> templateCitizens;
    private final ObservableList<Citizen> studentCitizens;

    private final ObservableList<Student> students;

    private Teacher currentTeacher;

    public TeacherModel() throws IOException {
        this.bllManager = new BLLManager();
        this.templateCitizens = FXCollections.observableArrayList();
        this.studentCitizens = FXCollections.observableArrayList();
        this.students = FXCollections.observableArrayList();
    }

    public void setCurrentTeacher(Teacher currentTeacher) {
        this.currentTeacher = currentTeacher;
        getCitizensFromDatabase();
        getStudentsFromDatabase();
    }

    public Teacher getCurrentTeacher() {
        return currentTeacher;
    }

    /**
     * Requests all citizens of the school from the database and sorts them into "template" and "assigned"
     */
    public void getCitizensFromDatabase() {
        templateCitizens.clear();
        studentCitizens.clear();
        ArrayList<Citizen> citizens = bllManager.getCitizensOfSchool(currentTeacher.getSchoolID());
        for (Citizen c : citizens ) {
            if (c.isTemplate())
                templateCitizens.add(c);
            else
                studentCitizens.add(c);
        }
    }

    /**
     * Requests all students from the database
     */
    public void getStudentsFromDatabase(){
        students.clear();
        students.addAll(bllManager.getAllStudents(currentTeacher.getSchoolID()));
    }


    public ObservableList<Citizen> getTemplateCitizens() {
        return templateCitizens;
    }

    public ObservableList<Citizen> getStudentCitizens() {
        return studentCitizens;
    }

    /**
     * Passes the new citizen to be created in the database to the logic layer.
     */
    public void createNewCitizen(Citizen citizen) {
        citizen.setTeacherID(currentTeacher.getId());
        citizen.setIsTemplate(true);
        bllManager.createNewCitizen(citizen, currentTeacher.getSchoolID());
        templateCitizens.add(citizen);
    }

    /**
     * Handles creation of copies of citizens.
     */
    public void createCitizenCopy(Citizen citizen, boolean isTemplate){
        Citizen newCitizen = bllManager.createCitizenCopy(citizen, isTemplate, currentTeacher.getId());
        try {
            ModelManager.getInstance().getCitizenModel().setCurrentCitizen(newCitizen);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(isTemplate){
            if (DialogHandler.confirmationAlert("Vil du ændre navnet på den kopirede borger?")){
                SceneManager.showNewCitizenWindow(newCitizen);
            }
            templateCitizens.add(newCitizen);
        }
        else {
            studentCitizens.add(newCitizen);
            if (DialogHandler.confirmationAlert("Vil du tildele borgeren til elever med det samme?")){
                SceneManager.showAssignStudentsWindow();
            }
        }

    }

    public ObservableList<Student> getStudents() {
        return students;
    }
}
