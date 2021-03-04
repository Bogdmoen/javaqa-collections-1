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
        Set<String> querySet = new HashSet<>(Arrays.asList(queries));
        int requestCount = querySet.size();
        Set<Task> result = new HashSet<>();
        int i = 0;

        for (Task item : getAll()) {
            if (item.getAssignee().size() < querySet.size()) {   // если размер массива запроса превышает размер массива поля обьекта - сравнения не происходит
                continue;
            }
            else if (item.getAssignee().size() > querySet.size()) {
                List<String> q = new ArrayList<String>(querySet);
                List<String> a = new ArrayList<String>(item.getAssignee());
                Collections.sort(q);
                Collections.sort(a);
                int t = 0;
                for(int j = 0; j < q.size(); j++){
                    for(int l = 0; l < q.size(); l++){
                        if(q.get(j).equals(a.get(l))){
                            t++;
                        }
                        }
                    }
                if(t == requestCount) {
                    result.add(item);
                }
            }
            else if (item.getAssignee().size() == querySet.size()) {
                List<String> q = new ArrayList<String>(querySet);
                List<String> a = new ArrayList<String>(item.getAssignee());
                Collections.sort(q);
                Collections.sort(a);

                if(q.equals(a)) {
                    result.add(item);
                }

                // если размер массива запроса равен размеру массива поля обьекта - сравнения не происходит
//            for (String query : querySet) {
//                Set<Task> tmp = new HashSet<>();
//                    for (String n : item.getAssignee()) {
//                        boolean isEqual = n.equalsIgnoreCase(query);
//                        if (isEqual) {
//                            i++;
//                            tmp.add(item);
//                        }
//                        if (i == requestCount) {
//                            result.addAll(tmp);
//                            i = 0;
//                        }
//                    }
//                }
            }
        }
        return result;
    }
}
