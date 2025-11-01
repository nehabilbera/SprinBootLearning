package com.neha.ToDoList.service;

import com.neha.ToDoList.Exception.TaskNotFoundException;
import com.neha.ToDoList.model.Task;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Service
public class ToDoServiceMapImpl {
    HashMap<Integer, Task> tasks = new HashMap<>();

    public List<Task> getTaskLists(){
        return tasks.values().stream().toList();
    }

    int count = 0;
    public Task addTask(Task newTask){
        newTask.setDeadline(new Date());
        int Id = ++count;
        newTask.setId(Id);
        newTask.setName(newTask.getName()+Id);
        newTask.setIsDone(newTask.getIsDone());
        tasks.put(Id,newTask);
        return newTask;
    }

    public Task getTask(int Id) {
        if(Id<=0 || Id>tasks.size()){
            throw new TaskNotFoundException("Task with Id " + Id + " not found");
        }
        Task task = tasks.get(Id);
        return task;
    }

    public Task deleteTask(int Id) {
        if(Id<=0) {
            throw new TaskNotFoundException("Task with Id " + Id + " not found");
        }

        Task deletedTask = tasks.remove(Id);

        return deletedTask;
    }

    public Task updateTask(int Id, Task updatedTask) {
        Task newTask = tasks.get(Id);

        if(newTask == null){
            throw new TaskNotFoundException("Task-"+Id+" not found");
        }

        if(updatedTask.getName() != null) newTask.setName(updatedTask.getName());
        if(updatedTask.getDeadline() != null) newTask.setDeadline(updatedTask.getDeadline());
        if(updatedTask.getIsDone() != null) newTask.setIsDone(updatedTask.getIsDone());
        tasks.put(Id, newTask);
        return newTask;
    }

}
