package employee_management_java_friday_assignement.Repository;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class TaskRepository<T> {
    private List<T> taskList = new ArrayList<>();

    public void add(T task) {
        taskList.add(task);
    }

    public void remove(T task) {
        taskList.remove(task);
    }

    public Optional<T> find(Predicate<T> predicate) {
        return taskList.stream().filter(predicate).findFirst();
    }

    public List<T> getAll() {
        return new ArrayList<>(taskList); // Defensive copy
    }
}
