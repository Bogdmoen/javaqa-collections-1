package ru.netology.mamager;

import ru.netology.domain.Task;
import ru.netology.repository.TaskRepository;

import java.util.*;


public class TaskManager {

    TaskRepository repository = new TaskRepository();


    public Collection<Task> getAll() {
        return repository.getAll();
    }

    public void addTask(Task task) {
        repository.save(task);
    }


    public void removeTask (int id) {
        repository.removeById(id);
    }

    public void addTasks(Collection<Task> tasks) {
        repository.saveAll(tasks);
    }

    public Collection<Task> getOpenTasks () {
        Collection<Task> result = new ArrayList<>();
        for (Task item : repository.getAll()) {
            if (item.isOpen()) {
              result.add(item);
          }
      }
        return result;
    }

    public Collection<Task> getClosedTasks() {
        Collection<Task> result = new ArrayList<>();
        for (Task item : repository.getAll()) {
            if (!item.isOpen()) {
                result.add(item);
            }
        }
        return result;
    }

    public Collection<Task> filterByAssignee(String... queries) {

        int requestCount = queries.length;
        Set<String> querySet = new HashSet<>(Arrays.asList(queries));
        Set<Task> result = new HashSet<>();
        boolean isBreak = false;
        int i = 0;
        int length = 0;

        for (Task item : getAll()) {

            for (String query : querySet) {
                Set<Task> tmp = new HashSet<>();
                if(item.getAssignee().size() == queries.length) {
                for (String n : item.getAssignee()) {
                    boolean isEqual = n.equalsIgnoreCase(query);
                    if (isEqual) {
                        i++;
                        tmp.add(item);
                    }
                    if (i == requestCount) {
                        result.addAll(tmp);
                        i = 0;
                    }
                }
                }
                else if(item.getAssignee().size() < queries.length) {
                    isBreak = true;
                }
                else {
                    for (String n : item.getAssignee()) {
                        boolean isEqual = n.equalsIgnoreCase(query);
                        if (isEqual) {
                            result.add(item);
                        }
                    }
                }
                if (isBreak)
                    break;
            }
        }
        return result;
    }
}
