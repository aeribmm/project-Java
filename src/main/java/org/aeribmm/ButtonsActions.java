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
    private FindStudent find;
    private RemoveStudent remove;


    public ButtonsActions() {
        this(new DAO(),new WindowStyle(),new LabelCreator(),new NewStudentWindow(),new FindStudent(),new RemoveStudent());
    }

    public ButtonsActions(DAO dao, WindowStyle styles, LabelCreator creator,NewStudentWindow window,FindStudent find,RemoveStudent removeStudent) {
        this.dao = dao;
        this.styles = styles;
        this.creator = creator;
        this.newWindow = window;
        this.find = find;
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
            find.create();
        });//find by id or lastName

        addStudentButton.addActionListener(e -> {
            newWindow.create();
        });//create a new student

        removeStudentButton.addActionListener(e -> {
            remove.create();
        });
    }
}


