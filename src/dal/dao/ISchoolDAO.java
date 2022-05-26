package dal.dao;

import be.School;

import java.util.ArrayList;

public interface ISchoolDAO {
    /**
     * Creates the given school in the database
     * @return false if name is already in use, true if created correctly
     */
    boolean createSchool(School school);

    /**
     * Deletes the school with the given ID from the database
     */
    void deleteSchool(int schoolID);

    /**
     * Requests all schools as well as their teachers and students from the database
     * @return the list of schools
     */
    ArrayList<School> getAllSchools();
}
