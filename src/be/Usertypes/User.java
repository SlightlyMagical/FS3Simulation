package be.Usertypes;

public class User {
    private final int id;
    private final int userType;
    private final String firstName;
    private final String lastName;

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
}
