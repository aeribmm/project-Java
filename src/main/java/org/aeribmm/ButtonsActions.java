package org.aeribmm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ButtonsActions {
    private DAO dao;
    private NewStudentWindow newWindow;
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
        this(new DAO(),new WindowStyle(),new LabelCreator(),new NewStudentWindow());
    }

    public ButtonsActions(DAO dao, WindowStyle styles, LabelCreator creator,NewStudentWindow window) {
        this.dao = dao;
        this.styles = styles;
        this.creator = creator;
        this.newWindow = window;
        searchFrame = creator.createSearchFrame();
        idField = creator.createIdField();
        lastNameField = creator.createLastNameField();
        idLabel = creator.createIdLabel();
        lastNameLabel = creator.createLastNameLabel();
        searchButton = creator.searchButton();
        resultPanel = creator.createResultLabels();
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
            searchFrame.add(inputPanel, BorderLayout.NORTH);
            searchFrame.add(resultPanel, BorderLayout.CENTER);
            searchFrame.setVisible(true);
            resultPanel.setVisible(false);
            searchButton.addActionListener(event -> {
                creator.handleSearchButtonClick(idField,resultPanel,searchFrame,lastNameField);
            });
        });//find by id or lastName

        addStudentButton.addActionListener(e -> {
            newWindow.create();
        });
    }
}


