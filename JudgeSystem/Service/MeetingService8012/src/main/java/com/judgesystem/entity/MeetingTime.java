package com.judgesystem.entity;

import lombok.Data;

import java.util.Date;
@Data
public class MeetingTime {
    private int id;
    private String  meetingTime;
    private int vote;
    private int meetingInfoId;
    private MeetingInfo meetingInfo;
}
