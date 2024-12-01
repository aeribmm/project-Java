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
    private final String findAll = "SELECT * FROM students";

    private final String findById = "SELECT * FROM students WHERE id = ?";
    private final String findByLastName = "SELECT * FROM students WHERE \"lastName\" = ?";

    public List<StudentModel> getAllStudents() {
        List<StudentModel> students = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(findAll)) {
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

    public StudentModel searchById(String id){
        StudentModel student = null;
        try (Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(findById)) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                student = new StudentModel();
                student.setId(resultSet.getString("id"));
                student.setName(resultSet.getString("name"));
                student.setLastName(resultSet.getString("lastName"));
                student.setAge(resultSet.getInt("age"));
                student.setGrade(resultSet.getDouble("grade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    public StudentModel searchByLastName(String lastName) {
        StudentModel student = null;
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(findByLastName)) {
            preparedStatement.setString(1, lastName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                student = new StudentModel();
                student.setId(resultSet.getString("id"));
                student.setName(resultSet.getString("name"));
                student.setLastName(resultSet.getString("lastName"));
                student.setAge(resultSet.getInt("age"));
                student.setGrade(resultSet.getDouble("grade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Something goes wrong: " + lastName, e);
        }
        return student;
    }




}
