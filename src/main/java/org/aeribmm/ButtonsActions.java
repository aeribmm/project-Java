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

    private ShowStudentWindow showStudentWindow;
    private RemoveStudent remove;

    public ButtonsActions() {
        this(new DAO(),new WindowStyle(),new LabelCreator(),new NewStudentWindow(),new ShowStudentWindow(),new RemoveStudent());
    }

    public ButtonsActions(DAO dao, WindowStyle styles, LabelCreator creator,NewStudentWindow window,ShowStudentWindow showStudent,RemoveStudent removeStudent) {
        this.dao = dao;
        this.styles = styles;
        this.creator = creator;
        this.newWindow = window;
        this.showStudentWindow = showStudent;
        this.remove = removeStudent;
    }

    public void buttons(JButton showAllButton, JButton showStudentButton, JButton addStudentButton, JButton removeStudentButton, JButton updateStudentButton, JButton calculateGradeButton) {

        showAllButton.addActionListener(e -> {
            List<StudentModel> students = dao.getAllStudents();
            JTable studentTable = creator.getAllStudents(students);
            styles.setStyleTable(studentTable);
            creator.showAll(studentTable);
        });//showAllMethod


        showStudentButton.addActionListener(e -> {
            showStudentWindow.create();
        });//find by id or lastName

        addStudentButton.addActionListener(e -> {
            newWindow.create();
        });//create a new student

        removeStudentButton.addActionListener(e -> {
            remove.create();//create remove frame
        });

        updateStudentButton.addActionListener(e -> {


        });

        calculateGradeButton.addActionListener(e -> {


        });
    }
}


