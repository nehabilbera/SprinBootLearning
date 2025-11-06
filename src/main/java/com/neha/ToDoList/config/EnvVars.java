package com.neha.ToDoList.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;

public class EnvVars {
    EnvVars env = new EnvVars();
    public EnvVars(){
        System.out.println("Environment variable created.");
    }

    public String getEnv(String env){
        return System.getenv(env);
    }
}
