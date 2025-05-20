package model;

public class Enrollment {
    private Course course;
    private double grade;

    public Enrollment(Course course, double grade) {
        this.course = course;
        this.grade = grade;
    }

    public Course getCourse() {
        return course;
    }

    public double getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return course.getName() + " - Grade: " + grade;
    }
}


