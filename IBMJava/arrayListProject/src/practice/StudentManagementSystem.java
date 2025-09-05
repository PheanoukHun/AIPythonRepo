import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class StudentManagementSystem {
    public static void main(String[] args) {
        // Create an ArrayList to store Student objects
        ArrayList<Student> students = new ArrayList<>();

        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Infinite loop to keep the program running until the user chooses to exit
        while (true) {
            // Display the menu options to the user
            System.out.println("\nMenu:");
            System.out.println("1. Add a student");
            System.out.println("2. Update a student");
            System.out.println("3. Delete a student");
            System.out.println("4. View all students");
            System.out.println("5. Sort students by age");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            // Read the user's choice
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            // Option 1: Add a student
            if (choice == 1) {
                System.out.print("Enter student name: ");
                String name = scanner.nextLine();

                System.out.print("Enter student age: ");
                int age = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                System.out.print("Enter student major: ");
                String major = scanner.nextLine();

                // Create a new Student object and add it to the list
                students.add(new Student(name, age, major));
                System.out.println("Student added successfully!");
            }
            // Option 2: Update a student
            else if (choice == 2) {
                System.out.print("Enter the index of the student to update: ");
                int index = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                // Check if the index is valid
                if (index >= 0 && index < students.size()) {
                    System.out.print("Enter new name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter new age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    System.out.print("Enter new major: ");
                    String major = scanner.nextLine();

                    // Update the student's details
                    students.get(index).setName(name);
                    students.get(index).setAge(age);
                    students.get(index).setMajor(major);
                    System.out.println("Student updated successfully!");
                } else {
                    System.out.println("Invalid index. No student found at the specified index.");
                }
            }
            // Option 3: Delete a student
            else if (choice == 3) {
                System.out.print("Enter the index of the student to delete: ");
                int index = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                // Check if the index is valid
                if (index >= 0 && index < students.size()) {
                    students.remove(index);
                    System.out.println("Student deleted successfully!");
                } else {
                    System.out.println("Invalid index. No student found at the specified index.");
                }
            }
            // Option 4: View all students
            else if (choice == 4) {
                if (students.isEmpty()) {
                    System.out.println("No students found.");
                } else {
                    System.out.println("\nList of Students:");
                    for (int i = 0; i < students.size(); i++) {
                        System.out.println(i + ". " + students.get(i));
                    }
                }
            }
            // Option 5: Sort students by age
            else if (choice == 5) {
                // Sort the students using a custom Comparator
                Collections.sort(students, new Comparator<Student>() {
                    @Override
                    public int compare(Student s1, Student s2) {
                        // Sort in ascending order of age
                        return Integer.compare(s1.getAge(), s2.getAge());
                    }
                });

                System.out.println("Students sorted by age (ascending):");
                for (int i = 0; i < students.size(); i++) {
                    System.out.println(i + ". " + students.get(i));
                }
            }
            // Option 6: Exit the program
            else if (choice == 6) {
                System.out.println("Exiting the program. Goodbye!");
                break;
            }
            // Handle invalid choices
            else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        // Close the scanner
        scanner.close();
    }
}