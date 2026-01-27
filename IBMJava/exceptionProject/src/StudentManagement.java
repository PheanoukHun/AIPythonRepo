// File: StudentManagement.java

// Importing Scanner Class
import java.util.Scanner;

// Student class implementing Cloneable Interface
class Student implements Cloneable {
    private String name;
    private int age;
    private String major;

    public Student(String name, int age, String major) throws StudentUnderAgeException, StudentNameValidationException {
        if (age < 18) { throw new StudentUnderAgeException("Student Age has to be 18 or More"); }
        if (name.matches("^[a-zA-Z' -]+$") == false) { throw new StudentNameValidationException("Please Input a Valid Student Name"); }

        this.name = name;
        this.age = age;
        this.major = major;
    }

    public String getName() { return this.name; }
    public int getAge() { return this.age; }
    public String getMajor() { return this.major; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setMajor(String major) { this.major = major; }

    @Override
    public String toString() {
        return "Student: \n\t- Name: " + name + "\n\t- Age: " + age + "\n\t- Major: " + major;
    }

    @Override
    public Student clone() throws CloneNotSupportedException {
        return (Student) super.clone();
    }
}

public class StudentManagement {
    public static void main(String[] args) {
        
        // Create a Scanner Object to Read User Input
        Scanner sc = new Scanner(System.in);

        try {
            // Introduction to the Program
            System.out.println("\nCreating a new Student:");

            // Name Input
            System.out.print("\tEnter the Student's Name Here: ");
            String name = sc.nextLine();

            // Age Input
            System.out.print("\tEnter the Student's Age Here: ");
            int age = Integer.parseInt(sc.nextLine());

            // Major Input
            System.out.print("\tEnter the Student's Major Here: ");
            String major = sc.nextLine();

            // Creating a Student Object
            Student student1 = new Student(name, age, major);
            System.out.println(student1 + "\nSuccessfully Created\n");

            // Cloning the Student Object
            Student student2 = student1.clone();
            System.out.println(student2 + "\nSuccessfully Cloned");
        }
        
        catch (CloneNotSupportedException e) { System.out.println("You can't clone this object!"); }
        catch (StudentUnderAgeException e) { System.out.println(e.getMessage()); }
        catch (NumberFormatException e) { System.out.println("Age has to be a Number."); }
        catch (StudentNameValidationException e) { System.out.println(e.getMessage()); }
        
        finally { 
            System.out.println();
            sc.close();
        }
    }
}
