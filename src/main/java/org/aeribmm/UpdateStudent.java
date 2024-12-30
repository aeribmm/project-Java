package org.aeribmm;


import javax.swing.*;
import java.awt.*;

public class UpdateStudent {
    //search objects
    private DAO dao;
    private LabelCreator creator;
    private JFrame frame;
    private JPanel searchPanel;
    private JLabel searchLabel;
    private JTextField searchField;
    private JButton searchButton;
    //query result objects
    private JPanel centerPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JLabel newNameLabel;
    private JLabel newLastNameLabel;
    private JLabel newAgeLabel;
    private JLabel newGradeLabel;
    private JButton updateButton;
    private JTextField newNameField;

    private JTextField newLastNameField;
    private JTextField newAgeField;
    private JTextField newGradeField;

    public UpdateStudent() {
        this(new DAO(),new LabelCreator());
    }

    public UpdateStudent(DAO dao, LabelCreator creator) {
        this.dao = dao;
        this.creator = creator;
    }

    public void create(){
        frame =creator.createSearchFrame("Update");

        //search panel
        searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(new Color(45, 45, 45));
        searchLabel = creator.createLastNameLabel("Enter last name");
        searchField = creator.createIdField();
        searchButton = creator.searchButton("Search");
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        //
        centerPanel = new JPanel(new GridLayout(1, 2, 10, 10)); // 1 строка, 2 столбца
        centerPanel.setBackground(new Color(45, 45, 45));
        //leftPanel

        leftPanel = creator.createResultLabels();
        //create right panel
        updateButton = creator.searchButton("Update");
        newNameLabel = creator.createLastNameLabel("New name: ");
        newLastNameLabel = creator.createLastNameLabel("New last name: ");
        newAgeLabel = creator.createLastNameLabel("New age: ");
        newGradeLabel = creator.createLastNameLabel("New grade: ");
        newNameField = creator.createLastNameField();
        newLastNameField = creator.createLastNameField();
        newAgeField = creator.createLastNameField();
        newGradeField = creator.createLastNameField();
        searchButton.addActionListener(e ->{
            creator.handleSearchButtonClick(leftPanel,frame,searchField);
        });
        rightPanel = creator.createUpdatePanel(newNameLabel,newNameField,newLastNameLabel,newLastNameField, newAgeLabel,newAgeField,newGradeLabel,newGradeField,updateButton);
        updateButton.addActionListener(e ->{
            dao.updateStudent(searchField,newNameField,newLastNameField,newAgeField,newGradeField);
        });
        centerPanel.add(leftPanel);
        centerPanel.add(rightPanel);
        frame.add(searchPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
