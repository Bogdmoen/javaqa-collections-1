package ru.netology.repository;

import ru.netology.domain.Task;


import java.util.ArrayList;
import java.util.Collection;


public class TaskRepository {

    private Collection<Task> items = new ArrayList<>();


    public void save(Task task) {
        items.add(task);
    }


    public Collection<Task> getAll() {
        return items;
    }

    public void removeById(int id) {
        items.removeIf(task -> task.getId() == id);
    }

    public void saveAll(Collection<Task> tasks) {
        items.addAll(tasks);

    }

    public void removeAll() {
        items.removeAll(items);

    }



}
