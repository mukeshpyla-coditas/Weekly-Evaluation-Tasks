package Week4.student_results_analyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

record Student(String name, int marks) {}

public class StudentResultsAnalyzer {
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        boolean choice = true;

        while(choice) {
            System.out.print("Enter name of the Student: ");
            String name = s.nextLine();
            System.out.print("Enter marks of the student: ");
            int marks = s.nextInt();
            s.nextLine();
            studentList.add(new Student(name, marks));
            System.out.print("Do you want to enter marks of other students?(1 -> YES ; 0 -> NO): ");
            int val = s.nextInt();
            s.nextLine();
            if(val == 0) choice = false;
        }

        printTopper(studentList);
        printLowestMarks(studentList);
        reversedList(studentList);
    }

    // Used .max() terminal operation
    private static void printTopper(List<Student> studentList) {
        Student student =  studentList.stream()
                .max((s1, s2) -> s1.marks() - s2.marks())
                .orElseThrow();

        System.out.println("Topper: " + student.name() + " with marks: " + student.marks());
    }

    // Used .min() terminal operation
    private static void printLowestMarks(List<Student> studentList) {
        Student student =  studentList.stream()
                .min((s1, s2) -> s1.marks() - s2.marks())
                .orElseThrow();

        System.out.println("Lowest Marks: " + student.name() + " with marks: " + student.marks());
    }

    // Used .toList() and reversed() terminal operations together as shown
    private static void reversedList(List<Student> studentList) {
        List<Student> studentsInReversedOrder = studentList.stream()
                .toList().reversed();

        System.out.println("Students in reversed order: ");
        for(Student student : studentsInReversedOrder) {
            System.out.println(student.name() + " : " + student.marks());
        }
    }
}
