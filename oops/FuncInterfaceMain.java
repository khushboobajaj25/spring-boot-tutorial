package oops;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Student implements Comparable<Student> {
    String name;

    public Student(String name) {
        this.name = new String(name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Student student = (Student) obj;
        return name != null ? name.equals(student.name) : student.name == null;
    }

    @Override
    public String toString() {
        return name;
    }

    public int compareTo(Student student) {
        if (this.name.equals(student.name))
            return 0;

        if (this.name.compareTo(student.name) >= 1) {
            return 1;
        }

        if (this.name.compareTo(student.name) <= -1) {
            return -1;
        }

        return 0;
    }
}

class CustomStudentSort implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        if (s1.name.equals(s2.name))
            return 0;

        if (s1.name.compareTo(s2.name) >= 1) {
            return -1;
        }

        if (s1.name.compareTo(s2.name) <= -1) {
            return 1;
        }

        return 0;
    }
}

public class FuncInterfaceMain {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>(
                List.of(new Student("Karan"), new Student("Prium"), new Student("Khushboo"),
                        new Student("Anisha")));
        Collections.sort(students,
                (s1, s2) -> {
                    if (s1.name.equals(s2.name))
                        return 0;

                    if (s1.name.compareTo(s2.name) >= 1) {
                        return -1;
                    }

                    if (s1.name.compareTo(s2.name) <= -1) {
                        return 1;
                    }

                    return 0;
                });
        System.out.println(students);

        FuncInterface obj = (x, y) -> x + y;

        System.out.println(obj.sum(5, 6));
    }
}
