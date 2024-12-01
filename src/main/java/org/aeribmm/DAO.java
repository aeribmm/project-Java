package org.aeribmm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO extends JFrame{

    private final String url = "jdbc:postgresql://localhost:5432/studentsApp";
    private final String username = "postgres";
    private final String password = "3152";

    public List<StudentModel> getAllStudents() {
        List<StudentModel> students = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM students")) {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                int age = resultSet.getInt("age");
                double grade = resultSet.getDouble("grade");

                students.add(new StudentModel(id, name,lastName, age, grade));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }


    public StudentModel searchStudentById(List<StudentModel> list,String id){
        for(StudentModel student : list){
            if(student.getId().equals(id)){
                return student;
            }
        }
        return null;
    }


    public StudentModel searchStudentByLastName(List<StudentModel> list,String lastName){
        for(StudentModel student : list){
            if(student.getLastName().equals(lastName)){
                return student;
            }
        }
        return null;
    }
}
