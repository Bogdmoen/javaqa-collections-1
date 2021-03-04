package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Task;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TaskRepositoryTest {
    TaskRepository repository = new TaskRepository();
    Task first = new Task(1, "Task1", "mike", Set.of("test"), Set.of("test"), true);
    Task second = new Task(2, "Task2", "ann", Set.of("test"), Set.of("test"), false);
    Task third = new Task(3, "Task3", "jim", Set.of("test"), Set.of("test"), true);
    Task forth = new Task(4, "Task4", "mike", Set.of("test"), Set.of("test"), true);
    Task fifth = new Task(5, "Task5", "ann", Set.of("test"), Set.of("test"), false);


    @Test
    public void shouldSaveAll() {

        repository.saveAll(List.of(first, second, third, forth, fifth));

        Collection<Task> actual = repository.getAll();
        Collection<Task> expected = List.of(first, second, third, forth, fifth);

        assertEquals(expected, actual);
    }

}