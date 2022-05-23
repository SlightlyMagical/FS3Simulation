package dal.dao;

import be.School;

import java.util.ArrayList;

public interface ISchoolDAO {
    boolean createSchool(School school);

    void deleteSchool(int schoolID);

    ArrayList<School> getAllSchools();
}
