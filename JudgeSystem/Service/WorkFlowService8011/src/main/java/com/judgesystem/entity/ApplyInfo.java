package com.judgesystem.entity;

import lombok.Data;

@Data
public class ApplyInfo {
    private String deal_user_id;
    private Boolean submit;
    private String taskId;
    private String taskName;
    private String review_result;

    public String getDeal_user_id() {
        return deal_user_id;
    }

    public void setDeal_user_id(String deal_user_id) {
        this.deal_user_id = deal_user_id;
    }

    public Boolean getSubmit() {
        return submit;
    }

    public void setSubmit(Boolean submit) {
        this.submit = submit;
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

    public String getReview_result() {
        return review_result;
    }

    public void setReview_result(String review_result) {
        this.review_result = review_result;
    }
}
