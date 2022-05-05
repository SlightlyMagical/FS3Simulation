package be.Categories;

import be.enums.Status;

public class FunctionalAbility {
    private final String name;
    private Status status;
    private int currentLevel;
    private int expectedLevel;
    private String professionalNote;
    private String taskExecution;
    private boolean executionLimitation;
    private String citizenGoal;


    public FunctionalAbility(String name, Status status, int currentLevel, int expectedLevel, String professionalNote, String taskExecution, boolean executionLimitation, String citizenGoal) {
        this.name = name;
        this.status = status;
        this.currentLevel = currentLevel;
        this.expectedLevel = expectedLevel;
        this.professionalNote = professionalNote;
        this.taskExecution = taskExecution;
        this.executionLimitation = executionLimitation;
        this.citizenGoal = citizenGoal;
    }

    public String getName() {
        return name;
    }
}
