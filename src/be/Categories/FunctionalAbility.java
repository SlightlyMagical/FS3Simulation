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
    private boolean executionLimitation;
    private String citizenGoal;


    public FunctionalAbility(int id, int catID, String name) {
        this.name = name;
        this.id = id;
        this.catID = catID;
    }

    public String getName() {
        return name;
    }
}
