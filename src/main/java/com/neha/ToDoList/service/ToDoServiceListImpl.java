package com.neha.ToDoList.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import com.neha.ToDoList.dao.ToDoDao;
import com.neha.ToDoList.exception.InvalidParams;
import com.neha.ToDoList.exception.InvalidTaskException;
import com.neha.ToDoList.exception.TaskNotFoundException;
import com.neha.ToDoList.model.Task;

import static com.neha.ToDoList.utils.DateMethods.compareDate;

public class ToDoServiceListImpl implements ToDoService{

    public ToDoServiceListImpl(){
        System.out.println("List Service called.");
    }
    private final List<Task> tasks = new ArrayList<>();
    private int count = 0;

    @Override
    public List<Task> getTaskLists(){
        return tasks;
    }

    @Override
    public List<Task> addBatchTask(List<Task> new_tasks){

        for(Task t : new_tasks){
            int id = ++count;
            Task newTask = new Task(t.getName(), id, t.getDeadline(), t.getIsDone());
            tasks.add(newTask);
        }

        return tasks.subList(tasks.size()-new_tasks.size(), tasks.size());
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
    public List<Task> updateBatchTask(List<Task> updatedTask) throws InvalidTaskException, TaskNotFoundException {

        for(Task t : updatedTask){
            int id = t.getId();
            if (id <= 0) {
                throw new InvalidTaskException("Task Id should be positive");
            }

            boolean check = false;
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
            if(check){
                if (t.getName() != null && !t.getName().isEmpty())
                    newTask.setName(t.getName());

                if (t.getDeadline() != null)
                    newTask.setDeadline(t.getDeadline());

                if (t.getIsDone() != null)
                    newTask.setIsDone(t.getIsDone());

                tasks.set(ind, newTask);
            }
            else{
                throw new TaskNotFoundException("Task not found with id");
            }
        }

        return updatedTask;
    }

    @Override
    public List<Task> search(String name){
        List<Task> searchedTasks = new ArrayList<>();
        for(Task t : tasks){
            if(t.getName().toLowerCase().contains(name.toLowerCase())){
                searchedTasks.add(t);
            }
        }
        return searchedTasks;
    }

    @Override
    public List<Task> sort(String field, int desc) throws InvalidParams {

        if(("deadline".equals(field) || "name".equals(field)) && (desc==0 || desc==1)){
            List<Task> sortedTasks = new ArrayList<>(tasks);
            if(Objects.equals(field, "deadline")){
                if(desc==0) {
                    sortedTasks.sort(Comparator.comparing(Task::getDeadline));
                    return sortedTasks;
                }
                else {
                    sortedTasks.sort(Comparator.comparing(Task::getDeadline).reversed());
                    return sortedTasks;
                }
            }
            else{
                if(desc==0){
                    sortedTasks.sort(Comparator.comparing(Task::getName));
                    return sortedTasks;
                }
                else {
                    sortedTasks.sort(Comparator.comparing(Task::getName).reversed());
                    return sortedTasks;
                }
            }
        }
        else {
            System.out.println("Failed in services");
            throw new InvalidParams("Invalid Parameters");
        }

    }

    @Override
    public List<Task> filter(String name, LocalDate deadline, Boolean isDone){
        List<Task> filteredTasks = new ArrayList<>();
        for(Task t : tasks){
            Boolean namePart = (name==null) || (t.getName().trim().toLowerCase().contains(name.toLowerCase().trim()));
            Boolean deadlinePart = (deadline==null) || compareDate(t.getDeadline(), deadline);
            Boolean isDonePart = (isDone==null) || (t.getIsDone().equals(isDone));

            if(namePart && deadlinePart && isDonePart){
                filteredTasks.add(t);
            }
        }

        return filteredTasks;
    }
}