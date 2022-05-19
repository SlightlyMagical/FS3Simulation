package be.Categories;

import be.enums.Status;

public class FunctionalAbility {
    private int id;
    private int catID;
    private final String name;
    private Status status;
    private int currentLevel;
    private int expectedLevel;
    private String professionalNote;
    private String taskExecution;
    private String executionLimitation;
    private String citizenGoal;
    private String observation;


    public FunctionalAbility(int id, int catID, String name) {
        this.name = name;
        this.id = id;
        this.catID = catID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCatID() {
        return catID;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getExpectedLevel() {
        return expectedLevel;
    }

    public void setExpectedLevel(int expectedLevel) {
        this.expectedLevel = expectedLevel;
    }

    public String getProfessionalNote() {
        return professionalNote;
    }

    public void setProfessionalNote(String professionalNote) {
        this.professionalNote = professionalNote;
    }

    public String getTaskExecution() {
        return taskExecution;
    }

    public void setTaskExecution(String taskExecution) {
        this.taskExecution = taskExecution;
    }

    public String getExecutionLimitation() {
        return executionLimitation;
    }

    public void setExecutionLimitation(String executionLimitation) {
        this.executionLimitation = executionLimitation;
    }

    public String getCitizenGoal() {
        return citizenGoal;
    }

    public void setCitizenGoal(String citizenGoal) {
        this.citizenGoal = citizenGoal;
    }

    public String getName() {
        return name;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
}
