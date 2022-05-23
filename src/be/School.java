package be;

import be.Usertypes.Student;
import be.Usertypes.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class School {
    private final String name;
    private int id;
    private final ObservableList<Student> students;
    private final ObservableList<Teacher> teachers;

    public School(String name, int id) {
        this.name = name;
        this.id = id;
        students = FXCollections.observableArrayList();
        teachers = FXCollections.observableArrayList();
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ObservableList<Student> getStudents() {
        return students;
    }

    public ObservableList<Teacher> getTeachers() {
        return teachers;
    }
}
