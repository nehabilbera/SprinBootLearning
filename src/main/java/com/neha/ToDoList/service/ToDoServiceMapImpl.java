package com.neha.ToDoList.service;

import com.neha.ToDoList.exception.InvalidTaskException;
import com.neha.ToDoList.exception.TaskNotFoundException;
import com.neha.ToDoList.model.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ToDoServiceMapImpl {
    HashMap<Integer, Task> tasks = new HashMap<>();
    private int count = 0;

    public List<Task> getTaskLists(){
        return tasks.values().stream().toList();
    }

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

}
