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
    Task sixth = new Task(25, "Task25", "john", Set.of("mike", "jim"), Set.of("one", "two", "double", "five"), false);
    Task seventh = new Task(9, "Task9", "john", Set.of("peter"), Set.of("one", "two", "seven", "five"), false);

    @Nested
    public class addRemove {

        @Test
        public void shouldAddItem() {

            manager.addTask(first);

            Collection<Task> actual = manager.getAll();
            Collection<Task> expected = List.of(first);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldRemoveItem() {

            manager.addTask(first);
            manager.addTask(second);
            manager.addTask(third);
            manager.addTask(forth);
            manager.addTask(fifth);
            manager.removeTask(5);

            Collection<Task> actual = manager.getAll();
            Collection<Task> expected = List.of(first, second, third, forth);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldNotRemoveItem() {

            manager.addTask(first);
            manager.addTask(second);
            manager.addTask(third);
            manager.addTask(forth);
            manager.addTask(fifth);
            manager.removeTask(10);

            Collection<Task> actual = manager.getAll();
            Collection<Task> expected = List.of(first, second, third, forth, fifth);

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

    @Nested
    public class FilterLabel {

        @BeforeEach
        public void setUp() {
            manager.addTasks(List.of(first, second, third, forth, fifth));
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

        @Test
        public void shouldGetNoeIssueWithLongQuery() {
            Collection<Task> actual = manager.filterByLabel("five", "one", "two", "four", "six");
            Collection<Task> expected = List.of();

            assertEquals(expected, actual);
            System.out.println(actual);
        }
    }

    @Nested
    public class FilterAuthor {

        @BeforeEach
        public void setUp() {
            manager.addTasks(List.of(first, second, third, forth, fifth, sixth, seventh));
        }

        @Test
        public void shouldReturnIssue() {

            Collection<Task> actual = manager.filterByAuthor("Jim");
            Collection<Task> expected = List.of(third);

            assertEquals(expected, actual);

        }

        @Test
        public void shouldReturnSeveralIssues() {
            Collection<Task> actual = manager.filterByAuthor("john");
            Collection<Task> expected = List.of(seventh, sixth);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldNotReturnIssue() {
            Collection<Task> actual = manager.filterByAuthor("asdf");
            Collection<Task> expected = List.of();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FilterAssignee {

        @BeforeEach
        public void setUp() {
            manager.addTasks(List.of(first, second, third, forth, fifth, sixth, seventh));
        }

        @Test
        public void shouldReturnOne() {

            Collection<Task> actual = manager.filterByAssignee("peter");
            Collection<Task> expected = List.of(seventh);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldReturnNone() {

            Collection<Task> actual = manager.filterByAssignee("asdf");
            Collection<Task> expected = List.of();

            assertEquals(expected, actual);
        }

        @Test
        public void shouldReturnSeveral() {

            Collection<Task> actual = manager.filterByAssignee("ann");
            Collection<Task> expected = List.of(first, forth, fifth);

            assertEquals(expected, actual);
        }



    }

    @Nested
    public class TaskStatus {
        @BeforeEach
        public void setUp() {
            manager.addTasks(List.of(first, second, third, forth, fifth, sixth, seventh));
        }

        @Test
        public void shouldCloseIssue() {

            manager.ChangeStatus(1);

            Boolean actual = first.isOpen();

            assertFalse(actual);
        }

        @Test
        public void shouldOpenIssue() {

            manager.ChangeStatus(2);

            Boolean actual = second.isOpen();

            assertTrue(actual);
        }

        @Test
        public void shouldNotChangeIssue() {
            manager.ChangeStatus(50);
        }

    }
}

