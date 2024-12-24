package org.aeribmm;

import javax.swing.*;
import java.awt.*;

public class RemoveStudent {
    private DAO dao;
    private JFrame searchFrame;
    private JPanel inputPanel;
    private JTextField idField;
    private JTextField lastNameField;
    private JLabel idLabel;
    private JLabel lastNameLabel;
    private JButton remove;

    private LabelCreator creator;
    public RemoveStudent(LabelCreator creator,DAO dao) {
        this.creator = creator;
        this.dao = dao;
    }
    public RemoveStudent(){
        this(new LabelCreator(),new DAO());
    }

    public void create(){
        searchFrame = creator.createSearchFrame("Remove student from DB");
        idField = creator.createIdField();
        lastNameField = creator.createLastNameField();
        idLabel = creator.createIdLabel("Enter Student ID:");
        lastNameLabel = creator.createLastNameLabel("Or Enter Last Name:");
        remove = creator.searchButton("Remove");
        inputPanel = creator.createInputPanel(idLabel,idField,lastNameLabel,lastNameField,remove);
        searchFrame.add(inputPanel, BorderLayout.NORTH);
        searchFrame.setVisible(true);
        remove.addActionListener(event -> {
            dao.removeStudent(idField,lastNameField);
        });

    }
}
