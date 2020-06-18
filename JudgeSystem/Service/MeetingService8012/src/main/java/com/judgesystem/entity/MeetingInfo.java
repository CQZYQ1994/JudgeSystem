package com.judgesystem.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MeetingInfo {
    private int id;
    private String create_name;
    private String topic;
    private String place;
    private String participants;
    private Boolean meet_status;
    private String  create_time;
    private String document_path;
    private List<MeetingTime> meetingTimes;


}