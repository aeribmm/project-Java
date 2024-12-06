package org.aeribmm;

import java.sql.SQLException;
import java.util.List;

public interface StudentManager {
    StudentModel searchByLastName(String lastName);
    StudentModel searchById(String id);
    List<StudentModel> getAllStudents();
    void addStudent(String name,String lastName,int age) ;

    void removeStudent(String id);

}
