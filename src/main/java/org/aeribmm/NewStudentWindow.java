package org.aeribmm;

import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class NewStudentWindow extends JDialog {
    private JFrame panel;
    private JPanel inputPanel;
    private JTextField idField;
    private JTextField lastNameField;
    private JLabel idLabel;
    private JLabel lastNameLabel;
    private JButton searchButton;
    private JPanel resultPanel;

    private WindowStyle style;
    private Checker check;
    private LabelCreator creator;
    public NewStudentWindow(Checker check,LabelCreator creator) {
        this.check = check;
        this.creator = creator;
    }

    public NewStudentWindow(){
        this(new WindowStyle());
    }

    public NewStudentWindow(WindowStyle style) {
        this(new Checker(),new LabelCreator());
        this.style = style;
    }

    public void create() {
        panel = creator.createSearchFrame();
        inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBackground(new Color(45, 45, 45));
        JLabel nameLabel = creator.createStyledLabel("Name:");
        JTextField nameField = creator.createIdField();
        JLabel lastNameLabel = creator.createStyledLabel("Last Name:");
        JTextField lastNameField = creator.createLastNameField();
        JLabel ageLabel = creator.createStyledLabel("Age:");
        JTextField ageField = creator.createIdField();
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(lastNameLabel);
        inputPanel.add(lastNameField);
        inputPanel.add(ageLabel);
        inputPanel.add(ageField);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(45, 45, 45));
        JButton addButton = creator.searchButton();
        addButton.setText("Add");

        buttonPanel.add(addButton);
        addButton.addActionListener(e -> {
            check.isFull(nameField,lastNameField,ageField);
        });
        panel.add(inputPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        panel.pack();
        panel.setLocationRelativeTo(null);
        panel.setVisible(true);
    }
}
