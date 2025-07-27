
package com.neha.ToDoList.controller;


import com.neha.ToDoList.model.ApiResponse;
import com.neha.ToDoList.model.Task;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
public class ToDoListController {

    List<Task> tasks = new ArrayList<>();

    @GetMapping("/tasks")
    public ApiResponse<List<Task>> getTaskLists(){
//        ApiResponse<List<Task>> getResponse = new ApiResponse<>(tasks, "Task retrived successfully.");

//        ApiResponse<List<Task>> getResponse = new ApiResponse<>(tasks, "Task retrived successfully.", new Date(), "Success");
//        return getResponse;
        return ApiResponse.success(tasks,"Task retrived successfully.");
    }

    @PostMapping("/tasks")
    public ApiResponse<Task> addTask(@RequestBody Task task){
        int id = tasks.size()+1;
        Task newTask = new Task(task.name()+id, id, new Date(), task.isDone());
        tasks.add(newTask);
//        ApiResponse<Task> addResponse = new ApiResponse<>(newTask, "Task Successfully Added!");

//        ApiResponse<Task> addResponse = new ApiResponse<>(newTask, "Task Successfully Added!", new Date(), "Success");
//        return addResponse;
            return ApiResponse.success(newTask,"Task Successfully Added!");
    }

    @GetMapping("/tasks/{id}")
    public ApiResponse<Task> getTask(@PathVariable int id){
        if(id<=0 || id>tasks.size()){
//            return new ApiResponse<>(null, "Task with ID " + id + " not found");

//            return new ApiResponse<>(null, "Task with ID " + id + " not found", new Date(), "Error");
            return ApiResponse.error("Task with ID " + id + " not found");
        }
        String getTaskByIdMsg = "Task by Id "+id;
//        ApiResponse<Task> getResponseById = new ApiResponse<>(tasks.get(id-1), getTaskByIdMsg);

//        ApiResponse<Task> getResponseById = new ApiResponse<>(tasks.get(id-1), getTaskByIdMsg, new Date(), "Success");
//        return getResponseById;
        return ApiResponse.success(tasks.get(id-1), getTaskByIdMsg);
    }

    @DeleteMapping("tasks/{id}")
    public ApiResponse<Task> deleteTask(@PathVariable int id){
        if(id<=0 || id>tasks.size()){
//            return new ApiResponse<>(null, "Task with ID " + id + " not found");

//            return new ApiResponse<>(null, "Task with ID " + id + " not found", new Date(), "Error");
            return ApiResponse.error("Task with ID " + id + " not found");
        }
        Task deletedTask = tasks.get(id-1);
        tasks.remove(id-1);
        String getTaskByIdMsg = "Delete Task having Id "+id;
//        ApiResponse<Task> deleteResponseById = new ApiResponse<>(deletedTask, getTaskByIdMsg);

//        ApiResponse<Task> deleteResponseById = new ApiResponse<>(deletedTask, getTaskByIdMsg, new Date(), "Success");
//        return deleteResponseById;
        return ApiResponse.success(deletedTask, getTaskByIdMsg);
    }

    @PutMapping("tasks/{id}")
    public ApiResponse<Task> updateTask(@PathVariable int id, @RequestBody Task updatedTask){
        if(id<=0 || id>tasks.size()){
            return ApiResponse.error("Task with ID "+id+" is not found");
        }
        Task newTask = new Task(updatedTask.name(),id,new Date(),updatedTask.isDone());
        tasks.set(id-1, newTask);
        return ApiResponse.success(newTask, "Task updated Successfully");
    }
}