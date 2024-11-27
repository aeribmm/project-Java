package org.aeribmm;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class WindowStyle {

    public void setStyleTable(JTable studentTable){
        // Настройки для темного стиля таблицы
        studentTable.setBackground(new Color(43, 43, 43));        // Тёмный фон для ячеек
        studentTable.setForeground(Color.WHITE);                  // Белый текст
        studentTable.setGridColor(new Color(60, 63, 65));         // Цвет сетки между ячейками
        studentTable.setSelectionBackground(new Color(75, 110, 175));  // Цвет выделения строк
        studentTable.setSelectionForeground(Color.WHITE);         // Текст выделенной строки

        // Настройки для заголовка таблицы
        JTableHeader tableHeader = studentTable.getTableHeader();
        tableHeader.setBackground(new Color(60, 63, 65));         // Темный фон для заголовков
        tableHeader.setForeground(Color.WHITE);                   // Белый текст заголовков
        tableHeader.setFont(new Font("SansSerif", Font.BOLD, 14));
    }
}
