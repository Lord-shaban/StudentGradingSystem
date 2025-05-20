package gui;

import manager.GradingSystem;

import javax.swing.*;
import java.awt.*;

public class AddCoursePanel extends JPanel {
    public AddCoursePanel(GradingSystem system, JFrame parentFrame) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Add New Course", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JTextField codeField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField creditField = new JTextField();

        form.add(new JLabel("Course Code:"));
        form.add(codeField);
        form.add(new JLabel("Course Name:"));
        form.add(nameField);
        form.add(new JLabel("Credit Hours:"));
        form.add(creditField);

        add(form, BorderLayout.CENTER);

        JButton addButton = new JButton("Add Course");
        JLabel messageLabel = new JLabel("", SwingConstants.CENTER);
        JButton backButton = new JButton("Back to Menu");

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(addButton, BorderLayout.CENTER);
        bottom.add(backButton, BorderLayout.EAST);
        bottom.add(messageLabel, BorderLayout.NORTH);

        add(bottom, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            String code = codeField.getText().trim();
            String name = nameField.getText().trim();
            String creditText = creditField.getText().trim();

            if (code.isEmpty() || name.isEmpty() || creditText.isEmpty()) {
                messageLabel.setText("Please fill in all fields.");
                return;
            }

            try {
                int credit = Integer.parseInt(creditText);
                if (credit <= 0) throw new NumberFormatException();
                system.addCourse(code, name, credit);
                messageLabel.setText("Course \"" + code + "\" added successfully!");
                codeField.setText("");
                nameField.setText("");
                creditField.setText("");
            } catch (NumberFormatException ex) {
                messageLabel.setText("Credit hours must be a positive number.");
            }
        });

        backButton.addActionListener(e -> {
            parentFrame.setContentPane(new MainMenuPanel(parentFrame, system));
            parentFrame.revalidate();
            parentFrame.repaint();


                });
    }
}

