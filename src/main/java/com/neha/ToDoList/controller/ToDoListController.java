package com.neha.ToDoList.controller;

import com.neha.ToDoList.exception.InvalidTaskException;
import com.neha.ToDoList.exception.TaskNotFoundException;
import com.neha.ToDoList.model.ApiResponse;
import com.neha.ToDoList.model.Task;
import com.neha.ToDoList.service.ToDoServiceListImpl;
import com.neha.ToDoList.service.ToDoServiceMapImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoListController {

//    private ToDoServiceListImpl toDoService = new ToDoServiceListImpl();
    private ToDoServiceMapImpl toDoService = new ToDoServiceMapImpl();

    @GetMapping("/tasks")
    public ApiResponse<List<Task>> getTaskLists() {
        return ApiResponse.success(toDoService.getTaskLists(), "Retrieved tasks successfully");
    }

    @PostMapping("/tasks")
    public ApiResponse<List<Task>> addBatchTask(@RequestBody List<Task> task) {
        return ApiResponse.success(toDoService.addBatchTask(task), "Added tasks successfully");
    }

    @GetMapping("/tasks/{id}")
    public ApiResponse<Task> getTask(@PathVariable int id) {
        try{
            return ApiResponse.success(toDoService.getTask(id), "Task with Id-"+id+" retrieve successfully");
        }
        catch (InvalidTaskException | TaskNotFoundException e){
            return ApiResponse.error(e.getMessage());
        }
    }

    @DeleteMapping("tasks/{id}")
    public ApiResponse<Task> deleteTask(@PathVariable int id) {
        try{
            return ApiResponse.success(toDoService.deleteTask(id), "Task with Id-"+id+" delete successfully");
        }
        catch (InvalidTaskException | TaskNotFoundException e){
            return ApiResponse.error(e.getMessage());
        }
    }

    @PutMapping("tasks/{id}")
    public ApiResponse<Task> updateTask(@PathVariable int id, @RequestBody Task updatedTask) {
        try{
            return ApiResponse.success(toDoService.updateTask(id, updatedTask), "Task with Id-"+id+" update successfully");
        }
        catch (InvalidTaskException | TaskNotFoundException e){
            return ApiResponse.error(e.getMessage());
        }
    }

    @PutMapping("tasks")
    public ApiResponse<List<Task>> updateBatchTask(@RequestBody List<Task> updatedTask) {
        try{
            return ApiResponse.success(toDoService.updateBatchTask(updatedTask), "Tasks updated successfully");
        }
        catch (InvalidTaskException | TaskNotFoundException e){
            return ApiResponse.error(e.getMessage());
        }
    }


}