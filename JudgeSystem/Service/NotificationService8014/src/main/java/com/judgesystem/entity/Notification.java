package com.judgesystem.entity;

import lombok.Data;

@Data
public class Notification {
    private int id;
    private String notifi_name;
    private String notifi_desc;
    private String start_time;
    private String end_time;
    private String create_time;
    private String create_user;
    private boolean del_sign;
    private String document;



}
