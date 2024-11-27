package org.aeribmm;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            Application frame = new Application();
            frame.setVisible(true);
        });
    }
}