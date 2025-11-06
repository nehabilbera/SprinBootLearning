package com.neha.ToDoList.config;

import com.neha.ToDoList.controller.ToDoListController;
import com.neha.ToDoList.dao.ToDoDao;
import com.neha.ToDoList.dao.ToDoDaoMySqlImpl;
import com.neha.ToDoList.service.ToDoService;
import com.neha.ToDoList.service.ToDoServiceDaoImpl;
import com.neha.ToDoList.service.ToDoServiceListImpl;
import com.neha.ToDoList.service.ToDoServiceMapImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.sql.SQLException;

@Configuration
public class AppConfig {
    @Primary
    @Bean
    public ToDoDao toDoDao() throws SQLException {
        return new ToDoDaoMySqlImpl();
    }

    @Bean
    @Primary
    public ToDoService toDoService(ToDoDao toDoDao) throws SQLException {
        return new ToDoServiceDaoImpl(toDoDao);
    }

    @Bean
    public ToDoService toDoService1(){
        return new ToDoServiceListImpl();
    }

    @Bean
    public ToDoService toDoService2(){
        return new ToDoServiceMapImpl();
    }
}