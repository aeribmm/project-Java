package org.aeribmm;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {
    private DAO dao;
    private ButtonsActions but;
    public Application(DAO dao, ButtonsActions but){
        this.dao = dao;
        this.but = but;
    }

    public Application() {
        this(new DAO(),new ButtonsActions());
        setTitle("Student Management System");
        setSize(1080, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(45, 45, 45));
        createMainMenu();
    }


    private void createMainMenu() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(new Color(45, 45, 45)); // Цвет фона панели

        JLabel titleLabel = new JLabel("Student Management System", JLabel.CENTER);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(200, 200, 200));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JButton showAllButton = createMenuButton("Show All Students");
        JButton showStudentButton = createMenuButton("Show Student");
        JButton addStudentButton = createMenuButton("Add New Student");
        JButton removeStudentButton = createMenuButton("Remove Student");
        JButton updateStudentButton = createMenuButton("Update Student");
        JButton calculateGradeButton = createMenuButton("Calculate Average Grade");

        menuPanel.add(titleLabel);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(showAllButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(showStudentButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(addStudentButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(removeStudentButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(updateStudentButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(calculateGradeButton);

        // Центрируем панель меню
        setLayout(new GridBagLayout());
        but.buttons(showAllButton,showStudentButton,addStudentButton,removeStudentButton,updateStudentButton,calculateGradeButton);
        add(menuPanel);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(300, 40));
        button.setMaximumSize(new Dimension(300, 40));
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setFocusPainted(false); // Убирает рамку фокуса вокруг текста
        button.setBackground(new Color(60, 60, 60));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(60, 60, 60));
            }
        });
        return button;
    }
}
