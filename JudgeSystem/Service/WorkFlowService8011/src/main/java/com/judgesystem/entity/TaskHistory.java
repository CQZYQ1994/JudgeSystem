package com.judgesystem.entity;

import lombok.Data;

@Data
public class TaskHistory {
    private String taskHisId;
    private String taskHisName;
    private String taskProcInsId;
    private String taskStartTime;
    private String taskEndTime;
    private String taskDurationInMillis;
    private String taskUser;

    public String getTaskUser() {
        return taskUser;
    }

    public void setTaskUser(String taskUser) {
        this.taskUser = taskUser;
    }

    public String getTaskHisId() {
        return taskHisId;
    }

    public void setTaskHisId(String taskHisId) {
        this.taskHisId = taskHisId;
    }

    public String getTaskHisName() {
        return taskHisName;
    }

    public void setTaskHisName(String taskHisName) {
        this.taskHisName = taskHisName;
    }

    public String getTaskProcInsId() {
        return taskProcInsId;
    }

    public void setTaskProcInsId(String taskProcInsId) {
        this.taskProcInsId = taskProcInsId;
    }

    public String getTaskStartTime() {
        return taskStartTime;
    }

    public void setTaskStartTime(String taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    public String getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(String taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public String getTaskDurationInMillis() {
        return taskDurationInMillis;
    }

    public void setTaskDurationInMillis(String taskDurationInMillis) {
        this.taskDurationInMillis = taskDurationInMillis;
    }
}
