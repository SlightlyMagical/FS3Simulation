package be.Usertypes;

public class User {
    private final int id;
    private final int userType;
    private final String username;


    public User(int id, int userType, String username) {
        this.id = id;
        this.userType = userType;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public int getUserType() {
        return userType;
    }

    public String getUsername() {
        return username;
    }
}
