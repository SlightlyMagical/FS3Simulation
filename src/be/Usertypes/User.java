package be.Usertypes;

public class User {
    private int id;
    private final int userType;
    private final String username;
    private final int schoolID;


    public User(int id, int userType, String username, int schoolID) {
        this.id = id;
        this.userType = userType;
        this.username = username;
        this.schoolID = schoolID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserType() {
        return userType;
    }

    public String getUsername() {
        return username;
    }

    public int getSchoolID() {
        return schoolID;
    }

}
