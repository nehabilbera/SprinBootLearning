package com.neha.ToDoList.utils;

import java.time.LocalDate;

public class DateMethods {
    public static Boolean compareYear(LocalDate date1, LocalDate date2){
        if(date1==null || date2==null) return false;
        return (date1.getYear()== date2.getYear());
    }

    public static Boolean compareMonth(LocalDate date1, LocalDate date2){
        if(date1==null || date2==null) return false;
        return (date1.getMonth()== date2.getMonth());
    }

    public static Boolean compareDate(LocalDate date1, LocalDate date2){
        if(date1==null || date2==null) return false;
        return date1.equals(date2);
    }
}
