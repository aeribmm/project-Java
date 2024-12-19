package org.aeribmm;

import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class NewStudentWindow extends JDialog {
    private WindowStyle style;
    private Checker check;
    private LabelCreator creator;
    private JFrame frame;
    private JPanel panel;
    private JTextField name;
    private JTextField lastName;
    private JTextField age;
    private JLabel nameLabel;
    private JLabel lastNameLabel;
    private JLabel ageLabel;
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
    public void create(){
        frame = creator.createSearchFrame("Add new student to data base");


        nameLabel = creator.createLastNameLabel("Name: ");
        name = creator.createLastNameField();
        lastNameLabel = creator.createLastNameLabel("Last name: ");
        lastName = creator.createLastNameField();
        ageLabel = creator.createIdLabel("Age: ");
        age = creator.createLastNameField();
        panel = creator.createInputPanel1(nameLabel,name,lastNameLabel,lastName,ageLabel,age);
        JButton add = creator.searchButton("Add");
        panel.add(nameLabel);
        panel.add(name);
        panel.add(lastNameLabel);
        panel.add(lastName);
        panel.add(ageLabel);
        panel.add(age);
        panel.add(add);
        frame.add(panel, BorderLayout.NORTH);
        frame.setVisible(true);
    }

}
