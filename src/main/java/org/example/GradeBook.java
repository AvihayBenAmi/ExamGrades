package org.example;

import java.util.Map;

class GradeBook {
    private final Object lock = new Object(); //מנעול
    private Student topStudent;

    public void updateTopStudent(Student student) {
        synchronized (lock) {
            if (topStudent == null || calculateAverageGrade(student) > calculateAverageGrade(this.topStudent)) {
                topStudent = student;
            }
        }
    }

    public Student getTopStudent() {
        synchronized (lock) {
            return topStudent;
        }
    }
    private double calculateAverageGrade(Student student) {
        Map<String, Integer> grades = student.getCoursesGrades();
        int total = grades.values().stream().mapToInt(Integer::intValue).sum();
        return (double) total / grades.size();
    }
}
