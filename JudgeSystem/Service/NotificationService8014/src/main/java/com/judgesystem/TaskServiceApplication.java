package com.judgesystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.judgesystem.dao")
public class TaskServiceApplication {
    public static void main(String[] args){
        SpringApplication.run(TaskServiceApplication.class,args);
    }
}
