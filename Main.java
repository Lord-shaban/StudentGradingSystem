import gui.MainMenuPanel;
import manager.GradingSystem;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GradingSystem system = new GradingSystem();

            JFrame frame = new JFrame("Student Grading System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null);

            frame.setContentPane(new MainMenuPanel(frame, system));
            frame.setVisible(true);
        });
    }
}