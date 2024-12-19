package org.aeribmm;

import javax.swing.*;
import java.awt.*;

public class ShowStudentWindow {
    private JFrame searchFrame;
    private JPanel inputPanel;
    private JTextField idField;
    private JTextField lastNameField;
    private JLabel idLabel;
    private JLabel lastNameLabel;
    private JButton searchButton;
    private JPanel resultPanel;
    private LabelCreator creator;

    public ShowStudentWindow() {
        this(new LabelCreator());
    }

    public ShowStudentWindow(LabelCreator creator) {
        this.creator = creator;
    }

    public void create(){
        searchFrame = creator.createSearchFrame("Find a student");
        idField = creator.createIdField();
        lastNameField = creator.createLastNameField();
        idLabel = creator.createIdLabel();
        lastNameLabel = creator.createLastNameLabel();
        searchButton = creator.searchButton("Find student");
        resultPanel = creator.createResultLabels();
        inputPanel = creator.createInputPanel(idLabel,idField,lastNameLabel,lastNameField,searchButton);

        searchFrame.add(inputPanel, BorderLayout.NORTH);
        searchFrame.add(resultPanel, BorderLayout.CENTER);
        searchFrame.setVisible(true);
        resultPanel.setVisible(false);
        searchButton.addActionListener(event -> {
            creator.handleSearchButtonClick(idField,resultPanel,searchFrame,lastNameField);
        });

    }
}
