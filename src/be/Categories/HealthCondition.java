package be.Categories;

import be.enums.Status;

public class HealthCondition {
    private final int id;
    private final int catID;
    private final String name;
    private String professionalNote;
    private String currentAssessment;
    private String expectedLevel;
    private Status status;

    public HealthCondition(int id, int catID, String name) {
        this.id = id;
        this.catID = catID;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public String getProfessionalNote() {
        return professionalNote;
    }

    public void setProfessionalNote(String professionalNote) {
        this.professionalNote = professionalNote;
    }

    public String getCurrentAssessment() {
        return currentAssessment;
    }

    public void setCurrentAssessment(String currentAssessment) {
        this.currentAssessment = currentAssessment;
    }

    public String getExpectedLevel() {
        return expectedLevel;
    }

    public void setExpectedLevel(String expectedLevel) {
        this.expectedLevel = expectedLevel;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getCatID() {
        return catID;
    }
}
