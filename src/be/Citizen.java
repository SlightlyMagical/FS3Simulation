package be;

import be.Categories.FunctionalAbility;
import be.Categories.GeneralInfo;
import be.Categories.HealthCondition;

import java.util.HashMap;

public class Citizen {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final HashMap<String, GeneralInfo> generalInfo;
    private final HashMap<String, HealthCondition> healthConditions;
    private final HashMap<String, FunctionalAbility> functionalAbilities;

    public Citizen(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.generalInfo = new HashMap<>();
        this.healthConditions = new HashMap<>();
        this.functionalAbilities = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + "" + lastName;
    }

    public HashMap<String, GeneralInfo> getGeneralInfo() {
        return generalInfo;
    }

    public void addGeneralInfo(GeneralInfo generalInfo) {
        this.generalInfo.put(generalInfo.getName(), generalInfo);
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

}
