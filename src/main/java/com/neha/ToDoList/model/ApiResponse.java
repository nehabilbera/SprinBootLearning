package com.neha.ToDoList.model;

import java.util.Date;

public record ApiResponse<T> (T data, String message, Date timeStamp, String status){}

//public class ApiResponse<T> {
//    private T data;
//    private String message;
//    private Date timeStamp;
//    private String status;
//
//    public ApiResponse(T data, String message){
//        this.data=data;
//        this.message=message;
//        this.timeStamp=new Date();
//        this.status = (data != null) ? "Success" : "Error";
//    }
//
//    public T getData(){ return this.data; }
//    public void setData(T data){ this.data=data; }
//
//    public String getMessage(){ return this.message; }
//    public void setMessage(String message) { this.message=message; }
//
//    public Date getTimeStamp() { return this.timeStamp; }
//    public void setTimeStamp(Date timeStamp) { this.timeStamp=timeStamp; }
//
//    public String getStatus(){ return this.status; }
//    public void setStatus(String status) { this.status=status; }
//
//}
