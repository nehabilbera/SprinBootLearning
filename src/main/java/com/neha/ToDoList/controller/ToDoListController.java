package com.neha.ToDoList.controller;

import com.neha.ToDoList.model.Task;
import com.neha.ToDoList.service.ToDoServiceListImpl;
import com.neha.ToDoList.service.ToDoServiceMapImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoListController {

//    ToDoServiceListImpl toDoServiceListImpl = new ToDoServiceListImpl();
    ToDoServiceMapImpl toDoServiceMapImpl = new ToDoServiceMapImpl();

    @GetMapping("/tasks")
    public List<Task> getTaskLists() {
//        return toDoServiceListImpl.getTaskLists();
        return toDoServiceMapImpl.getTaskLists();
    }

    @PostMapping("/tasks")
    public Task addTask(@RequestBody Task task) {
//        return toDoServiceListImpl.addTask(task);
        return toDoServiceMapImpl.addTask(task);
    }

    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable int id) {
//        return toDoServiceListImpl.getTask(id);
        return toDoServiceMapImpl.getTask(id);
    }

    @DeleteMapping("tasks/{id}")
    public Task deleteTask(@PathVariable int id) {
//        return toDoServiceListImpl.deleteTask(id);
        return toDoServiceMapImpl.deleteTask(id);
    }

    @PutMapping("tasks/{id}")
    public Task updateTask(@PathVariable int id, @RequestBody Task updatedTask) {
//        return toDoServiceListImpl.updateTask(id, updatedTask);
        return toDoServiceMapImpl.updateTask(id, updatedTask);
    }

}