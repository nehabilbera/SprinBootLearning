package com.neha.ToDoList.model;

import java.util.Date;

//method-1 ------ Task is a POJO class
    public class Task {
        private String name;
        private int id;
        private Date deadline;
        private Boolean isDone;

        public Task(String name, int id, Date deadline, Boolean isDone) {
            this.name = name;
            this.id = id;
            this.deadline = deadline;
            this.isDone = isDone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Date getDeadline() {
            return deadline;
        }

        public void setDeadline(Date deadline) {
            this.deadline = deadline;
        }

        public Boolean getIsDone() {
            return isDone;
        }

        public void setIsDone(Boolean isDone) {
            this.isDone = isDone;
        }
    }



//method-2 ----- Task is a record having no getter and setter
//public record Task(String name, int id, Date deadline, Boolean isDone) {}
