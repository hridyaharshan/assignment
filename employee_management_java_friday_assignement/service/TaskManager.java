package employee_management_java_friday_assignement.service;

import employee_management_java_friday_assignement.exception.TaskNotFoundException;
import employee_management_java_friday_assignement.model.Employee;
import employee_management_java_friday_assignement.model.Task;


import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TaskManager {
    private final Map<Employee, List<Task>> employeeTasks = new HashMap<>();

    // Assign a task to an employee
    public void assignTask(Employee employee, Task task) {
        employeeTasks.computeIfAbsent(employee, k -> new ArrayList<>()).add(task);
    }

    // Get all tasks
    public List<Task> getAllTasks() {
        return employeeTasks.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    // Get tasks by employee
    public List<Task> getTasksForEmployee(Employee employee) {
        return employeeTasks.getOrDefault(employee, new ArrayList<>());
    }

    // Search tasks by keyword in description
    public List<Task> searchTasks(String keyword) {
        return getAllTasks().stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());
    }

    // Sort tasks by priority or due date
    public List<Task> getSortedTasks(Employee employee, String criteria) {
        Comparator<Task> comparator;
        if ("priority".equalsIgnoreCase(criteria)) {
            comparator = Comparator.comparingInt(Task::getPriority);
        } else if ("dueDate".equalsIgnoreCase(criteria)) {
            comparator = Comparator.comparing(Task::getDueDate);
        } else {
            return getTasksForEmployee(employee); // No sort
        }

        return getTasksForEmployee(employee).stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    // Update task status by task ID
    public void updateTaskStatus(Employee employee, int taskId, String newStatus) throws TaskNotFoundException {
        List<Task> tasks = employeeTasks.get(employee);
        if (tasks != null) {
            for (Task task : tasks) {
                if (task.getId() == taskId) {
                    task.setStatus(newStatus);
                    return;
                }
            }
        }
        throw new TaskNotFoundException("Task with ID " + taskId + " not found for this employee.");
    }

    // Get employees with more than 3 pending tasks
    public List<Employee> getEmployeesWithHighPendingTasks() {
        return employeeTasks.entrySet().stream()
                .filter(entry -> entry.getValue().stream()
                        .filter(task -> "Pending".equalsIgnoreCase(task.getStatus()))
                        .count() > 3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    // Get tasks due tomorrow
    public List<Task> getTasksDueTomorrow() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        return getAllTasks().stream()
                .filter(task -> task.getDueDate().equals(tomorrow))
                .collect(Collectors.toList());
    }

    // Get overdue tasks
    public List<Task> getOverdueTasks() {
        LocalDate today = LocalDate.now();
        return getAllTasks().stream()
                .filter(task -> task.getDueDate().isBefore(today) && !"Completed".equalsIgnoreCase(task.getStatus()))
                .collect(Collectors.toList());
    }
}

