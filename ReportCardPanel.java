package gui;

import manager.GradingSystem;
import model.Enrollment;
import model.Student;

import javax.swing.*;
import java.awt.*;

public class ReportCardPanel extends JPanel {
    private GradingSystem system;
    private JFrame parentFrame;

    private JComboBox<Student> studentCombo;
    private JTextArea reportArea;
    private JButton backButton;

    public ReportCardPanel(GradingSystem system, JFrame parentFrame) {
        this.system = system;
        this.parentFrame = parentFrame;

        setLayout(new BorderLayout());

        JLabel title = new JLabel("Student Report Card", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        studentCombo = new JComboBox<>(system.getStudents().toArray(new Student[0]));
        add(studentCombo, BorderLayout.NORTH);

        reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(reportArea);
        add(scroll, BorderLayout.CENTER);

        backButton = new JButton("Back to Menu");
        add(backButton, BorderLayout.SOUTH);

        studentCombo.addActionListener(e -> updateReport());

        backButton.addActionListener(e -> {
                parentFrame.setContentPane(new MainMenuPanel(parentFrame, system));
            parentFrame.revalidate();
            parentFrame.repaint();

        });

        if (studentCombo.getItemCount() > 0) {
            studentCombo.setSelectedIndex(0);
            updateReport();
        } else {
            reportArea.setText("No students available.");
        }
    }

    private void updateReport() {
        Student student = (Student) studentCombo.getSelectedItem();
        if (student == null) return;

        StringBuilder sb = new StringBuilder();
        sb.append("Report Card for ").append(student.getName()).append("\n");
        sb.append("ID: ").append(student.getId()).append("\n\n");

        if (student.getEnrollments().isEmpty()) {
            sb.append("No courses enrolled.\n");
        } else {
            sb.append(String.format("%-20s %-10s %-10s\n", "Course", "Credits", "Grade"));
            sb.append("------------------------------------------------\n");
            for (Enrollment e : student.getEnrollments()) {
                String courseName = e.getCourse().getName();
                int credits = e.getCourse().getCreditHours();
                double grade = e.getGrade();
                sb.append(String.format("%-20s %-10d %-10.2f\n", courseName, credits, grade));
            }

            double gpa = system.calculateGPA(student);
            sb.append("\nGPA: ").append(String.format("%.2f", gpa));
        }

        reportArea.setText(sb.toString());
    }
}

