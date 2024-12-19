package org.aeribmm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RemoveStudentWindow {

    public void create(){
        JFrame frame = new JFrame("Remove Student");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null); // Абсолютное позиционирование

        // Поле для ввода ID студента
        JLabel idLabel = new JLabel("Enter Student ID:");
        idLabel.setBounds(20, 20, 120, 25);
        JTextField idField = new JTextField();
        idField.setBounds(150, 20, 200, 25);

        // Поле для ввода фамилии студента
        JLabel lastNameLabel = new JLabel("Or Enter Last Name:");
        lastNameLabel.setBounds(20, 60, 120, 25);
        JTextField lastNameField = new JTextField();
        lastNameField.setBounds(150, 60, 200, 25);

        // Кнопка поиска
        JButton searchButton = new JButton("Search");
        searchButton.setBounds(150, 100, 100, 30);

        // Обработчик кнопки "Search"
        searchButton.addActionListener(e ->  {
                String id = idField.getText().trim();
                String lastName = lastNameField.getText().trim();

                if (id.isEmpty() && lastName.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter either Student ID or Last Name.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Здесь должна быть логика поиска студента по ID или фамилии
                    System.out.println("Searching for Student ID: " + id + ", Last Name: " + lastName);
                    JOptionPane.showMessageDialog(frame, "Search functionality is not implemented.", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            });


        // Добавление компонентов в окно
        frame.add(idLabel);
        frame.add(idField);
        frame.add(lastNameLabel);
        frame.add(lastNameField);
        frame.add(searchButton);

        // Установка видимости окна
        frame.setLocationRelativeTo(null); // Центрирование окна
        frame.setVisible(true);
    }

}
