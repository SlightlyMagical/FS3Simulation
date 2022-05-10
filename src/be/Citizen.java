package be;

import be.Categories.FunctionalAbility;
import be.Categories.GeneralInfo;
import be.Categories.HealthCondition;
import be.Categories.InfoTemplates;

import java.util.HashMap;

public class Citizen {
    private final int id;
    private final String firstName;
    private final String lastName;
    private HashMap<String, GeneralInfo> generalInfo;
    private final HashMap<String, HealthCondition> healthConditions;
    private final HashMap<String, FunctionalAbility> functionalAbilities;

    public Citizen(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.healthConditions = InfoTemplates.getHealthConditionHashMap();
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

}
