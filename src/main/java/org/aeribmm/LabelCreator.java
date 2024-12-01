package org.aeribmm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class LabelCreator {
    private WindowStyle style = new WindowStyle();
    public void showAll(JTable studentTable){
        JFrame frame = new JFrame("All Students");
        frame.setSize(1080, 720);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new JScrollPane(studentTable), BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public JTable getAllStudents(List<StudentModel> list){
        String [] columns = {"id","name","lastName","age","grade"};
        DefaultTableModel table = new DefaultTableModel(columns,0);
        for (StudentModel student : list) {
            Object[] row = {
                    student.getId(),
                    student.getName(),
                    student.getLastName(),
                    student.getAge(),
                    student.getGrade()
            };
            table.addRow(row);
        }
        return new JTable(table);
    }
    public JFrame createSearchFrame(){
        JFrame searchFrame = new JFrame("Search Student");
        searchFrame.setLocationRelativeTo(null);
        searchFrame.setSize(500, 300);
        searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchFrame.setLayout(new BorderLayout());
        style.searchFrameStyle(searchFrame);
        return searchFrame;
    }
    public JPanel createInputPanel(JLabel idLabel,JTextField idField,JLabel lastNameLabel,JTextField lastNameField,JButton searchButton){
        JPanel inputPanel = new JPanel(new GridLayout(4, 1));
        inputPanel.setBackground(new Color(45, 45, 45));
        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(lastNameLabel);
        inputPanel.add(lastNameField);
        inputPanel.add(searchButton);
        return inputPanel;
    }
    public JTextField createIdField(){
        JTextField idField = new JTextField(15);
        style.idField(idField);
        return idField;
    }
    public JTextField createLastNameField(){
        JTextField lastNameField = new JTextField(15);
        style.lastNameField(lastNameField);
        return lastNameField;
    }
    public JLabel createIdLabel(){
        JLabel idLabel = new JLabel("Enter Student ID:");
        idLabel.setForeground(Color.LIGHT_GRAY);
        return idLabel;
    }
    public JLabel createLastNameLabel(){
        JLabel lastNameLabel = new JLabel("Or Enter Last Name:");
        lastNameLabel.setForeground(Color.LIGHT_GRAY);
        return lastNameLabel;
    }
    public JButton searchButton(){
        JButton searchButton =new JButton("Search");
        searchButton.setBackground(new Color(100, 100, 100));
        searchButton.setForeground(Color.WHITE);
        return searchButton;
    }

    public JPanel createResultPanel(){
        JPanel resultPanel = new JPanel(new GridLayout(5, 1));
        resultPanel.setBackground(new Color(45, 45, 45));
        return resultPanel;
    }
    public JPanel createResultLabels() {
        JPanel resultPanel = new JPanel(new GridLayout(5, 1)); // или другой layout
        resultPanel.setBackground(new Color(45, 45, 45));

        JLabel resultIdLabel = createStyledLabel("ID: ");
        JLabel resultNameLabel = createStyledLabel("Name: ");
        JLabel resultLastNameLabel = createStyledLabel("Last name: ");
        JLabel resultAgeLabel = createStyledLabel("Age: ");
        JLabel resultGradeLabel = createStyledLabel("Grade: ");

        resultPanel.add(resultIdLabel);
        resultPanel.add(resultNameLabel);
        resultPanel.add(resultLastNameLabel);
        resultPanel.add(resultAgeLabel);
        resultPanel.add(resultGradeLabel);

        return resultPanel;
    }
    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.LIGHT_GRAY);
        return label;
    }
}
