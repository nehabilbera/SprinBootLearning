
package com.neha.ToDoList.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.neha.ToDoList.service.ToDoService;
import com.neha.ToDoList.service.ToDoServiceDaoImpl;
import com.neha.ToDoList.service.ToDoServiceListImpl;
import com.neha.ToDoList.service.ToDoServiceMapImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import com.neha.ToDoList.exception.InvalidTaskException;
import com.neha.ToDoList.exception.TaskNotFoundException;
import com.neha.ToDoList.model.ApiResponse;
import com.neha.ToDoList.model.Task;


@RestController
@RequestMapping("/tasks")
public class ToDoListController {

    private final ToDoService toDoService;

    public ToDoListController(ToDoService toDoService) throws SQLException{
        this.toDoService=toDoService;
    }

    @GetMapping
    public ApiResponse<List<Task>> getTaskLists() throws SQLException {
        return ApiResponse.success(toDoService.getTaskLists(), "Retrieved tasks successfully");
    }

    @PostMapping
    public ApiResponse<List<Task>> addBatchTask(@RequestBody List<Task> task) throws SQLException {
        return ApiResponse.success(toDoService.addBatchTask(task), "Added tasks successfully");
    }

    @GetMapping("/{id}")
    public ApiResponse<Task> getTask(@PathVariable int id) throws SQLException {
        try{
            return ApiResponse.success(toDoService.getTask(id), "Task with Id-"+id+" retrieve successfully");
        }
        catch (InvalidTaskException | TaskNotFoundException e){
            return ApiResponse.error(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Task> deleteTask(@PathVariable int id) throws SQLException {
        try{
            return ApiResponse.success(toDoService.deleteTask(id), "Task with Id-"+id+" delete successfully");
        }
        catch (InvalidTaskException | TaskNotFoundException e){
            return ApiResponse.error(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ApiResponse<Task> updateTask(@PathVariable int id, @RequestBody Task updatedTask) throws SQLException {
        try{
            return ApiResponse.success(toDoService.updateTask(id, updatedTask), "Task with Id-"+id+" update successfully");
        }
        catch (InvalidTaskException | TaskNotFoundException e){
            return ApiResponse.error(e.getMessage());
        }
    }

    @PutMapping
    public ApiResponse<List<Task>> updateBatchTask(@RequestBody List<Task> updatedTask) {
        try{
            return ApiResponse.success(toDoService.updateBatchTask(updatedTask), "Tasks updated successfully");
        }
        catch (InvalidTaskException | TaskNotFoundException | SQLException e){
            return ApiResponse.error(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ApiResponse<List<Task>> search(@RequestParam String name) throws SQLException {
        return ApiResponse.success(toDoService.search(name), "Retrieved searched tasks successfully");
    }

    // todo: validate fields and request params
    @GetMapping("/sort")
    public ApiResponse<List<Task>> sort(@RequestParam(required = false, defaultValue = "deadLine") String field, @RequestParam(required = false, defaultValue = "0") int desc) throws SQLException {
        // todo: add custom exc. invalid parameters
        return ApiResponse.success(toDoService.sort(field, desc), "Tasks sort Successfully");
    }

    @GetMapping("/filter")
    public ApiResponse<List<Task>> filter(@RequestParam(required = false) String name,
                                          @RequestParam(required = false) LocalDate deadLine,
                                          @RequestParam(required = false) Boolean isDone) throws SQLException, TaskNotFoundException {
        //todo : add param conditions
        return ApiResponse.success(toDoService.filter(name, deadLine, isDone), "Get filtered tasks successfully");
    }
}
