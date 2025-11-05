package com.neha.ToDoList.service;

import com.neha.ToDoList.dao.ToDoDao;
import com.neha.ToDoList.dao.ToDoDaoMySqlImpl;
import com.neha.ToDoList.exception.InvalidTaskException;
import com.neha.ToDoList.exception.TaskNotFoundException;
import com.neha.ToDoList.model.Task;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


public class ToDoServiceDaoImpl implements ToDoService{

    ToDoDao toDoDao = new ToDoDaoMySqlImpl();

    public ToDoServiceDaoImpl() throws SQLException {
    }

    @Override
    public List<Task> getTaskLists() throws SQLException {
        return toDoDao.getTaskLists();
    }

    @Override
    public List<Task> addBatchTask(List<Task> new_tasks) throws SQLException {
        return toDoDao.addBatchTask(new_tasks);
    }

    @Override
    public Task getTask(int id) throws InvalidTaskException, TaskNotFoundException, SQLException {
        return toDoDao.getTask(id);
    }

    @Override
    public Task deleteTask(int id) throws InvalidTaskException, TaskNotFoundException, SQLException {
        return toDoDao.deleteTask(id);
    }

    @Override
    public Task updateTask(int id, Task updatedTask) throws InvalidTaskException, TaskNotFoundException, SQLException {
        return toDoDao.updateTask(id, updatedTask);
    }

    @Override
    public List<Task> updateBatchTask(List<Task> updatedTask) throws InvalidTaskException, TaskNotFoundException, SQLException {
        return toDoDao.updateBatchTask(updatedTask);
    }

    @Override
    public List<Task> search(String name) throws SQLException {
        return toDoDao.search(name);
    }

    @Override
    public List<Task> sort(String field, int desc) throws SQLException {
        // todo : add field condition
        return toDoDao.sort(field, desc);
    }

    @Override
    public List<Task> filter(String name, LocalDate deadLine, Boolean isDone) throws SQLException, TaskNotFoundException {
        //todo : add param conditions
        return toDoDao.filter(name, deadLine, isDone);
    }
}
