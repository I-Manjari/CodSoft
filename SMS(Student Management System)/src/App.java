import java.io.*;
import java.util.*;

class Student {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }
}

class StudentManagementSystem {
    private ArrayList<Student> students;

    public StudentManagementSystem() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public Student searchStudent(String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null; // Not found
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }

    public void saveToFile(String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Student student : students) {
                writer.println(student.getName() + "," + student.getRollNumber() + "," + student.getGrade());
            }
        }
    }

    public void loadFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    int rollNumber = Integer.parseInt(parts[1]);
                    String grade = parts[2];
                    students.add(new Student(name, rollNumber, grade));
                }
            }
        }
    }
}

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();

        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Save to File");
            System.out.println("6. Load from File");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add Student
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter roll number: ");
                    int rollNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter grade: ");
                    String grade = scanner.nextLine();
                    sms.addStudent(new Student(name, rollNumber, grade));
                    break;
                case 2:
                    // Remove Student
                    System.out.print("Enter student name to remove: ");
                    String nameToRemove = scanner.nextLine();
                    Student studentToRemove = sms.searchStudent(nameToRemove);
                    if (studentToRemove != null) {
                        sms.removeStudent(studentToRemove);
                        System.out.println("Student removed.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 3:
                    // Search Student
                    System.out.print("Enter student name to search: ");
                    String nameToSearch = scanner.nextLine();
                    Student studentToSearch = sms.searchStudent(nameToSearch);
                    if (studentToSearch != null) {
                        System.out.println("Student found:");
                        System.out.println("Name: " + studentToSearch.getName());
                        System.out.println("Roll Number: " + studentToSearch.getRollNumber());
                        System.out.println("Grade: " + studentToSearch.getGrade());
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    // Display All Students
                    ArrayList<Student> allStudents = sms.getAllStudents();
                    if (!allStudents.isEmpty()) {
                        System.out.println("All students:");
                        for (Student student : allStudents) {
                            System.out.println("Name: " + student.getName());
                            System.out.println("Roll Number: " + student.getRollNumber());
                            System.out.println("Grade: " + student.getGrade());
                            System.out.println("---------------");
                        }
                    } else {
                        System.out.println("No students to display.");
                    }
                    break;
                case 5:
                    // Save to File
                    try {
                        sms.saveToFile("students.txt");
                        System.out.println("Data saved to file.");
                    } catch (IOException e) {
                        System.out.println("Error saving data to file.");
                    }
                    break;
                case 6:
                    // Load from File
                    try {
                        sms.loadFromFile("students.txt");
                        System.out.println("Data loaded from file.");
                    } catch (IOException e) {
                        System.out.println("Error loading data from file.");
                    }
                    break;
                case 7:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
