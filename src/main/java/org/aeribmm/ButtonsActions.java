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
    private UpdateStudent update;
    private AverageGrade average;

    public ButtonsActions() {
        this(new DAO(),new WindowStyle(),new LabelCreator(),new NewStudentWindow(),new ShowStudentWindow(),new RemoveStudent(),new UpdateStudent(),new AverageGrade());
    }

    public ButtonsActions(DAO dao, WindowStyle styles, LabelCreator creator,NewStudentWindow window,ShowStudentWindow showStudent,RemoveStudent removeStudent,UpdateStudent updateStudent,AverageGrade avg) {
        this.dao = dao;
        this.styles = styles;
        this.creator = creator;
        this.newWindow = window;
        this.showStudentWindow = showStudent;
        this.remove = removeStudent;
        this.update = updateStudent;
        this.average = avg;
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
            update.create();//create update frame
        });

        calculateGradeButton.addActionListener(e -> {

            average.create();
        });
    }
}


