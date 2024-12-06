package org.aeribmm;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class WindowStyle {

    public void setStyleTable(JTable studentTable){
        studentTable.setBackground(new Color(43, 43, 43));        // Тёмный фон для ячеек
        studentTable.setForeground(Color.WHITE);                  // Белый текст
        studentTable.setGridColor(new Color(60, 63, 65));         // Цвет сетки между ячейками
        studentTable.setSelectionBackground(new Color(75, 110, 175));  // Цвет выделения строк
        studentTable.setSelectionForeground(Color.WHITE);         // Текст выделенной строки
        JTableHeader tableHeader = studentTable.getTableHeader();
        tableHeader.setBackground(new Color(60, 63, 65));         // Темный фон для заголовков
        tableHeader.setForeground(Color.WHITE);                   // Белый текст заголовков
        tableHeader.setFont(new Font("SansSerif", Font.BOLD, 14));
    }
    public void searchFrameStyle(JFrame searchFrame){
        searchFrame.getContentPane().setBackground(new Color(45, 45, 45));
    }

    public void idField(JTextField idField){
        idField.setBackground(new Color(60, 60, 60));
        idField.setForeground(Color.WHITE);
        idField.setCaretColor(Color.WHITE);
        idField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    public void lastNameField(JTextField lastNameField){
        lastNameField.setBackground(new Color(60, 60, 60));
        lastNameField.setForeground(Color.WHITE);
        lastNameField.setCaretColor(Color.WHITE);
        lastNameField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }
}
