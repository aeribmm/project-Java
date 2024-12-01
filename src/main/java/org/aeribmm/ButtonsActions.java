package org.aeribmm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ButtonsActions {
    private DAO dao;
    private WindowStyle styles;
    private LabelCreator creator;

    private JFrame searchFrame;
    private JPanel inputPanel;
    private JTextField idField;
    private JTextField lastNameField;
    private JLabel idLabel;
    private JLabel lastNameLabel;
    private JButton searchButton;
    private JPanel resultPanel;

    public ButtonsActions() {
        this(new DAO(),new WindowStyle(),new LabelCreator());
    }

    public ButtonsActions(DAO dao, WindowStyle styles, LabelCreator creator) {
        this.dao = dao;
        this.styles = styles;
        this.creator = creator;
        searchFrame = creator.createSearchFrame();

        idField = creator.createIdField();
        lastNameField = creator.createLastNameField();
        idLabel = creator.createIdLabel();
        lastNameLabel = creator.createLastNameLabel();
        searchButton = creator.searchButton();
        resultPanel = creator.createResultPanel();
        inputPanel = creator.createInputPanel(idLabel,idField,lastNameLabel,lastNameField,searchButton);
    }

    public void buttons(JButton showAllButton, JButton showStudentButton, JButton addStudentButton, JButton removeStudentButton, JButton updateStudentButton, JButton calculateGradeButton) {

        showAllButton.addActionListener(e -> {
            List<StudentModel> students = dao.getAllStudents();
            JTable studentTable = creator.getAllStudents(students);
            styles.setStyleTable(studentTable);
            creator.showAll(studentTable);
        });//showAllMethod


        showStudentButton.addActionListener(e -> {
            List<StudentModel> list = dao.getAllStudents();
            searchFrame.add(inputPanel, BorderLayout.NORTH);
            searchFrame.add(resultPanel, BorderLayout.CENTER);
            searchFrame.setVisible(true);
            resultPanel.setVisible(false);

            searchButton.addActionListener(event -> {
//                String idText = idField.getText().trim();
//                String lastName = lastNameField.getText().trim();
//                resultPanel.setVisible(false);
//
//                resultIdLabel.setText("ID: ");
//                resultNameLabel.setText("Name: ");
//                resultLastNameLabel.setText("Last name: ");
//                resultAgeLabel.setText("Age: ");
//                resultGradeLabel.setText("Grade: ");
//
//                StudentModel foundStudent = null;
//                try {
//                    if (!idText.isEmpty()) {
//                        String id = String.valueOf(idText);
//                        foundStudent = dao.searchStudentById(list, id);
//                    } else if (!lastName.isEmpty()) {
//                        foundStudent = dao.searchStudentByLastName(list, lastName);
//                    } else {
//                        JOptionPane.showMessageDialog(searchFrame, "Please enter either ID or Last Name.");
//                        return;
//                    }
//
//                    if (foundStudent != null) {
//                        resultIdLabel.setText("ID: " + foundStudent.getId());
//                        resultNameLabel.setText("Name: " + foundStudent.getName());
//                        resultLastNameLabel.setText("Last name: " + foundStudent.getLastName());
//                        resultAgeLabel.setText("Age: " + foundStudent.getAge());
//                        resultGradeLabel.setText("Grade: " + foundStudent.getGrade());
//                        resultPanel.setVisible(true);
//                    } else {
//                        JOptionPane.showMessageDialog(searchFrame, "Student not found.");
//                    }
//
//                } catch (NumberFormatException ex) {
//                    JOptionPane.showMessageDialog(searchFrame, "Invalid ID format. Please enter a valid number.");
//                }
//            });
//
//            searchFrame.setVisible(true);
        });

//        addStudentButton.addActionListener(e -> {
//            List<StudentModel> list = dao.getAllStudents();
//            System.out.println(list);
//        });
        });
    }
}


