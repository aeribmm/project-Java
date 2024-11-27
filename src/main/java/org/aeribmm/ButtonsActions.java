package org.aeribmm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ButtonsActions {
    private DAO dao = new DAO();
    private WindowStyle styles = new WindowStyle();
    public void buttons(JButton showAllButton,JButton showStudentButton,JButton addStudentButton,JButton removeStudentButton,JButton updateStudentButton,JButton calculateGradeButton) {
        showAllButton.addActionListener(e -> {
            List<StudentModel> students = dao.getAllStudents();
            JTable studentTable = dao.getAllStudents(students);
            styles.setStyleTable(studentTable);

            JFrame frame = new JFrame("All Students");
            frame.setSize(1080, 720);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.add(new JScrollPane(studentTable), BorderLayout.CENTER);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        });
        showStudentButton.addActionListener(e -> {
            List<StudentModel> list = dao.getAllStudents();
            JFrame searchFrame = new JFrame("Search Student");
            searchFrame.setLocationRelativeTo(null);
            searchFrame.setSize(500, 300);
            searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            searchFrame.setLayout(new BorderLayout());

            searchFrame.getContentPane().setBackground(new Color(45, 45, 45));

            JPanel inputPanel = new JPanel(new GridLayout(4, 1));
            inputPanel.setBackground(new Color(45, 45, 45));

            JTextField idField = new JTextField(15);
            idField.setBackground(new Color(60, 60, 60));
            idField.setForeground(Color.WHITE);
            idField.setCaretColor(Color.WHITE);
            idField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            JTextField lastNameField = new JTextField(15);
            lastNameField.setBackground(new Color(60, 60, 60));
            lastNameField.setForeground(Color.WHITE);
            lastNameField.setCaretColor(Color.WHITE);
            lastNameField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            JLabel idLabel = new JLabel("Enter Student ID:");
            idLabel.setForeground(Color.LIGHT_GRAY);
            JLabel lastNameLabel = new JLabel("Or Enter Last Name:");
            lastNameLabel.setForeground(Color.LIGHT_GRAY);

            inputPanel.add(idLabel);
            inputPanel.add(idField);
            inputPanel.add(lastNameLabel);
            inputPanel.add(lastNameField);

            JButton searchButton = new JButton("Search");
            searchButton.setBackground(new Color(100, 100, 100));
            searchButton.setForeground(Color.WHITE);
            inputPanel.add(searchButton);

            JPanel resultPanel = new JPanel(new GridLayout(5, 1));
            resultPanel.setBackground(new Color(45, 45, 45));

            JLabel resultIdLabel = new JLabel("ID: ");
            JLabel resultNameLabel = new JLabel("Name: ");
            JLabel resultLastNameLabel = new JLabel("Last name: ");
            JLabel resultAgeLabel = new JLabel("Age: ");
            JLabel resultGradeLabel = new JLabel("Grade: ");

            resultIdLabel.setForeground(Color.LIGHT_GRAY);
            resultNameLabel.setForeground(Color.LIGHT_GRAY);
            resultLastNameLabel.setForeground(Color.LIGHT_GRAY);
            resultAgeLabel.setForeground(Color.LIGHT_GRAY);
            resultGradeLabel.setForeground(Color.LIGHT_GRAY);

            resultPanel.add(resultIdLabel);
            resultPanel.add(resultNameLabel);
            resultPanel.add(resultLastNameLabel);
            resultPanel.add(resultAgeLabel);
            resultPanel.add(resultGradeLabel);

            searchFrame.add(inputPanel, BorderLayout.NORTH);
            searchFrame.add(resultPanel, BorderLayout.CENTER);

            resultPanel.setVisible(false);

            searchButton.addActionListener(event -> {
                String idText = idField.getText().trim();
                String lastName = lastNameField.getText().trim();
                resultPanel.setVisible(false);

                resultIdLabel.setText("ID: ");
                resultNameLabel.setText("Name: ");
                resultLastNameLabel.setText("Last name: ");
                resultAgeLabel.setText("Age: ");
                resultGradeLabel.setText("Grade: ");

                StudentModel foundStudent = null;
                try {
                    if (!idText.isEmpty()) {
                        String id = String.valueOf(idText);
                        foundStudent = dao.searchStudentById(list, id);
                    } else if (!lastName.isEmpty()) {
                        foundStudent = dao.searchStudentByLastName(list, lastName);
                    } else {
                        JOptionPane.showMessageDialog(searchFrame, "Please enter either ID or Last Name.");
                        return;
                    }

                    if (foundStudent != null) {
                        resultIdLabel.setText("ID: " + foundStudent.getId());
                        resultNameLabel.setText("Name: " + foundStudent.getName());
                        resultLastNameLabel.setText("Last name: " + foundStudent.getLastName());
                        resultAgeLabel.setText("Age: " + foundStudent.getAge());
                        resultGradeLabel.setText("Grade: " + foundStudent.getGrade());
                        resultPanel.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(searchFrame, "Student not found.");
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(searchFrame, "Invalid ID format. Please enter a valid number.");
                }
            });

            searchFrame.setVisible(true);
        });

        addStudentButton.addActionListener(e -> {
            List<StudentModel> list = dao.getAllStudents();

        });
    }
}


