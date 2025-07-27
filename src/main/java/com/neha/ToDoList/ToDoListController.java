package com.neha.ToDoList;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


class Task{
    private String task;
    private int id;
    private Date deadline;
    private boolean isDone;

    Task(String task, int id, Date deadline, boolean isDone){
        this.task=task;
        this.id=id;
        this.deadline=deadline;
        this.isDone=isDone;
    }

    public String getTask() { return task; }
    public void setTask(String task) { this.task = task; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getDeadline() { return deadline; }
    public void setDeadline(Date deadline) { this.deadline = deadline; }

    public boolean isDone() { return isDone; }
    public void setDone(boolean isDone) { this.isDone = isDone; }
}

@RestController
public class ToDoListController {

    List<Task> tasks = new ArrayList<>();

    int counter = 0;
    @GetMapping("getTasks")
    public List<Task> getTaskLists(){
        return tasks;
    }

    @PostMapping("addTask")
    public String addTask(){
        counter = counter + 1;
        tasks.add(new Task("Neha", counter, new Date() ,false));
        return "Task Successfully Added!";
    }

}
