package ru.netology.mamager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Task;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {
    TaskManager manager = new TaskManager();
    Task first = new Task(1, "Task1", "mike", Set.of("ann", "jim", "david", "kate"), Set.of("one", "two"), true);
    Task second = new Task(2, "Task2", "ann", Set.of("john", "jim"), Set.of("one", "two", "three"), false);
    Task third = new Task(3, "Task3", "jim", Set.of("john"), Set.of("five", "one", "two"), true);
    Task forth = new Task(4, "Task4", "mike", Set.of("jim", "ann"), Set.of("one"), true);
    Task fifth = new Task(5, "Task5", "ann", Set.of("mike", "ann", "jim"), Set.of("one", "two", "four", "five"), false);

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

        @Test
        public void shouldGetLabel() {
            Collection<Task> actual = manager.filterByLabel("one");
            Collection<Task> expected = List.of(first, second, third, forth, fifth);

            assertEquals(expected, actual);

            System.out.println(actual);

        }

        @Test
        public void shouldGetSomeLabel() {
            Collection<Task> actual = manager.filterByLabel("one", "two");
            Collection<Task> expected = List.of(first, second, third, fifth);

            assertEquals(expected, actual);
            System.out.println(actual);

        }
        @Test
        public void shouldGetNoneLabel() {
            Collection<Task> actual = manager.filterByLabel("one", "three", "five");
            Collection<Task> expected = List.of();

            assertEquals(expected, actual);

            System.out.println(actual);

        }

        @Test
        public void shouldGetOneItemWithLabels() {
            Collection<Task> actual = manager.filterByLabel("five", "one", "two", "four");
            Collection<Task> expected = List.of(fifth);

            assertEquals(expected, actual);

            System.out.println(actual);

        }

    }
}