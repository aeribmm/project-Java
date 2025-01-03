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
    private final String deleteById = "DELETE FROM students WHERE id = ?";
    private final String deleteByLastName = "DELETE FROM students WHERE \"lastName\" = ?";


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
                JOptionPane.showMessageDialog(null,"Added","Success",JOptionPane.INFORMATION_MESSAGE);
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
    public void removeStudent(JTextField id, JTextField lastName) {
        String idStr = id.getText().trim();
        String lastNameStr = lastName.getText().trim();

        if (idStr.isEmpty() && lastNameStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter ID or last name", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(idStr.isEmpty() ? deleteByLastName : deleteById)) {

            if (!idStr.isEmpty()) {
                stmt.setString(1, idStr);
            } else {
                stmt.setString(1, lastNameStr);
            }

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Deleted", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Student not found", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        id.setText("");
        lastName.setText("");

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

    public double calculateAverageGrade(){
        double averageGrade = 0.0;
        String query = "SELECT AVG(grade) AS average_grade FROM students";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                averageGrade = resultSet.getDouble("average_grade");
            }

        } catch (SQLException e) {
            System.err.println("Error while calculating average grade: " + e.getMessage());
        }
        return averageGrade;
    }
    public void updateStudent(JTextField searchLastName,JTextField name,JTextField lastName,JTextField age,JTextField grade){

        // Получаем фамилию студента, данные которого нужно обновить
        String searchLastNameValue = searchLastName.getText().trim();
        if (searchLastNameValue.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter the last name of the student to search.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int ageInt = Integer.parseInt(age.getText().trim());
            if (ageInt < 0 || ageInt > 100) {
                JOptionPane.showMessageDialog(null, "Age must be between 0 and 100", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid age value", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            double gradeDouble = Double.parseDouble(grade.getText().trim());
            if (gradeDouble < 0 || gradeDouble > 100) {
                JOptionPane.showMessageDialog(null, "Grade must be between 0 and 100", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid grade value", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Создаем строку для SQL запроса
        StringBuilder updateQuery = new StringBuilder("UPDATE students SET ");
        boolean isFirstField = true;

        // Проверяем каждое поле и добавляем его в запрос, если оно заполнено
        if (!name.getText().trim().isEmpty()) {
            updateQuery.append("name = ? ");
            isFirstField = false;
        }
        if (!lastName.getText().trim().isEmpty()) {
            if (!isFirstField) updateQuery.append(", ");
            updateQuery.append("\"lastName\" = ? ");
            isFirstField = false;
        }
        if (!age.getText().trim().isEmpty()) {
            if (!isFirstField) updateQuery.append(", ");
            updateQuery.append("age = ? ");
            isFirstField = false;
        }
        if (!grade.getText().trim().isEmpty()) {
            if (!isFirstField) updateQuery.append(", ");
            updateQuery.append("grade = ? ");
        }

        // Если ничего не заполнено, выводим сообщение
        if (isFirstField) {
            JOptionPane.showMessageDialog(null, "No fields were filled to update.","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        updateQuery.append("WHERE \"lastName\" = ?");
        System.out.println("Generated query: " + updateQuery.toString());
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery.toString())) {

            int index = 1;
            if (!name.getText().trim().isEmpty()) {
                preparedStatement.setString(index++, name.getText().trim());
            }
            if (!lastName.getText().trim().isEmpty()) {
                preparedStatement.setString(index++, lastName.getText().trim());
            }
            if (!age.getText().trim().isEmpty()) {
                preparedStatement.setInt(index++, Integer.parseInt(age.getText().trim()));
            }
            if (!grade.getText().trim().isEmpty()) {
                preparedStatement.setDouble(index++, Double.parseDouble(grade.getText().trim()));
            }
            preparedStatement.setString(index, searchLastNameValue);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Данные успешно обновлены.");
            } else {
                JOptionPane.showMessageDialog(null, "Студент с такой фамилией не найден.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ошибка обновления данных: " + e.getMessage());
        }
    }

}
