package manager;

import model.Course;
import model.Enrollment;
import model.Student;

import java.util.ArrayList;
import java.util.List;

public class GradingSystem {
    private List<Student> students;
    private List<Course> courses;

    public GradingSystem() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
    }

    public void addStudent(String name) {
        students.add(new Student(name));
    }

    public void addCourse(String code, String name, int creditHours) {
        courses.add(new Course(code, name, creditHours));
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public Student findStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    public Course findCourseByCode(String code) {
        for (Course c : courses) {
            if (c.getCode().equalsIgnoreCase(code)) return c;
        }
        return null;
    }

    public void assignGrade(int studentId, String courseCode, double grade) {
        Student student = findStudentById(studentId);
        Course course = findCourseByCode(courseCode);

        if (student != null && course != null) {
            Enrollment enrollment = new Enrollment(course, grade);
            student.enroll(enrollment);
        }
    }

    public double calculateGPA(Student student) {
        double totalPoints = 0;
        int totalCredits = 0;

        for (Enrollment e : student.getEnrollments()) {
            double grade = e.getGrade();
            int credits = e.getCourse().getCreditHours();
            double point = convertToPoint(grade);
            totalPoints += point * credits;
            totalCredits += credits;
        }

        return totalCredits == 0 ? 0 : totalPoints / totalCredits;
    }

    private double convertToPoint(double grade) {
        if (grade >= 90) return 4.0;
        else if (grade >= 85) return 3.7;
        else if (grade >= 80) return 3.3;
        else if (grade >= 75) return 3.0;
        else if (grade >= 70) return 2.7;
        else if (grade >= 65) return 2.3;
        else if (grade >= 62) return 2.0;
        else if (grade >= 58) return 1.7;
        else if (grade >= 55) return 1.3;
        else if (grade >= 50) return 1.0;
        else return 0.0;
    }
}

