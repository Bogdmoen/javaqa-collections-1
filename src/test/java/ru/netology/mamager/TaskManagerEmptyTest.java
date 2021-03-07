package ru.netology.mamager;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Task;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerEmptyTest {
    TaskManager manager = new TaskManager();
    Task first = new Task(1, "Task1", "mike", Set.of("ann", "jim", "david", "kate"), Set.of("one", "two"), true);

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
        public void shouldNotRemoveItem() {
            manager.removeTask(10);

            Collection<Task> actual = manager.getAll();
            Collection<Task> expected = List.of();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FilterOpen {

        @Test
        public void shouldGetOpenTasks() {
            Collection<Task> actual = manager.getOpenTasks();
            Collection<Task> expected = List.of();

            assertEquals(expected, actual);
        }

        @Test
        public void shouldGetClosedTasks() {
            Collection<Task> actual = manager.getClosedTasks();
            Collection<Task> expected = List.of();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FilterLabel {

        @Test
        public void shouldGetNoneLabel() {
            Collection<Task> actual = manager.filterByLabel("one", "three", "five");
            Collection<Task> expected = List.of();

            assertEquals(expected, actual);
            System.out.println(actual);
        }
    }

    @Nested
    public class FilterAuthor {

        @Test
        public void shouldNotReturnIssue() {
            Collection<Task> actual = manager.filterByAuthor("asdf");
            Collection<Task> expected = List.of();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FilterAssignee {

        @Test
        public void shouldReturnNone() {
            Collection<Task> actual = manager.filterByAssignee("asdf");
            Collection<Task> expected = List.of();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class TaskStatus {

        @Test
        public void shouldNotChangeIssue() {
            manager.changeStatus(50);
        }

    }
}
