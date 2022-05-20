package be.Usertypes;

public class Student extends User{
    private final int schoolID;
    private String password;

    public Student(int id, String username, int schoolID, int userType) {
        super(id, userType, username);
        this.schoolID = schoolID;
    }

    public int getSchoolID() {
        return schoolID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
