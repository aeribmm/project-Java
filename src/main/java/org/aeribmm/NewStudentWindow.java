package org.aeribmm;

import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class NewStudentWindow extends JDialog {
    private JFrame searchFrame;
    private JTextField lastNameField;
    private JLabel lastNameLabel;
    private JButton removeStudent;
    private JPanel inputPanel;

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
        // Создание основного окна
        searchFrame = creator.createSearchFrame("Add Student");

        // Поле для ввода фамилии
        lastNameField = creator.createLastNameField();
        lastNameLabel = creator.createLastNameLabel();
        removeStudent = creator.searchButton("Add Student");

        // Создание панели ввода
        inputPanel = creator.createInputPanel(lastNameLabel, lastNameField, removeStudent);

        // Настройка окна
        searchFrame.add(inputPanel, BorderLayout.CENTER);
        searchFrame.setVisible(true);

        // Обработчик кнопки удаления
        removeStudent.addActionListener(event -> {
            String lastName = lastNameField.getText().trim();
            if (lastName.isEmpty()) {
                JOptionPane.showMessageDialog(searchFrame, "Please enter the last name.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
//                if (success) {
//                    JOptionPane.showMessageDialog(searchFrame, "Student successfully deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
//                    lastNameField.setText("");
//                } else {
//                    JOptionPane.showMessageDialog(searchFrame, "No student found with that last name.", "Error", JOptionPane.ERROR_MESSAGE);
//                }
            }
        });
    }
}
