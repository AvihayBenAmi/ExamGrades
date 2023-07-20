package org.example;

import javax.swing.plaf.TableHeaderUI;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final SharedResource<Student> sharedResource = new SharedResource<>();
        final GradeBook gradeBook = new GradeBook();

        Thread threadA = new Thread(() -> {
            while (true) {
                sharedResource.addToList(new Student());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(() -> {
            while (true) {
                Student student = sharedResource.removeFromList();
                if (student != null) {
                    System.out.println("Processed: " + student);
                    if (gradeBook.getTopStudent() == student) {
                        gradeBook.updateTopStudent(null);
                    }
                }
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadC = new Thread(() -> {
            while (true) {
                List<Student> students = sharedResource.getList();
                int numOfStudents = students.size();
                System.out.println("Number of students: " + numOfStudents);
                if (numOfStudents > 0) {
                    Student topStudent = students.get(0);
                    for (Student student : students) {
                        if (gradeBook.getTopStudent() == student) {
                            topStudent = student;
                            break;
                        }
                    }
                    System.out.println("Top student: " + topStudent);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
    }
}