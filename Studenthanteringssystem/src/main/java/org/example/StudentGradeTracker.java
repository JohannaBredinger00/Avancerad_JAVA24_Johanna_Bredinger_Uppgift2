package org.example;

import javax.xml.transform.Source;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

class Student {
    private int id;
    private int grade;
    private String name;


    public Student(int id, String name, int grade) {
        this.id = id;
        this.grade = grade;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + grade;
    }
}

    public class StudentGradeTracker {

    private static StudentGradeTracker instance;
    private static final String FILE_NAME = "students.txt";
    private HashMap<Integer, Student> studentDatabase = new HashMap<>();

    private StudentGradeTracker(){
        loadStudentsFromFile();

    }

    public static StudentGradeTracker getInstance() {
        if (instance == null) {
            synchronized (StudentGradeTracker.class) {
                if (instance == null) {
                    instance = new StudentGradeTracker();
                }
            }
        }
        return instance;
    }

    public void addStudent(int id, String name, int grade){
        Student newStudent = new Student(id, name, grade);
        studentDatabase.put(id, newStudent);
        System.out.println("Student added");
    }

    public void displayAllStudents(){
        if (studentDatabase.isEmpty()){
            System.out.println("No students found.");
        }else {
            System.out.println("All sorted stuents:");
            for (Student student : studentDatabase.values()){
                System.out.println(student);
            }
        }
    }

    public void saveStudentsToFile(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))){
            for (Student student : studentDatabase.values()){
                writer.write(student.toString());
                writer.newLine();
            }
            System.out.println("Student data has been saved to: " + FILE_NAME);
        }catch (IOException e){
            System.out.println("An error occoured while saving the data:" + e.getMessage());
        }
    }

    private void loadStudentsFromFile(){
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] studentData = line.split(",");
                int id = Integer.parseInt(studentData[0]);
                String name = studentData[1];
                int grade = Integer.parseInt(studentData[2]);
                studentDatabase.put(id, new Student(id, name, grade));
            }
        } catch (Exception e) {
            System.out.println("No previous student data found or error reading file.");
        }
    }

    public void findStudentById(int id){
        if (studentDatabase.containsKey(id)){
            Student student = studentDatabase.get(id);
            System.out.println("Student found: " + student);
        }else {
            System.out.println("No student found with ID: " + id);
        }
    }


        public static void main(String[] args) {
        StudentGradeTracker tracker = StudentGradeTracker.getInstance();
            Scanner scan = new Scanner(System.in);
            boolean running = true;


            while (running) {
                //Meny för att välja mellan alternativ
                System.out.println("Choose an option: ");
                System.out.println("1. Add Student ID ");
                System.out.println("2. Add Student Name ");
                System.out.println("3. Add Student Grade ");
                System.out.println("4. Display All Student ");
                System.out.println("5. Save Students to File ");
                System.out.println("6. Find student by ID ");
                System.out.println("7. Exit");

                int choice = scan.nextInt();
                scan.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Enter Students ID: ");
                        int id = scan.nextInt();
                        scan.nextLine();
                        System.out.println("Enter Students name: ");
                        String name = scan.nextLine();
                        System.out.println("Enter Students Grade: ");
                        int grade = scan.nextInt();
                        scan.nextLine();
                        tracker.addStudent(id, name, grade);
                        break;


                    case 2:
                        tracker.displayAllStudents();
                        break;


                    case 3:
                        tracker.saveStudentsToFile();
                        break;

                    case 4:
                        System.out.println("Enter Student ID:");
                        int searchId = scan.nextInt();
                        scan.nextLine();
                        tracker.findStudentById(searchId);
                        break;

                    case 5:
                        running = false;
                        System.out.println("Exiting program.");
                        break;

                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            }

            scan.close();
                }

            }




