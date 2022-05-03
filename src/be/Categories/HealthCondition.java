package be.Categories;

import be.enums.Status;

public class HealthCondition {
    private final String name;
    private String professionalNote;
    private String currentAssessment;
    private String expectedLevel;
    private Status status;

    public HealthCondition(String name) {
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
}
