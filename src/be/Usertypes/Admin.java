package be.Usertypes;

public class Admin extends User {

    public Admin(int id, String firstName, String lastName, int userType) {
        super(id, userType, firstName, lastName);
    }
}
