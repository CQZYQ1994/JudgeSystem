package com.judgesystem.dao;

import com.judgesystem.entity.MeetingTime;

import java.util.List;

public interface MeetingTimeDao {
    public List<MeetingTime> findAll();
    public int count();
    public MeetingTime findById(int id);
    public void save(MeetingTime meetingInfo);
    public void update(MeetingTime meetingInfo);
    public void updateVote(MeetingTime meetingInfo);
    public void deleteById(int id);
    public void addMeetingTime(List<MeetingTime> list);
    public List<MeetingTime> findByInfoId(int id);
    public List<MeetingTime> findCurrentMeetTime();
}
