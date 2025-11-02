
package com.neha.ToDoList.controller;

import java.time.LocalDate;
import java.util.List;

import com.neha.ToDoList.service.ToDoServiceListImpl;
import org.springframework.web.bind.annotation.*;

import com.neha.ToDoList.exception.InvalidTaskException;
import com.neha.ToDoList.exception.TaskNotFoundException;
import com.neha.ToDoList.model.ApiResponse;
import com.neha.ToDoList.model.Task;

@RestController
public class ToDoListController {

    private final ToDoServiceListImpl toDoService = new ToDoServiceListImpl();
//    private ToDoServiceMapImpl toDoService = new ToDoServiceMapImpl();

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

    @GetMapping("tasks/search")
    public ApiResponse<List<Task>> search(@RequestParam String name){
        return ApiResponse.success(toDoService.search(name), "Retrieved searched tasks successfully");
    }

    @GetMapping("tasks/sortByName")
    public List<Task> sortByName(){
        return toDoService.sortByName();
    }

    @GetMapping("tasks/sortByDeadLine")
    public List<Task> sortByDeadLine(){
        return toDoService.sortByDeadLine();
    }

    @GetMapping("tasks/sortByIsDone")
    public List<Task> sortByIsDone(){
        return toDoService.sortByIsDone();
    }

    @GetMapping("tasks/filter")
    public Object filter(@RequestParam(required = false) String name,
                         @RequestParam(required = false) LocalDate deadLine,
                         @RequestParam(required = false) Boolean isDone){
        return ApiResponse.success(toDoService.filter(name, deadLine, isDone), "Get filtered tasks successfully");
    }
}
