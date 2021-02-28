package ru.netology.mamager;

import ru.netology.domain.Task;
import ru.netology.repository.TaskRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

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

}
