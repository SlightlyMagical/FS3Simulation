package be;

import be.Categories.FunctionalAbility;
import be.Categories.GeneralInfo;
import be.Categories.HealthCondition;

import java.util.HashMap;

public class Citizen {
    private final String firstName;
    private final String lastName;
    private HashMap<String, GeneralInfo> generalInfo;
    private HashMap<String, HealthCondition> healthConditions;
    private HashMap<String, FunctionalAbility> functionalAbilities;

    public Citizen(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public void setGeneralInfo(HashMap<String, GeneralInfo> generalInfo) {
        this.generalInfo = generalInfo;
    }

    public HashMap<String, HealthCondition> getHealthConditions() {
        return healthConditions;
    }

    public void setHealthConditions(HashMap<String, HealthCondition> healthConditions) {
        this.healthConditions = healthConditions;
    }

    public HashMap<String, FunctionalAbility> getFunctionalAbilities() {
        return functionalAbilities;
    }

    public void setFunctionalAbilities(HashMap<String, FunctionalAbility> functionalAbilities) {
        this.functionalAbilities = functionalAbilities;
    }
}
