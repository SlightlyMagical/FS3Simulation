package be.Usertypes;

public class Student extends User{
    private final int schoolID;

    public Student(int id, String firstName, String lastName, int schoolID, int userType) {
        super(id, userType, firstName, lastName);
        this.schoolID = schoolID;
    }

    public int getSchoolID() {
        return schoolID;
    }
}
