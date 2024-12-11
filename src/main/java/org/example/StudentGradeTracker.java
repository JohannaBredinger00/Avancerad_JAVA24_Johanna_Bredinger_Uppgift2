package org.example;

import java.io.*;
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

    private static final String FILE_NAME = "students.txt";

        public static void main(String[] args) {
            HashMap<Integer, Student> studentDatabase = new HashMap<>();
            Scanner scan = new Scanner(System.in);

            //Läser in data från den befintliga filen
            loadStudentsFromFile(studentDatabase);

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

                        //Skapa en ny student och lägger till i HashMap
                        Student newStudent = new Student(id, name, grade);
                        studentDatabase.put(id, newStudent);
                        System.out.println("The student is added.");
                        break;


                    case 2:
                        //Visa betyg för den specifika studenten
                        System.out.println("Enter students ID to show the grade: ");
                        int studentId = scan.nextInt();
                        scan.nextLine();

                        if (studentDatabase.containsKey(studentId)) {
                            Student student = studentDatabase.get(studentId);
                            System.out.println("Students grade: " + student);
                        } else {
                            System.out.println("Student does not exist");
                        }
                        break;


                    case 3:
                        //Visa alla studenters betyg
                        System.out.println("All students grades: ");
                        for (Student student : studentDatabase.values()) {
                            System.out.println(student);
                        }
                        break;

                    case 4:
                        //Visa alla studentposter som ID, namn och betyg
                        displayAllStudents(studentDatabase);
                        break;

                    case 5:
                        //Spara alla studentposter till en fil
                        saveStudentsToFile(studentDatabase);
                        break;


                    case 6:
                        //Hitta student efter ID
                        System.out.println("Enter student ID to find the student: ");
                        int findId = scan.nextInt();
                        scan.nextLine();
                        findStudentById(studentDatabase, findId);
                        break;


                    case 7:
                        //Avsluta programmet
                        running = false;
                        System.out.println("The program is canceling.");
                        break;

                    default:
                        System.out.println("Invalid choice, Please try again:");
                }

            }
            //Stäng scanner
            scan.close();
        }


        //Skapar metod för att spara all studentdata til en textfil
        public static void saveStudentsToFile(HashMap<Integer, Student> studentDatabase){
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt."))) {
                for (Student student : studentDatabase.values()) {
                    writer.write(student.toString()); //Skriver studentens information
                    writer.newLine(); //Skapar ny rad för varje student
                }
                System.out.println("Student data has been saved to: " + FILE_NAME);
            } catch (IOException e) {
                System.out.println("An error occured while saving the data: " + e.getMessage());
            }
        }

        public static void loadStudentsFromFile(HashMap<Integer, Student> studentDatabase) {
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] studentData = line.split(",");
                    int id = Integer.parseInt(studentData[0]);
                    String name = studentData[1];
                    int grade = Integer.parseInt(studentData[2]);

                    //Lägger till student i HashMap
                    Student student = new Student(id, name, grade);
                    studentDatabase.put(id, student);
                }
            } catch (Exception e) {
                //Om filen inte finns ännu
                System.out.println("No previous student data found or error reading file.");
            }
        }

        //Skapa metod för att hitta samt skriva ut en student genom ID
        private static void findStudentById(HashMap<Integer, Student> studentDatabase, int id) {
            if (studentDatabase.containsKey(id)){
                Student student = studentDatabase.get(id);
                System.out.println("Student found: " + student);
            }else {
                System.out.println("No student found with ID: " + id);
            }
        }

        //Skapa metod för att visa alla studentposter som ID, namn och betyg
        public static void displayAllStudents(HashMap<Integer, Student> studentDatabase){
            if (studentDatabase.isEmpty()){
                System.out.println("No students found");
            }else {
                System.out.println("All stored students: ");
                for (Student student : studentDatabase.values()){
                    System.out.println(student); // visar varje students uppgifter
                }
            }

        }
    }


