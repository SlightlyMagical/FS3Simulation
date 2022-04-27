package be.Usertypes;

public class Student extends User{
    private final String school;

    public Student(int id, String name, String school) {
        super(id, name);
        this.school = school;
    }

    public String getSchool() {
        return school;
    }
}
