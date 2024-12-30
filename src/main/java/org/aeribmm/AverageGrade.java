package org.aeribmm;

import javax.swing.*;
import java.awt.*;

public class AverageGrade {
    private JFrame frame;
    private JLabel text;
    private LabelCreator creator;
    private double average;
    private DAO dao;

    public AverageGrade() {
        this(new LabelCreator(),new DAO());
    }

    public AverageGrade(LabelCreator label,DAO dataBaseLevel) {
        this.creator = label;
        this.dao = dataBaseLevel;
    }

    public void create(){
        average = dao.calculateAverageGrade();
        frame = creator.createSearchFrame("Average Grade");
        text = creator.createStyledLabel("Average Grade: " + String.format("%.2f",average));
        text.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(text,BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setPreferredSize(null);
    }
}
