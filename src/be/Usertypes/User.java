package be.Usertypes;

import be.Citizen;
import javafx.collections.ObservableList;

public class User {
    private final int id;
    private final int userType;
    private final String firstName;
    private final String lastName;
    private ObservableList<Citizen> Citizens;

    public User(int id, int userType, String firstName, String lastName) {
        this.id = id;
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public int getUserType() {
        return userType;
    }

    public ObservableList<Citizen> getCitizens() {
        return Citizens;
    }

    public void setCitizens(ObservableList<Citizen> citizens) {
        Citizens = citizens;
    }
}
