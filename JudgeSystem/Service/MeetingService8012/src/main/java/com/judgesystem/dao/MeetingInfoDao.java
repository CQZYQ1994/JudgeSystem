package com.judgesystem.dao;
import com.judgesystem.entity.MeetingInfo;
import java.util.List;

public interface MeetingInfoDao {
    public List<MeetingInfo> findAll();
    public int count();
    public MeetingInfo findById(int id);
    public void save(MeetingInfo meetingInfo);
    public void update(MeetingInfo meetingInfo);
    public void deleteById(int id);
    public MeetingInfo findByTopic(String topic);
    public List<MeetingInfo> findCurrentMeet();
}
