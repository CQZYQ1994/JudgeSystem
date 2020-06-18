package com.judgesystem.service;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;

public class ActivitiDeployment {
    public static void main(String[] args){
        ProcessEngine processEngine= ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService=processEngine.getRepositoryService();
        repositoryService.createDeployment()
                .addClasspathResource("processes/ReviewProcess.xml")
                .addClasspathResource("processes/ReviewProcess.png")
                .deploy();
    }
}
