package gui;

import manager.GradingSystem;

import javax.swing.*;
import java.awt.*;

public class AddStudentPanel extends JPanel {
    public AddStudentPanel(GradingSystem system, JFrame parentFrame) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Add New Student", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(1, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        form.add(new JLabel("Student Name:"));
        JTextField nameField = new JTextField();
        form.add(nameField);

        add(form, BorderLayout.CENTER);

        JButton addButton = new JButton("Add Student");
        JLabel messageLabel = new JLabel("", SwingConstants.CENTER);
        JButton backButton = new JButton("Back to Menu");

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(addButton, BorderLayout.CENTER);
        bottom.add(backButton, BorderLayout.EAST);
        bottom.add(messageLabel, BorderLayout.NORTH);

        add(bottom, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                system.addStudent(name);
                messageLabel.setText("Student \"" + name + "\" added successfully!");
                nameField.setText("");
            } else {
                messageLabel.setText("Please enter a valid name.");
            }
        });

        backButton.addActionListener(e -> {
            parentFrame.setContentPane(new MainMenuPanel(parentFrame, system));
            parentFrame.revalidate();
            parentFrame.repaint();
        });

    }
}
