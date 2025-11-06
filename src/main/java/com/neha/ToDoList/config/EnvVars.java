package com.neha.ToDoList.config;

import org.springframework.beans.factory.annotation.Value;

public class EnvVars {

    public EnvVars(){
        System.out.println("Environment variable created.");
    }

    @Value("${DATABASE_URL}")
    private String dbUrl;

    public String getDbUrl(){
        return dbUrl;
    }

    @Value("${DATABASE_USERNAME}")
    private String dbUsername;

    public String getDbUsername(){
        return dbUsername;
    }

    @Value("${DATABASE_PASSWORD}")
    private String dbPassword;

    public String getDbPassword(){
        return dbPassword;
    }
}
