package gui;

import manager.GradingSystem;
import model.Course;
import model.Student;

import javax.swing.*;
import java.awt.*;

public class AssignGradePanel extends JPanel {
    private GradingSystem system;
    private JFrame parentFrame;

    private JComboBox<Student> studentCombo;
    private JComboBox<Course> courseCombo;
    private JTextField gradeField;
    private JButton assignButton;
    private JLabel messageLabel;

    public AssignGradePanel(GradingSystem system, JFrame parentFrame) {
        this.system = system;
        this.parentFrame = parentFrame;

        setLayout(new BorderLayout());

        JLabel title = new JLabel("Assign Grade", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        studentCombo = new JComboBox<>(system.getStudents().toArray(new Student[0]));
        courseCombo = new JComboBox<>(system.getCourses().toArray(new Course[0]));
        gradeField = new JTextField();

        form.add(new JLabel("Select Student:"));
        form.add(studentCombo);
        form.add(new JLabel("Select Course:"));
        form.add(courseCombo);
        form.add(new JLabel("Enter Grade (0–100):"));
        form.add(gradeField);

        add(form, BorderLayout.CENTER);

        assignButton = new JButton("Assign Grade");
        messageLabel = new JLabel("", SwingConstants.CENTER);
        JButton backButton = new JButton("Back to Menu");

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(assignButton, BorderLayout.CENTER);
        bottomPanel.add(backButton, BorderLayout.EAST);
        bottomPanel.add(messageLabel, BorderLayout.NORTH);

        add(bottomPanel, BorderLayout.SOUTH);

        assignButton.addActionListener(e -> {
            try {
                Student selectedStudent = (Student) studentCombo.getSelectedItem();
                Course selectedCourse = (Course) courseCombo.getSelectedItem();
                double grade = Double.parseDouble(gradeField.getText().trim());

                if (grade < 0 || grade > 100) {
                    messageLabel.setText("Grade must be between 0 and 100.");
                    return;
                }

                system.assignGrade(selectedStudent.getId(), selectedCourse.getCode(), grade);
                messageLabel.setText("Grade assigned successfully!");
                gradeField.setText("");

            } catch (NumberFormatException ex) {
                messageLabel.setText("Please enter a valid number.");
            } catch (Exception ex) {
                messageLabel.setText("Error assigning grade.");
            }
        });

        backButton.addActionListener(e -> {
            parentFrame.setContentPane(new MainMenuPanel(parentFrame, system));
            parentFrame.revalidate();
            parentFrame.repaint();


        });
    }
}
