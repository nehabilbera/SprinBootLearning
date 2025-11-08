package com.neha.ToDoList.dao;

import com.neha.ToDoList.exception.InvalidParams;
import com.neha.ToDoList.exception.InvalidTaskException;
import com.neha.ToDoList.exception.TaskNotFoundException;
import com.neha.ToDoList.model.Task;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


public interface ToDoDao {
     List<Task> getTaskLists() throws SQLException;

     List<Task> addBatchTask(List<Task> new_tasks) throws SQLException;

     Task getTask(int id) throws InvalidTaskException, TaskNotFoundException, SQLException;

     Task deleteTask(int id) throws InvalidTaskException, TaskNotFoundException, SQLException;

     Task updateTask(int id, Task updatedTask) throws InvalidTaskException, TaskNotFoundException, SQLException;

     List<Task> updateBatchTask(List<Task> updatedTask) throws InvalidTaskException, TaskNotFoundException, SQLException;

     List<Task> search(String name) throws SQLException;

     List<Task> sort(String field, int desc) throws SQLException, InvalidParams;

     List<Task> filter(String name, LocalDate deadline, Boolean isDone) throws SQLException, TaskNotFoundException;
}
