package com.neha.ToDoList.service;

import com.neha.ToDoList.exception.InvalidTaskException;
import com.neha.ToDoList.exception.TaskNotFoundException;
import com.neha.ToDoList.model.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface ToDoService{
    public List<Task> getTaskLists();

    public List<Task> addBatchTask(List<Task> new_tasks);

    public Task getTask(int id) throws InvalidTaskException, TaskNotFoundException;

    public Task deleteTask(int id) throws InvalidTaskException, TaskNotFoundException;

    public Task updateTask(int id, Task updatedTask) throws InvalidTaskException, TaskNotFoundException;

    public List<Task> updateBatchTask(List<Task> updatedTask) throws InvalidTaskException, TaskNotFoundException;

    public List<Task> search(String name);

    public List<Task> sort(String field, int desc);

    public List<Task> filter(String name, LocalDate deadLine, Boolean isDone);
}
