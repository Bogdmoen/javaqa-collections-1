package ru.netology.mamager;

import ru.netology.domain.Task;
import ru.netology.repository.TaskRepository;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class TaskManager {
    TaskRepository repository = new TaskRepository();

    public Collection<Task> getAll() {
        return repository.getAll();
    }

    public void addTask(Task task) {
        repository.save(task);
    }


    public void removeTask(int id) {
        repository.removeById(id);
    }

    public void changeStatus(int id) {

        for (Task item : getAll()) {
            if (item.getId() == id && item.isOpen())
                item.setOpen(false);
            else if (!item.isOpen()) {
                item.setOpen(true);
            }
        }
    }

    public void addTasks(Collection<Task> tasks) {
        repository.saveAll(tasks);
    }

    public Collection<Task> getOpenTasks() {
        List<Task> result = new ArrayList<>();
        for (Task item : getAll()) {
            if (item.isOpen()) {
                result.add(item);
            }
        }
        Collections.sort(result);
        return result;
    }

    public Collection<Task> getClosedTasks() {
        List<Task> result = new ArrayList<>();
        for (Task item : getAll()) {
            if (!item.isOpen()) {
                result.add(item);
            }
        }
        Collections.sort(result);
        return result;
    }

    // метод возвращает сутщности отфильтрованные по метке или или "assignee"
    public Collection<Task> filterBy(String type, String... queries) {
        List<Task> result = new ArrayList<>();
        Set<String> query = new HashSet<>(Arrays.asList(queries));
        Set<String> itemArg = new HashSet<>();
        for (Task item : getAll()) {
            if (type.equalsIgnoreCase("Label")) {
                itemArg = item.getLabel();
            }
            else if (type.equalsIgnoreCase("Assignee")) {
                itemArg = item.getAssignee();
            }
            if (itemArg.equals(query)) {
                result.add(item);
            }
        }
        Collections.sort(result);
        return result;
    }

    public Collection<Task> filterByAuthor(String query) {
        return getAll().stream()
                .filter(x -> x.getAuthor().equalsIgnoreCase(query))
                .sorted().collect(Collectors.toList());
    }

}
