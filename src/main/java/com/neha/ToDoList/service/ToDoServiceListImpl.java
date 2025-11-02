package com.neha.ToDoList.service;

import com.neha.ToDoList.exception.InvalidTaskException;
import com.neha.ToDoList.exception.TaskNotFoundException;
import com.neha.ToDoList.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ToDoServiceListImpl {
    private List<Task> tasks = new ArrayList<>();
    private int count = 0;

    public List<Task> getTaskLists(){
        return tasks;
    }

    public List<Task> addBatchTask(List<Task> new_tasks){

        for(Task t : new_tasks){
            int id = ++count;
            Task newTask = new Task(t.getName(), id, t.getDeadline(), t.getIsDone());
            tasks.add(newTask);
        }

        return tasks.subList(tasks.size()-new_tasks.size(), tasks.size());
    }

    public Task getTask(int id) throws InvalidTaskException, TaskNotFoundException {
        if(id<=0){
            throw new InvalidTaskException("Task Id should be positive");
        }

        Task task = null;
        for(Task t : tasks){
            if(t.getId() == id){
                task = t;
                break;
            }
        }

        if(task == null){
            throw new TaskNotFoundException("Task Not Found");
        }
        return task;
    }

    public Task deleteTask(int id) throws InvalidTaskException, TaskNotFoundException {
        if(id<=0) {
            throw new InvalidTaskException("Task Id should be positive");
        }

        Task deletedTask = null;
        for(Task t : tasks){
            if(t.getId() == id){
                deletedTask = t;
                break;
            }
        }

        if(deletedTask == null){
            throw new TaskNotFoundException("Task Not Found");
        }

        tasks.remove(deletedTask);

        return deletedTask;
    }

    public Task updateTask(int id, Task updatedTask) throws InvalidTaskException, TaskNotFoundException {
        if(id<=0){
            throw new InvalidTaskException("Task Id should be positive");
        }

        Task newTask = null;
        int ind = -1;
        for(int i = 0; i < tasks.size(); i++){
            Task t = tasks.get(i);
            if(t.getId() == id){
                newTask = t;
                ind = i;
                break;
            }
        }

        if(newTask == null){
            throw new TaskNotFoundException("Task Not Found");
        }

        if(updatedTask.getName() != null) newTask.setName(updatedTask.getName());
        if(updatedTask.getDeadline() != null) newTask.setDeadline(updatedTask.getDeadline());
        if(updatedTask.getIsDone() != null) newTask.setIsDone(updatedTask.getIsDone());
        tasks.set(ind, newTask);
        return newTask;
    }

    public List<Task> updateBatchTask(List<Task> updatedTask) throws InvalidTaskException, TaskNotFoundException {

        for(Task t : updatedTask){
            int id = t.getId();
            if (id <= 0) {
                throw new InvalidTaskException("Task Id should be positive");
            }

            Boolean check = false;
            Task newTask = null;
            int ind = -1;
            for(int i = 0; i < tasks.size(); i++){
                Task ta = tasks.get(i);
                if(ta.getId()==id){
                    newTask = ta;
                    ind = i;
                    check = true;
                    break;
                }
            }
            if(check == true){
                if (t.getName() != null) newTask.setName(t.getName());
                if (t.getDeadline() != null) newTask.setDeadline(t.getDeadline());
                if (t.getIsDone() != null) newTask.setIsDone(t.getIsDone());
                tasks.set(ind, newTask);
            }
            else{
                throw new TaskNotFoundException("Task not found with id");
            }
        }

        return updatedTask;
    }
}
