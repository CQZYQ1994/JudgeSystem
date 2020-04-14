package com.judgesystem.entity;

import lombok.Data;

@Data
public class Meeting {
    private int id;
    private String create_name;
    private String topic;
    private String place;
    private String participants;
    private String meeting_time1;
    private String meeting_time2;
    private String meeting_time3;
    private String document_path;


}