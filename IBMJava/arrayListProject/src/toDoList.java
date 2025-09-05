import java.util.*;

public class toDoList {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        try {
            ArrayList<Task> todoList = new ArrayList<Task>();

            while (true) {
                System.out.println(
                    "Press 1 to add a task, " +
                    "\n2 to view all the tasks " +
                    "\n3 to change status of tasks " +
                    "\n4 to delete a task " +
                    "\nAny other key to exit\n");

                System.out.print("Enter Your Choice Here: ");
                String userAction = sc.nextLine();

                if (userAction.equals("1")) {
                    System.out.print("\nEnter the Task: ");
                    String taskStr = sc.nextLine();

                    print("Enter the Priority - 1 Low, 2 - Medium, 3 - High: ");
                    int priority = Integer.parseInt(sc.nextLine());

                    priority = priority > 3 ? 1 : priority;
                    todoList.add(new Task(taskStr, priority));
                    println("The task has been added to the List.");
                } else if (userAction.equals("2")) { todoList.forEach(task -> println(task)); }
                else if (userAction.equals("3")) {
                    print("Enter the Index of the Status You Want to Change: ");
                    int changeIndex = sc.nextLine();
                    if (changeIndex > (todoList.size() - 1)) {
                        println("There is no such index in the list.");
                    } else {
                        print("Enter the new Status for the Task P for 'In Progress' or C for 'Completed: ");
                        String updatedStatus = sc.nextLine();

                        if (updatedStatus.equalsIgnoreCase("P")) { todoList.get(changeIndex).setStatus(Task.IN_PROGRESS); }
                        else if (updatedStatus.equalsIgnoreCase("C")) { todoList.get(changeIndex).setStatus(Task.COMPLETED); }
                    } println("The task has been changed in the List.");
                } else if (userAction.equals("4")) {
                    print("Enter the Index of the Status You Want to DELETE: ");
                    int removeIndex = Integer.parseInt(sc.nextLine());

                    if (removeIndex > (todoList.size() - 1)) { println("There is no such index position in the list."); }
                    else {
                        todoList.remove(removeIndex);
                        println("The Task has Been Removed from the List.");
                    }
                } else { break; }
            }
        } catch (NumberFormatException e) { System.out.println("Invalid Input. Please Enter a Valid Number."); }
        finally { sc.close(); }
    }

    public static void println(String s) {System.out.println(s);}
    public static void print(String s) {System.out.print(s);}
}
