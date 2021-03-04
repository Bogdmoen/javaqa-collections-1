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

    public void ChangeStatus(int id) {
        getAll().stream().anyMatch((i) -> i.getId() == id);

        for (Task item : getAll()) {
            if (item.getId() == id && item.isOpen())
                item.setOpen(false);
            else if(item.getId() == id && !item.isOpen()) {
                item.setOpen(true);
            }
        }
    }

    public void addTasks(Collection<Task> tasks) {
        repository.saveAll(tasks);
    }

    public Collection<Task> getOpenTasks() {
        Collection<Task> result = new ArrayList<>();
        for (Task item : getAll()) {
            if (item.isOpen()) {
                result.add(item);
            }
        }
        return result;
    }

    public Collection<Task> getClosedTasks() {
        Collection<Task> result = new ArrayList<>();
        for (Task item : getAll()) {
            if (!item.isOpen()) {
                result.add(item);
            }
        }
        return result;
    }

    public Collection<Task> filterByLabel(String... queries) {
        Set<String> querySet = new HashSet<>(Arrays.asList(queries));
        int requestCount = querySet.size();
        Set<Task> tmp = new HashSet<>();
        List<Task> result;

        for (Task item : getAll()) {
            if (item.getLabel().size() >= querySet.size()) {   // если размер массива запроса превышает размер массива поля обьекта - сравнения не происходит
                List<String> q = new ArrayList<>(querySet);
                List<String> a = new ArrayList<>(item.getLabel());
                String e = "";
                while (q.size() < a.size()) {
                    q.add(e);
                }
                Collections.sort(q);
                Collections.sort(a);
                int i = 0;
                for (int j = 0; j < q.size(); j++) {
                    for (int l = 0; l < q.size(); l++) {
                        if (q.get(j).equals(a.get(l))) {
                            i++;
                        }
                    }
                }
                if (i == requestCount) {
                    tmp.add(item);
                }
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

    public Collection<Task> filterByAssignee(String query) {

        Set<Task> tmp = new HashSet<>();
        List<Task> result;
        for (Task item : getAll()) {
            if (item.getAssignee().stream().anyMatch(a -> a.equalsIgnoreCase(query))) {
                tmp.add(item);
            }
        }
        result = new ArrayList<>(tmp);
        Collections.sort(result);
        return result;
    }
}
