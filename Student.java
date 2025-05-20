package model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private static int nextId = 1;

    private int id;
    private String name;
    private List<Enrollment> enrollments;

    public Student(String name) {
        this.id = nextId++;
        this.name = name;
        this.enrollments = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void enroll(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    @Override
    public String toString() {
        return "Student #" + id + ": " + name;
    }
}


