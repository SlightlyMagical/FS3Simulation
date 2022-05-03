package be.Categories;

import be.enums.Status;

public class FunctionalAbility {
    private final String name;
    private Status status;
    private int currentLevel;
    private int expectedLevel;
    private String professionalNote;
    private String taskExecution;
    private String executionImportance;
    private String citizenGoal;


    public FunctionalAbility(String name) {
        this.name = name;
    }
}
