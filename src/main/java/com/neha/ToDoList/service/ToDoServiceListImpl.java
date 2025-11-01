package com.neha.ToDoList.service;

import com.neha.ToDoList.Exception.TaskNotFoundException;
import com.neha.ToDoList.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class ToDoServiceListImpl {
    List<Task> tasks = new ArrayList<>();

    public List<Task> getTaskLists(){
        return tasks;
    }

    int count = 0;
    public Task addTask(Task task){
        int id = ++count;
        Task newTask = new Task(task.getName()+id, id, new Date(), task.getIsDone());
        tasks.add(newTask);
        return newTask;
    }

    public Task getTask(int id) {
        if(id<=0 || id>tasks.size()){
            throw new TaskNotFoundException("Task with ID " + id + " not found");
        }
        Task task = tasks.get(id-1);return task;
    }

    public Task deleteTask(int id) {
        if(id<=0) {
            throw new TaskNotFoundException("Task with ID " + id + " not found");
        }

        Task deletedTask = null;
        for(Task t : tasks){
            if(t.getId() == id){
                deletedTask = t;
                break;
            }
        }

        tasks.remove(deletedTask);

        return deletedTask;
    }

    public Task updateTask(int id, Task updatedTask) {
        if(id<=0 || id>tasks.size()){
            throw new TaskNotFoundException("Task with ID "+id+" is not found");
        }
        Task newTask = tasks.get(id-1);
        if(updatedTask.getName() != null) newTask.setName(updatedTask.getName());
        if(updatedTask.getDeadline() != null) newTask.setDeadline(updatedTask.getDeadline());
        if(updatedTask.getIsDone() != null) newTask.setIsDone(updatedTask.getIsDone());
        tasks.set(id-1, newTask);
        return newTask;
    }

}
