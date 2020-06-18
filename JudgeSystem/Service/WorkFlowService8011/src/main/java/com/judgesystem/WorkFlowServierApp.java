package com.judgesystem;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@MapperScan("com.judgesystem.dao")
public class WorkFlowServierApp {
    public static void main(String[] args){
        SpringApplication.run(WorkFlowServierApp.class,args);
    }
}
