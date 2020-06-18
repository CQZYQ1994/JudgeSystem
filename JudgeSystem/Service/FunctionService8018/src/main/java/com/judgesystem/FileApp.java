package com.judgesystem;
//import com.judgesystem.entity.FileProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

//@EnableConfigurationProperties({FileProperties.class})
@SpringBootApplication
public class FileApp {
    public static void main(String[] args){
        SpringApplication.run(FileApp.class,args);
    }
}
