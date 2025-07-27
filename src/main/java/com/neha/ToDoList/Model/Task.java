package com.neha.ToDoList.Model;

import java.util.Date;

public record Task(String name, int id, Date deadline, Boolean isDone) {
}

//public class Task {
//    private String name;
//    private int id;
//    private Date deadline;
//    private boolean isDone;
//
//    public Task(String name, int id, Date deadline, boolean isDone) {
//        this.name = name;
//        this.id = id;
//        this.deadline = deadline;
//        this.isDone = isDone;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public Date getDeadline() {
//        return deadline;
//    }
//
//    public void setDeadline(Date deadline) {
//        this.deadline = deadline;
//    }
//
//    public boolean isDone() {
//        return isDone;
//    }
//
//    public void setDone(boolean isDone) {
//        this.isDone = isDone;
//    }
//}
