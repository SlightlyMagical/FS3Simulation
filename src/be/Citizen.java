package be;

import be.Categories.FunctionalAbility;
import be.Categories.GeneralInfo;
import be.Categories.HealthCondition;
import be.Categories.InfoTemplates;
import be.Usertypes.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;

public class Citizen {
    private int id;
    private String firstName;
    private String lastName;
    private boolean isTemplate;
    private int teacherID;
    private int schoolID;
    private HashMap<String, GeneralInfo> generalInfo;
    private final HashMap<String, HealthCondition> healthConditions;
    private final HashMap<String, FunctionalAbility> functionalAbilities;

    private final ObservableList<Student> assignedStudents;


    public Citizen(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.generalInfo = InfoTemplates.getGeneralInfoHashMap();
        this.healthConditions = InfoTemplates.getHealthConditionHashMap();
        this.functionalAbilities = InfoTemplates.getFunctionalAbilityHashMap();
        this.assignedStudents = FXCollections.observableArrayList();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public HashMap<String, GeneralInfo> getGeneralInfo() {
        return generalInfo;
    }

    public void setGeneralInfo(HashMap<String, GeneralInfo> generalInfoHashMap) {
        this.generalInfo = generalInfoHashMap;
    }

    public HashMap<String, HealthCondition> getHealthConditions() {
        return healthConditions;
    }

    public void addHealthCondition(HealthCondition healthCondition) {
        this.healthConditions.put(healthCondition.getName(), healthCondition);
    }

    public HashMap<String, FunctionalAbility> getFunctionalAbilities() {
        return functionalAbilities;
    }

    public void addFunctionalAbility(FunctionalAbility functionalAbility) {
        this.functionalAbilities.put(functionalAbility.getName(), functionalAbility);
    }

    public boolean isTemplate() {
        return isTemplate;
    }

    public void setIsTemplate(boolean template) {
        isTemplate = template;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public ObservableList<Student> getAssignedStudents() {
        return assignedStudents;
    }
}
