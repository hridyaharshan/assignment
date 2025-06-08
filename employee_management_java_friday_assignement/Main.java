package employee_management_java_friday_assignement;



import employee_management_java_friday_assignement.exception.TaskNotFoundException;
import employee_management_java_friday_assignement.model.Employee;
import employee_management_java_friday_assignement.model.Task;
import employee_management_java_friday_assignement.service.TaskManager;
import employee_management_java_friday_assignement.service.TaskMonitor;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        TaskMonitor monitor = new TaskMonitor(manager);
        monitor.setDaemon(true);
        monitor.start();  // Start the background overdue checker

        Scanner scanner = new Scanner(System.in);

        // Create Employees
        Employee emp1 = new Employee(1, "Alice", "Engineering");
        Employee emp2 = new Employee(2, "Bob", "Marketing");

        // Create Tasks
        Task task1 = new Task(101, "Fix critical bug", "Pending", LocalDate.now().plusDays(1), 1);
        Task task2 = new Task(102, "Prepare marketing report", "In Progress", LocalDate.now().plusDays(2), 3);
        Task task3 = new Task(103, "Code review", "Pending", LocalDate.now().minusDays(1), 2); // overdue

        // Assign Tasks
        manager.assignTask(emp1, task1);
        manager.assignTask(emp2, task2);
        manager.assignTask(emp1, task3);

        // Menu loop
        while (true) {
            System.out.println("\n===== Employee Task Tracker =====");
            System.out.println("1. View all tasks");
            System.out.println("2. Search tasks by keyword");
            System.out.println("3. View tasks due tomorrow");
            System.out.println("4. View overdue tasks");
            System.out.println("5. View tasks sorted by priority");
            System.out.println("6. Update task status");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    manager.getAllTasks().forEach(System.out::println);
                    break;

                case 2:
                    System.out.print("Enter keyword: ");
                    String keyword = scanner.nextLine();
                    manager.searchTasks(keyword).forEach(System.out::println);
                    break;

                case 3:
                    System.out.println("Tasks due tomorrow:");
                    manager.getTasksDueTomorrow().forEach(System.out::println);
                    break;

                case 4:
                    System.out.println("Overdue tasks:");
                    manager.getOverdueTasks().forEach(System.out::println);
                    break;

                case 5:
                    System.out.print("Enter employee ID to sort tasks: ");
                    int empId = Integer.parseInt(scanner.nextLine());
                    Employee selectedEmp = empId == 1 ? emp1 : emp2;
                    System.out.print("Sort by (priority/dueDate): ");
                    String criteria = scanner.nextLine();
                    manager.getSortedTasks(selectedEmp, criteria).forEach(System.out::println);
                    break;

                case 6:
                    System.out.print("Enter employee ID: ");
                    int eId = Integer.parseInt(scanner.nextLine());
                    Employee emp = eId == 1 ? emp1 : emp2;
                    System.out.print("Enter task ID: ");
                    int tId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter new status: ");
                    String status = scanner.nextLine();
                    try {
                        manager.updateTaskStatus(emp, tId, status);
                        System.out.println("Task status updated.");
                    } catch (TaskNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 7:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

