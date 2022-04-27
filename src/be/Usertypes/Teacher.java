package be.Usertypes;

public class Teacher extends User{
    private final String school;

    public Teacher(int id, String name, String school) {
        super(id, name);
        this.school = school;
    }

    public String getSchool() {
        return school;
    }
}
