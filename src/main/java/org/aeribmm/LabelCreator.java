package org.aeribmm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class LabelCreator {
    private WindowStyle style;
    private DAO dao;
    private JLabel resultIdLabel;
    private JLabel resultNameLabel;
    private JLabel resultLastNameLabel;
    private JLabel resultAgeLabel;
    private JLabel resultGradeLabel;

    public LabelCreator() {
        this(new WindowStyle(),new DAO());
    }


    public LabelCreator(WindowStyle style, DAO dao) {
        this.style = style;
        this.dao = dao;
    }


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

    public JFrame createSearchFrame(String text){
        JFrame searchFrame = new JFrame(text);
        searchFrame.setSize(500, 300);
        searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchFrame.setLayout(new BorderLayout());
        style.searchFrameStyle(searchFrame);
        searchFrame.setLocationRelativeTo(null);
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
    public JPanel createInputPanelForNewStudent(JLabel idLabel,JTextField idField,JLabel lastNameLabel,JTextField lastNameField,JLabel ageLabel,JTextField age){
        JPanel inputPanel = new JPanel(new GridLayout(4, 1));
        inputPanel.setBackground(new Color(45, 45, 45));
        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(lastNameLabel);
        inputPanel.add(lastNameField);
        inputPanel.add(ageLabel);
        inputPanel.add(age);
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

    public JLabel createIdLabel(String text){
        JLabel idLabel = new JLabel(text);
        idLabel.setForeground(Color.LIGHT_GRAY);
        return idLabel;
    }
    public JLabel createLastNameLabel(String text){
        JLabel lastNameLabel = new JLabel(text);
        lastNameLabel.setForeground(Color.LIGHT_GRAY);
        return lastNameLabel;
    }
    public JButton searchButton(String text){
        JButton searchButton =new JButton(text);
        searchButton.setBackground(new Color(100, 100, 100));
        searchButton.setForeground(Color.WHITE);
        return searchButton;
    }
    public JPanel createResultLabels() {
        JPanel resultPanel = new JPanel(new GridLayout(5, 1));
        resultPanel.setBackground(new Color(45, 45, 45));

        resultIdLabel = createStyledLabel("ID: ");
        resultNameLabel = createStyledLabel("Name: ");
        resultLastNameLabel = createStyledLabel("Last name: ");
        resultAgeLabel = createStyledLabel("Age: ");
        resultGradeLabel = createStyledLabel("Grade: ");

        resultPanel.add(resultIdLabel);
        resultPanel.add(resultNameLabel);
        resultPanel.add(resultLastNameLabel);
        resultPanel.add(resultAgeLabel);
        resultPanel.add(resultGradeLabel);

        return resultPanel;
    }
    public JLabel  createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.LIGHT_GRAY);
        return label;
    }
    public void handleSearchButtonClick(JTextField idField,JPanel resultPanel,JFrame searchFrame,JTextField lastNameField) {
        String id = idField.getText().trim();
        String lastName = lastNameField.getText().trim();
        StudentModel student = null;
        if (!id.isEmpty()) {
            student = dao.searchById(id);
        }else if(!lastName.isEmpty()){
            student = dao.searchByLastName(lastName.trim());
        }
            if (student != null) {
                resultPanel.removeAll();
                resultPanel.add(resultIdLabel);
                resultPanel.add(resultNameLabel);
                resultPanel.add(resultLastNameLabel);
                resultPanel.add(resultAgeLabel);
                resultPanel.add(resultGradeLabel);
                resultIdLabel.setText("ID: " + student.getId());
                resultNameLabel.setText("Name: " + student.getName());
                resultLastNameLabel.setText("Last name: " + student.getLastName());
                resultAgeLabel.setText("Age: " + student.getAge());
                resultGradeLabel.setText("Grade: " + student.getGrade());
                resultPanel.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(
                        searchFrame,
                        "Student not found",
                        "Results",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
            searchFrame.revalidate();
            searchFrame.repaint();
        idField.setText("");
        lastNameField.setText("");
    }
    public JPanel createUpdatePanel(JLabel a, JTextField a1,JLabel a2, JTextField a3,JLabel b, JTextField b1 ,JLabel b2 ,JTextField b3,JButton button){
        JPanel inputPanel = new JPanel(new GridLayout(5, 2,10,10));
        inputPanel.setBackground(new Color(45, 45, 45));
        inputPanel.add(a);
        inputPanel.add(a1);
        inputPanel.add(a2);
        inputPanel.add(a3);
        inputPanel.add(b);
        inputPanel.add(b1);
        inputPanel.add(b2);
        inputPanel.add(b3);
        inputPanel.add(new JLabel());
        inputPanel.add(button);
        return inputPanel;
    }
    public void handleSearchButtonClick(JPanel leftPanel,JFrame searchFrame,JTextField lastNameField) {
        String lastName = lastNameField.getText().trim();
        StudentModel student = null;
        if(!lastName.isEmpty()){
            student = dao.searchByLastName(lastName.trim());
        }else{
            JOptionPane.showMessageDialog(
                    searchFrame,
                    "Cannot find with empty last name",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        if (student != null) {
            leftPanel.removeAll();
            leftPanel.add(resultIdLabel);
            leftPanel.add(resultNameLabel);
            leftPanel.add(resultLastNameLabel);
            leftPanel.add(resultAgeLabel);
            leftPanel.add(resultGradeLabel);
            resultIdLabel.setText("ID: " + student.getId());
            resultNameLabel.setText("Name: " + student.getName());
            resultLastNameLabel.setText("Last name: " + student.getLastName());
            resultAgeLabel.setText("Age: " + student.getAge());
            resultGradeLabel.setText("Grade: " + student.getGrade());
            leftPanel.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(
                    searchFrame,
                    "Student not found",
                    "Results",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
        searchFrame.revalidate();
        searchFrame.repaint();
    }

}
