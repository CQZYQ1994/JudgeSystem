package com.judgesystem.entity;

import lombok.Data;

@Data
public class TaskInfo {
    private String taskId;
    private String taskName;
    private String taskCreateTime;
    private String taskAssignee;
    private String taskProcInsId;
    private String taskExecutionId;
    private String taskProcDefId;
    private String businessKey;

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskCreateTime() {
        return taskCreateTime;
    }

    public void setTaskCreateTime(String taskCreateTime) {
        this.taskCreateTime = taskCreateTime;
    }

    public String getTaskAssignee() {
        return taskAssignee;
    }

    public void setTaskAssignee(String taskAssignee) {
        this.taskAssignee = taskAssignee;
    }

    public String getTaskProcInsId() {
        return taskProcInsId;
    }

    public void setTaskProcInsId(String taskProcInsId) {
        this.taskProcInsId = taskProcInsId;
    }

    public String getTaskExecutionId() {
        return taskExecutionId;
    }

    public void setTaskExecutionId(String taskExecutionId) {
        this.taskExecutionId = taskExecutionId;
    }

    public String getTaskProcDefId() {
        return taskProcDefId;
    }

    public void setTaskProcDefId(String taskProcDefId) {
        this.taskProcDefId = taskProcDefId;
    }
}
