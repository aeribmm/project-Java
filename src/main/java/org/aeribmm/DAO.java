package org.aeribmm;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DAO extends JFrame implements StudentManager{
    private final String url = "jdbc:postgresql://localhost:5432/studentsApp";
    private final String username = "postgres";
    private final String password = "3152";
    private final String findAll = "SELECT * FROM students";
    private final String findById = "SELECT * FROM students WHERE id = ?";
    private final String findByLastName = "SELECT * FROM students WHERE \"lastName\" = ?";
    private final String generateId = "SELECT id FROM students ORDER BY id";
    private final  String checkIfExist ="SELECT COUNT(*) FROM students WHERE name = ? AND \"lastName\" = ? AND age = ?";
    private final String insertStudent = "INSERT INTO students (id,name, \"lastName\", age, grade) VALUES (?,?, ?, ?, ?)";
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
    public void addStudent(StudentModel student) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(insertStudent)) {
            preparedStatement.setString(1,student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getLastName());
            preparedStatement.setInt(4, student.getAge());
            preparedStatement.setDouble(5, student.getGrade());
            int rowsInserted = preparedStatement.executeUpdate();
            if(rowsInserted> 0){
                JOptionPane.showMessageDialog(null,"Success");
            }else{
                JOptionPane.showMessageDialog(null,"Failed to add student");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(student);
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

    public void removeStudent(String id){

    }
    public String generateFreeId() {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(generateId)) {
            Set<String> existingIds = new HashSet<>();
            while (resultSet.next()) {
                existingIds.add(resultSet.getString("id"));
            }
            int currentId = 1;
            String currentIdStr = String.valueOf(currentId);
            while (existingIds.contains(currentIdStr)) {
                currentId++;
                currentIdStr = String.valueOf(currentId);
            }
            return currentIdStr;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error", e);
        }
    }

    public boolean checkIfStudentExists(String firstName, String lastName,int age ){
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(checkIfExist)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



}
