package be.Usertypes;

public class Student extends User{
    private final int schoolID;
    private String username;
    private String password;

    public Student(int id, String firstName, String lastName, int schoolID, int userType) {
        super(id, userType, firstName, lastName);
        this.schoolID = schoolID;
    }

    public int getSchoolID() {
        return schoolID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
