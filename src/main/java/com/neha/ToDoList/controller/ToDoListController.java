package com.neha.ToDoList.controller;

import com.neha.ToDoList.model.ApiResponse;
import com.neha.ToDoList.model.Task;
import com.neha.ToDoList.service.ApiService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoListController {

//    List<Task> tasks = new ArrayList<>(); ---- when we didn't use services

    private final ApiService apiService;

    public ToDoListController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/tasks")
    public ApiResponse<List<Task>> getTaskLists() {
        /*
        method-1 ---- when apiResponse is a class ---- when apiResponse is a class
            ApiResponse<List<Task>> getResponse = new ApiResponse<>(tasks, "Task retrived successfully.");
        */

        /*
        method-2 ---- when apiResponse is a record
            ApiResponse<List<Task>> getResponse = new ApiResponse<>(tasks, "Task retrived successfully.", new Date(), "Success");
            return getResponse;
         */

        /*
        method-3 ---- when apiResponse is a record with static method success and error
            return ApiResponse.success(tasks,"Task retrieved successfully.");
        */

        //method-4 ---- with the use of ApiService
        return apiService.getTaskLists();
    }

    @PostMapping("/tasks")
    public ApiResponse<Task> addTask(@RequestBody Task task) {
        /*
        method-1 ---- when apiResponse is a class
            int id = tasks.size()+1;
            Task newTask = new Task(task.name()+id, id, new Date(), task.isDone());
            tasks.add(newTask);
            ApiResponse<Task> addResponse = new ApiResponse<>(newTask, "Task Successfully Added!");
        */

        /*
        method-2 ---- when apiResponse is a record
            ApiResponse<Task> addResponse = new ApiResponse<>(newTask, "Task Successfully Added!", new Date(), "Success");
            return addResponse;
        */

        /*
        method-3 ---- when apiResponse is a record with static method success and error
            return ApiResponse.success(newTask,"Task Successfully Added!");
        */

        //method-4 ---- with the use of ApiService
        return apiService.addTask(task);
    }

    @GetMapping("/tasks/{id}")
    public ApiResponse<Task> getTask(@PathVariable int id) {
        /*
        method-1 ---- when apiResponse is a class
            if(id<=0 || id>tasks.size()){
                method-1 ---- when apiResponse is a class
                return new ApiResponse<>(null, "Task with ID " + id + " not found");

                method-2 ---- when apiResponse is a record
                return new ApiResponse<>(null, "Task with ID " + id + " not found", new Date(), "Error");

                method-3 ---- when apiResponse is a record with static method success and error
                return ApiResponse.error("Task with ID " + id + " not found");
            }

            String getTaskByIdMsg = "Task by Id "+id;
            ApiResponse<Task> getResponseById = new ApiResponse<>(tasks.get(id-1), getTaskByIdMsg);
            return getResponseById;
        */

        /*
        method-2 ---- when apiResponse is a record
            ApiResponse<Task> getResponseById = new ApiResponse<>(tasks.get(id-1), getTaskByIdMsg, new Date(), "Success");
            return getResponseById;
         */

        /*
        method-3 ---- when apiResponse is a record with static method success and error
            return ApiResponse.success(tasks.get(id-1), getTaskByIdMsg);
        */

        //method-4 ---- with the use of ApiService
        return apiService.getTask(id);
    }

    @DeleteMapping("tasks/{id}")
    public ApiResponse<Task> deleteTask(@PathVariable int id) {
        /*
        method-1 ---- when apiResponse is a class
            if(id<=0 || id>tasks.size()){
                method-1 ---- when apiResponse is a class
                return new ApiResponse<>(null, "Task with ID " + id + " not found");

                method-2 ---- when apiResponse is a record
                return new ApiResponse<>(null, "Task with ID " + id + " not found", new Date(), "Error");

                method-3 ---- when apiResponse is a record with static method success and error
                return ApiResponse.error("Task with ID " + id + " not found");
            }

            Task deletedTask = tasks.get(id-1);
            tasks.remove(id-1);
            String getTaskByIdMsg = "Delete Task having Id "+id;
            ApiResponse<Task> deleteResponseById = new ApiResponse<>(deletedTask, getTaskByIdMsg);
            return deleteResponseById;
        */

        /*
        method-2 ---- when apiResponse is a record
            ApiResponse<Task> deleteResponseById = new ApiResponse<>(deletedTask, getTaskByIdMsg, new Date(), "Success");
            return deleteResponseById;
         */

        /*
        method-3 ---- when apiResponse is a record with static method success and error
            return ApiResponse.success(deletedTask, getTaskByIdMsg);
        */

        //method-4 ---- with the use of ApiService
        return apiService.deleteTask(id);
    }

    @PutMapping("tasks/{id}")
    public ApiResponse<Task> updateTask(@PathVariable int id, @RequestBody Task updatedTask) {
        /*
        method-3 ---- when apiResponse is a record with static method success and error
            if(id<=0 || id>tasks.size()){
                return ApiResponse.error("Task with ID "+id+" is not found");
            }
            Task newTask = new Task(updatedTask.name(),id,new Date(),updatedTask.isDone());
            tasks.set(id-1, newTask);
            return ApiResponse.success(newTask, "Task updated Successfully");
        */

        //method-4 ---- with the use of ApiService
        return apiService.updateTask(id, updatedTask);
    }
    
}