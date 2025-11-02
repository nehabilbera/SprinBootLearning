package com.neha.ToDoList.exception;

public class TaskNotFoundException extends Exception{
    public TaskNotFoundException(String message){
        super(message);
    }
}
