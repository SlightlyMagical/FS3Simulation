package be.Usertypes;

public class Student extends User{
    private String password;

    public Student(int id, String username, int schoolID, int userType) {
        super(id, userType, username, schoolID);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
