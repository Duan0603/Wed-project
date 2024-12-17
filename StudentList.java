package model;

import view.Validation;

import java.util.ArrayList;
import java.util.Collections;

public class StudentList {
    private ArrayList<Student> students;

    public StudentList() {
        this.students = new ArrayList<>();
    }

    public void report() {
        this.students.forEach(System.out::println);
    }

    public void createStudent() {
        String id = Validation.getString("Enter Student id: ");
        String name = Validation.getString("Enter Student name: ");
        String semester = Validation.getString("Enter Semester: ");
        String course = Validation.getString("Enter course: ");

        this.students.add(new Student(id, name, semester, course));
    }

    public void findAndSort() {
        String name = Validation.getString("Enter name to search: ");
        this.students.stream().filter(p -> p.getStudentName().equalsIgnoreCase(name)).forEach(System.out::println);

        Collections.sort(students, Student::compareTo);
        System.out.println("List are sorted");
        report();
    }

    public void updateOrDelete(){
        String id = Validation.getString("Enter student ID to update/delete");
        int i = Validation.checkIDExist(this.students, id);
        if (Validation.getString("Update / delete").equalsIgnoreCase("Update")) {
            this.students.get(i).setStudentName(Validation.getString("Enter new name: "));
            this.students.get(i).setSemester(Validation.getString("Enter new semester: "));
            this.students.get(i).setCourseName(Validation.getString("Enter new course: "));
        }else {
            this.students.remove(i);
        }
    }
}
