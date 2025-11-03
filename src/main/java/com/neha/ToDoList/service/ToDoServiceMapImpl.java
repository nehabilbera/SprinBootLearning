
package com.neha.ToDoList.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.neha.ToDoList.exception.InvalidTaskException;
import com.neha.ToDoList.exception.TaskNotFoundException;
import com.neha.ToDoList.model.Task;

import static com.neha.ToDoList.utils.DateMethods.compareDate;


@Service
public class ToDoServiceMapImpl implements ToDoService {
    HashMap<Integer, Task> tasks = new HashMap<>();
    private int count = 0;

    @Override
    public List<Task> getTaskLists(){
        return tasks.values().stream().toList();
    }

    @Override
    public List<Task> addBatchTask(List<Task> new_tasks){
        for(Task t : new_tasks){
            int id = ++count;
            Task newTask = new Task(t.getName(), id, t.getDeadline(), t.getIsDone());
            tasks.put(id,newTask);
        }

        return tasks.values()
                .stream()
                .skip(Math.max(0, tasks.size() - new_tasks.size()))
                .collect(Collectors.toList());
    }

    @Override
    public Task getTask(int id) throws TaskNotFoundException, InvalidTaskException {
        if(id<=0){
            throw new InvalidTaskException("Task Id should be positive");
        }
        Task task = tasks.get(id);
        if(task == null){
            throw new TaskNotFoundException("Task Not Found");
        }
        return task;
    }

    @Override
    public Task deleteTask(int id) throws TaskNotFoundException, InvalidTaskException {
        if(id<=0) {
            throw new InvalidTaskException("Task Id should be positive");
        }
        Task deletedTask = tasks.remove(id);
        if(deletedTask == null){
            throw new TaskNotFoundException("Task Not Found");
        }
        return deletedTask;
    }

    @Override
    public Task updateTask(int id, Task updatedTask) throws TaskNotFoundException, InvalidTaskException {
        if(id<=0){
            throw new InvalidTaskException("Task Id should be positive");
        }
        Task newTask = tasks.get(id);
        if(newTask == null){
            throw new TaskNotFoundException("Task Not Found");
        }

        if(updatedTask.getName() != null) newTask.setName(updatedTask.getName());
        if(updatedTask.getDeadline() != null) newTask.setDeadline(updatedTask.getDeadline());
        if(updatedTask.getIsDone() != null) newTask.setIsDone(updatedTask.getIsDone());
        tasks.put(id, newTask);
        return newTask;
    }

    @Override
    public List<Task> updateBatchTask(List<Task> updatedTasks) throws InvalidTaskException, TaskNotFoundException {

        for (Task t : updatedTasks) {
            int id = t.getId();
            if (id <= 0) {
                throw new InvalidTaskException("Task Id should be positive");
            }
            Task newTask = tasks.get(id);
            if (newTask == null) {
                throw new TaskNotFoundException("Task not found");
            }
            if (t.getName() != null) newTask.setName(t.getName());
            if (t.getDeadline() != null) newTask.setDeadline(t.getDeadline());
            if (t.getIsDone() != null) newTask.setIsDone(t.getIsDone());
            tasks.put(id, newTask);
        }
        return updatedTasks;
    }

    @Override
    public List<Task> search(String name){
        List<Task> searchedTasks = new ArrayList<>();
        tasks.values().stream().filter(t-> (t.getName()!=null) && t.getName().toLowerCase().trim().contains(name.toLowerCase().trim()));
        return searchedTasks;
    }

    @Override
    public List<Task> sort(String field, int desc){
        List<Task> sortedTasks = new ArrayList<>(tasks.values());
        if(field.equals("deadLine")){
            if(desc==0) {
                sortedTasks.sort(Comparator.comparing(Task::getDeadline));
                return sortedTasks;
            }
            else {
                sortedTasks.sort(Comparator.comparing(Task::getDeadline).reversed());
                return sortedTasks;
            }
        }
        else{
            if(desc==0){
                sortedTasks.sort(Comparator.comparing(Task::getName));
                return sortedTasks;
            }
            else {
                sortedTasks.sort(Comparator.comparing(Task::getName).reversed());
                return sortedTasks;
            }
        }
    }

    @Override
    public List<Task> filter(String name, LocalDate deadLine, Boolean isDone){
        List<Task> filteredTasks = new ArrayList<>();
        for(Task t : tasks.values()){
            Boolean namePart = (name==null) || (t.getName().trim().toLowerCase().contains(name.toLowerCase().trim()));
            Boolean deadLinePart = (deadLine==null) || compareDate(t.getDeadline(), deadLine);
            Boolean isDonePart = (isDone==null) || (t.getIsDone().equals(isDone));

            if(namePart && deadLinePart && isDonePart){
                filteredTasks.add(t);
            }
        }

        return filteredTasks;
    }
}
