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
        Set<String> querySet = new HashSet<>(Arrays.asList(queries));
        Set<Task> tmp = new HashSet<>();
        List<Task> result;
        for (Task item : getAll()) {
            List<String> q = new ArrayList<>(querySet);
            List<String> a = new ArrayList<>();
            if (type.equalsIgnoreCase("label")) {
                a = new ArrayList<>(item.getLabel());
            } else if (type.equalsIgnoreCase("assignee")) {
                a = new ArrayList<>(item.getAssignee());
            }
            Collections.sort(q);
            Collections.sort(a);
            // сравниваем обьекты запроса и айтема, при совпадении складываем в переменную
            List<String> finalA = a;
            List<String> matching = q.stream()
                    .filter(o -> finalA.contains(o)).sorted().collect(Collectors.toList());
            // сравниваем переменную с запросом, при совпадении, добавляем айтем в лист
            boolean isEqual = q.equals(matching);
            if (isEqual) {
                tmp.add(item);
            }
        }
        result = new ArrayList<>(tmp);
        Collections.sort(result);
        return result;
    }

    public Collection<Task> filterByAuthor(String query) {
        return getAll().stream()
                .filter(x -> x.getAuthor().equalsIgnoreCase(query))
                .sorted().collect(Collectors.toList());
    }

}
