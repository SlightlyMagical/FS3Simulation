package gui.model;

import be.Citizen;
import be.Usertypes.Teacher;
import be.Usertypes.User;
import bll.BLLManager;
import bll.IBLLManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;

public class TeacherModel {
    private IBLLManager bllManager;
    private Teacher currentTeacher;

    private ObservableList<Citizen> templateCitizens;
    private ObservableList<Citizen> studentCitizens;


    public TeacherModel() throws IOException {
        this.bllManager = new BLLManager();
        this.templateCitizens = FXCollections.observableArrayList();
        this.studentCitizens = FXCollections.observableArrayList();
    }

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

    public void setCurrentTeacher(Teacher currentTeacher) {
        this.currentTeacher = currentTeacher;
        getCitizensFromDatabase();
    }

    public ObservableList<Citizen> getTemplateCitizens() {
        return templateCitizens;
    }

    public ObservableList<Citizen> getStudentCitizens() {
        return studentCitizens;
    }
}
