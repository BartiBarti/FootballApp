package pl.footballapp.bartek.gui;

import pl.footballapp.bartek.repository.DatabaseConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainTable extends JFrame {
    private JPanel mainPanel;

    public MainTable() throws HeadlessException {
        setTitle("Football App");
        setSize(500, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(mainPanel);
    }

    public static void main(String[] args) {
        DatabaseConnection.getInstance();
        SwingUtilities.invokeLater(() -> new MainTable().setVisible(true));
    }
}
