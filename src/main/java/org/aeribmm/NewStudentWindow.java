package org.aeribmm;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class NewStudentWindow extends JDialog {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField ageField;
    private DAO dao;
    private WindowStyle style;
    public NewStudentWindow(){
        this(new DAO(),new WindowStyle());
    }

    public NewStudentWindow(DAO dao,WindowStyle style) {
        this.dao = dao;
        this.style = style;
    }

    public void create() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JPanel buttonPanel = new JPanel();
        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        ageField = new JTextField(5);
        panel.add(new JLabel("Name:"));
        panel.add(firstNameField);
        panel.add(new JLabel("Last Name:"));
        panel.add(lastNameField);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);
        JButton addButton = new JButton("Add");

        style.lastNameField(lastNameField);
        style.lastNameField(firstNameField);
        style.lastNameField(ageField);
        buttonPanel.add(addButton);
        addButton.addActionListener(e -> {
                String name = firstNameField.getText().trim();
                String lastName = lastNameField.getText().trim();
                int age = Integer.parseInt(ageField.getText().trim());
                dao.addStudent(name, lastName, age);
        });
//        dao.addStudent(name,lastName,age);
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
