package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Task;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskRepositoryTest {
    TaskRepository repository = new TaskRepository();
    Task first = new Task(1, "Task1", 54, 41, "label1", true);
    Task second = new Task(2, "Task2", 55, 44, "label1", false);
    Task third = new Task(3, "Task3", 51, 41, "label5", true);
    Task forth = new Task(4, "Task4", 52, 44, "label5", true);
    Task fifth = new Task(5, "Task5", 59, 42, "label1", false);


    @Test
    public void shouldSaveAll() {

        repository.saveAll(List.of(first, second, third, forth, fifth));

        Collection<Task> actual = repository.getAll();
        Collection<Task> expected = List.of(first, second, third, forth, fifth);

        assertEquals(expected, actual);
    }

}