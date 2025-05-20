package gui;

import manager.GradingSystem;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {
    private JFrame parentFrame;
    private GradingSystem system;

    public MainMenuPanel(JFrame frame, GradingSystem system) {
        this.parentFrame = frame;
        this.system = system;

        setLayout(new BorderLayout());

        JLabel title = new JLabel("Student Grading System", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        add(title, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        JButton addStudentBtn = new JButton("Add Student");
        JButton addCourseBtn = new JButton("Add Course");
        JButton assignGradeBtn = new JButton("Assign Grade");
        JButton reportCardBtn = new JButton("View Report Card");
        JButton exitBtn = new JButton("Exit");

        buttonPanel.add(addStudentBtn);
        buttonPanel.add(addCourseBtn);
        buttonPanel.add(assignGradeBtn);
        buttonPanel.add(reportCardBtn);
        buttonPanel.add(exitBtn);

        add(buttonPanel, BorderLayout.CENTER);

        addStudentBtn.addActionListener(e -> {
            parentFrame.setContentPane(new AddStudentPanel(system, parentFrame));
            parentFrame.revalidate();
            parentFrame.repaint();
        });


        addCourseBtn.addActionListener(e -> {
            parentFrame.setContentPane(new AddCoursePanel(system, parentFrame));
            parentFrame.revalidate();
            parentFrame.repaint();
        });

        assignGradeBtn.addActionListener(e -> {
            parentFrame.setContentPane(new AssignGradePanel(system, parentFrame));
            parentFrame.revalidate();
            parentFrame.repaint();
        });

        reportCardBtn.addActionListener(e -> {
            parentFrame.setContentPane(new ReportCardPanel(system, parentFrame));
            parentFrame.revalidate();
            parentFrame.repaint();
        });

        exitBtn.addActionListener(e -> System.exit(0));
    }
}

