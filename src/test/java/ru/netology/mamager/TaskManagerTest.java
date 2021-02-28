package ru.netology.mamager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Task;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {
    TaskManager manager = new TaskManager();
    Task first = new Task(1, "Task1", 54, 41, "label1", true);
    Task second = new Task(2, "Task2", 55, 44, "label1", false);
    Task third = new Task(3, "Task3", 51, 41, "label5", true);
    Task forth = new Task(4, "Task4", 52, 44, "label5", true);
    Task fifth = new Task(5, "Task5", 59, 42, "label1", false);

    @Nested
    public class addRemove {

        @Test
        public void shouldAddItem() {

            manager.addTask(first);

            Collection<Task> actual = manager.getAll();
            Collection<Task> expected = List.of(first);

            assertEquals(expected, actual);
        }



    }

    @Nested
    public class FilterOpen {
        @BeforeEach
        public void setUp() {
            manager.addTasks(List.of(first, second, third, forth, fifth));
        }

        @Test
        public void shouldGetOpenTasks() {

            Collection<Task> actual = manager.getOpenTasks();
            Collection<Task> expected = List.of(first, third, forth);

            assertEquals(expected, actual);
        }
        @Test
        public void shouldGetClosedTasks() {

            Collection<Task> actual = manager.getClosedTasks();
            Collection<Task> expected = List.of(second, fifth);

            assertEquals(expected, actual);
        }

    }

}