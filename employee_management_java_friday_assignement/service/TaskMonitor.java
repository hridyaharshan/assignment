package employee_management_java_friday_assignement.service;



import employee_management_java_friday_assignement.model.Task;

import java.util.List;

public class TaskMonitor extends Thread {
    private final TaskManager taskManager;

    public TaskMonitor(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public void run() {
        while (true) {
            try {
                List<Task> overdueTasks = taskManager.getOverdueTasks();
                if (!overdueTasks.isEmpty()) {
                    System.out.println("\n⏰ Overdue Tasks:");
                    overdueTasks.forEach(System.out::println);
                }
                Thread.sleep(60000); // Sleep for 1 minute
            } catch (InterruptedException e) {
                System.out.println("TaskMonitor interrupted.");
                break;
            }
        }
    }
}

