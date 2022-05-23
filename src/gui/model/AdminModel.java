package gui.model;

import be.School;
import be.Usertypes.Admin;
import be.Usertypes.Student;
import be.Usertypes.Teacher;
import bll.BLLManager;
import bll.IBLLManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class AdminModel {
    private final ObservableList<Admin> admins;
    private final ObservableList<School> schools;

    IBLLManager bllManager;

    private School currentSchool;

    public AdminModel() throws IOException {
        this.bllManager = new BLLManager();
        this.admins = FXCollections.observableArrayList(bllManager.getAdmins());
        this.schools = FXCollections.observableArrayList();

    }

    public ObservableList<Admin> getAdmins() {
        return admins;
    }

    public ObservableList<School> getSchools() {
        return schools;
    }

    public boolean createNewUser(String username, String password, int userType){
        switch (userType){
            case 1 -> {
                Admin admin = new Admin(-1, username, userType);
                if (bllManager.createUser(admin, password))
                    admins.add(admin);
                else
                    return false;
            }
            case 2 -> {
                Teacher teacher = new Teacher(-1, username, currentSchool.getId(), userType);
                if (bllManager.createUser(teacher, password))
                    currentSchool.getTeachers().add(teacher);
                else
                    return false;
            }
            case 3 -> {
                Student student = new Student(-1, username, currentSchool.getId(), userType);
                student.setPassword(password);
                if (bllManager.createUser(student, password))
                    currentSchool.getStudents().add(student);
                else
                    return false;
            }
        }
        return true;
    }

    public boolean createNewSchool(School school) {
        if (bllManager.createSchool(school)){
            schools.add(school);
            return true;
        }
        return false;
    }

    public void deleteUser(int userID){
        bllManager.deleteUser(userID);
    }

    public void deleteSchool(School school){
        bllManager.deleteSchool(school.getId());
        schools.remove(school);
    }

    public School getCurrentSchool() {
        return currentSchool;
    }

    public void setCurrentSchool(School currentSchool) {
        this.currentSchool = currentSchool;
    }

    public void getSchoolsFromDatabase(){
        schools.clear();
        schools.addAll(bllManager.getSchools());
    }
}