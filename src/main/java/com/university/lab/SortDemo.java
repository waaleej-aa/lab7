package com.university.lab;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Lab Task 4 - Internal (Comparable) vs external (Comparator) ordering.
 */
public class SortDemo {

    // Step 1 + 2: natural ordering = rollNo ascending (internal rule).
    static class Student implements Comparable<Student> {
        private final int rollNo;
        private final double cgpa;

        public Student(int rollNo, double cgpa) {
            this.rollNo = rollNo;
            this.cgpa = cgpa;
        }

        public int getRollNo() { return rollNo; }
        public double getCgpa() { return cgpa; }

        @Override
        public int compareTo(Student other) {
            return Integer.compare(this.rollNo, other.rollNo);   // ascending
        }

        @Override
        public String toString() {
            return "Student{rollNo=" + rollNo + ", cgpa=" + cgpa + "}";
        }
    }

    // Step 3: external rule = cgpa descending (lives outside Student).
    static class CgpaComparator implements Comparator<Student> {
        @Override
        public int compare(Student a, Student b) {
            return Double.compare(b.getCgpa(), a.getCgpa());     // descending
        }
    }

    // Step 4: demonstrate both.
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student(103, 3.55));
        students.add(new Student(101, 3.20));
        students.add(new Student(102, 3.85));

        System.out.println("Original:");
        students.forEach(System.out::println);

        Collections.sort(students);                    // uses compareTo
        System.out.println("\nNatural order (Comparable, rollNo asc):");
        students.forEach(System.out::println);

        students.sort(new CgpaComparator());           // uses compare
        System.out.println("\nExternal order (CgpaComparator, cgpa desc):");
        students.forEach(System.out::println);
    }
}