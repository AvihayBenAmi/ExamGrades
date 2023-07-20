package org.example;

import java.util.*;


class Student {
    private static final List<String> FIRST_NAMES = Arrays.asList("John", "Emma", "Michael", "Sophia", "David", "Emily");
    private static final List<String> LAST_NAMES = Arrays.asList("Smith", "Johnson", "Brown", "Taylor", "Miller", "Davis");
    private static final List<String> COURSES = Arrays.asList("Math", "Science", "History", "Geography", "Music", "Art");
    private static final Random RANDOM = new Random();

    private final String firstName;
    private final String lastName;
    private final Map<String, Integer> coursesGrades;

    public Student() {
        this.firstName = FIRST_NAMES.get(RANDOM.nextInt(FIRST_NAMES.size()));
        this.lastName = LAST_NAMES.get(RANDOM.nextInt(LAST_NAMES.size()));
        this.coursesGrades = new HashMap<>();
        COURSES.forEach(course -> coursesGrades.put(course, RANDOM.nextInt(101)));
    }

    public Map<String, Integer> getCoursesGrades() { //מתודה שהוספתי כדי לקבל את הציונים של הסטודנט לצורך חישוב ממוצע
        return coursesGrades;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", coursesGrades=" + coursesGrades +
                '}';
    }

    @Override
    public boolean equals(Object o) { //מתודת השוואה שהוספתי לצורך מחיקה
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(coursesGrades, student.coursesGrades);
    }
}