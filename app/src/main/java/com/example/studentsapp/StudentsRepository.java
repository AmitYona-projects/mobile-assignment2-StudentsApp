package com.example.studentsapp;

import java.util.ArrayList;
import java.util.List;

public class StudentsRepository {
    private static StudentsRepository instance;
    private List<Student> students;

    private StudentsRepository() {
        students = new ArrayList<>();
        // Add some sample data for testing
        students.add(new Student("2992929292992", "Kuku", "05434534534", "Rishon...", true));
        students.add(new Student("1234567890123", "John Doe", "0501234567", "Tel Aviv", false));
        students.add(new Student("9876543210987", "Jane Smith", "0529876543", "Haifa", true));
    }

    public static StudentsRepository getInstance() {
        if (instance == null) {
            instance = new StudentsRepository();
        }
        return instance;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public Student getStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void updateStudent(String oldId, Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(oldId)) {
                students.set(i, updatedStudent);
                return;
            }
        }
    }

    public void deleteStudent(String id) {
        students.removeIf(student -> student.getId().equals(id));
    }

    public void toggleCheckStatus(String id) {
        Student student = getStudentById(id);
        if (student != null) {
            student.setChecked(!student.isChecked());
        }
    }
}
