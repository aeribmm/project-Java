package org.aeribmm;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public interface StudentManager {
    StudentModel searchByLastName(String lastName);
    StudentModel searchById(String id);
    List<StudentModel> getAllStudents();
    void addStudent(StudentModel student) ;

    void removeStudent(JTextField id, JTextField lastName);
    double calculateAverageGrade();
    void updateStudent(JTextField searchLastName,JTextField name,JTextField lastName,JTextField age,JTextField grade);

}
