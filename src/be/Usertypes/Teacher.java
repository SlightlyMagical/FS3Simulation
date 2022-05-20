package be.Usertypes;

public class Teacher extends User{
    private final int schoolID;

    public Teacher(int id, String username, int schoolID, int userType) {
        super(id, userType, username);
        this.schoolID = schoolID;
    }

    public int getSchoolID() {
        return schoolID;
    }
}
