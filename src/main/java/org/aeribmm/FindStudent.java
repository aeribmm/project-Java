package org.aeribmm;

import javax.swing.*;
import java.awt.*;

public class FindStudent {
    private JFrame searchFrame;
    private JPanel inputPanel;
    private JTextField idField;
    private JTextField lastNameField;
    private JLabel idLabel;
    private JLabel lastNameLabel;
    private JButton searchButton;
    private JPanel resultPanel;
    private LabelCreator creator;

    public FindStudent(LabelCreator creator) {
        this.creator = creator;
    }
    public FindStudent(){
        this(new LabelCreator());
    }

    public void create(){
        searchFrame = creator.createSearchFrame("Search Student");
        idField = creator.createIdField();
        lastNameField = creator.createLastNameField();
        idLabel = creator.createIdLabel("Enter Student ID:");
        lastNameLabel = creator.createLastNameLabel("Or Enter Last Name:");
        searchButton = creator.searchButton("Search");
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
