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
    @Bean
    public ToDoService ToDoServiceListImpl() {
        return new ToDoServiceListImpl();
    }

    @Bean
    public ToDoService ToDoServiceMapImpl() {
        return new ToDoServiceMapImpl();
    }

    @Primary
    @Bean
    public ToDoService ToDoServiceDaoImpl() throws SQLException {
        return new ToDoServiceDaoImpl();
    }




    @Primary
    @Bean
    public ToDoDao ToDoDaoMySqlImpl() throws SQLException {
        return new ToDoDaoMySqlImpl();
    }
}
