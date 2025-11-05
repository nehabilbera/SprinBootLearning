package com.neha.ToDoList.service;

import com.neha.ToDoList.exception.InvalidTaskException;
import com.neha.ToDoList.exception.TaskNotFoundException;
import com.neha.ToDoList.model.Task;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


public interface ToDoService{
    public List<Task> getTaskLists() throws SQLException;

    public List<Task> addBatchTask(List<Task> new_tasks) throws SQLException;

    public Task getTask(int id) throws InvalidTaskException, TaskNotFoundException, SQLException;

    public Task deleteTask(int id) throws InvalidTaskException, TaskNotFoundException, SQLException;

    public Task updateTask(int id, Task updatedTask) throws InvalidTaskException, TaskNotFoundException, SQLException;

    public List<Task> updateBatchTask(List<Task> updatedTask) throws InvalidTaskException, TaskNotFoundException, SQLException;

    public List<Task> search(String name) throws SQLException;

    public List<Task> sort(String field, int desc) throws SQLException;

    public List<Task> filter(String name, LocalDate deadLine, Boolean isDone) throws SQLException, TaskNotFoundException;
}
