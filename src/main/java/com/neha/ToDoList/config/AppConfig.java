package com.neha.ToDoList.config;

import com.neha.ToDoList.dao.ToDoDao;
import com.neha.ToDoList.dao.ToDoDaoMySqlImpl;
import com.neha.ToDoList.service.ToDoService;
import com.neha.ToDoList.service.ToDoServiceDaoImpl;
import com.neha.ToDoList.service.ToDoServiceListImpl;
import com.neha.ToDoList.service.ToDoServiceMapImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class AppConfig {

    //@Bean
    //@Primary
    public EnvVars envVars(){
        return new EnvVars();
    }

    @Bean
    @Primary
    public ToDoDao toDoDao(DataSource dataSource) throws SQLException {
        return new ToDoDaoMySqlImpl(dataSource);
    }

    @Primary
    @Bean
    public ToDoService toDoService(ToDoDao toDoDao) throws SQLException {
        return new ToDoServiceDaoImpl(toDoDao);
    }

    //@Bean
    public ToDoService toDoService1(){
        return new ToDoServiceListImpl();
    }

    //@Bean
    public ToDoService toDoService2(){
        return new ToDoServiceMapImpl();
    }
}