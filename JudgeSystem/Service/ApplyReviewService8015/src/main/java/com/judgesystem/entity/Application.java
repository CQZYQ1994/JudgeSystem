package com.judgesystem.entity;

import lombok.Data;

@Data
public class Application {
    private int id;
    private String name;
    private String topic;
    private String description;
    private boolean review_status;
    private String create_time;
    private String update_time;
    private String review_opinion;
    private String review_result;
    private String review_vote;
    private String document;
    private int user_id;
    private String processDefKey;
    private String processInsId;
    private UserInfo userInfo;

}
