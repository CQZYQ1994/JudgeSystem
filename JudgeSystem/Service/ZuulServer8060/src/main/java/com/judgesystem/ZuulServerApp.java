package com.judgesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ZuulServerApp {
    public static void main(String[] args){
        SpringApplication.run(ZuulServerApp.class,args);
    }
}
