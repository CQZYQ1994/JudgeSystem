package com.judgesystem.service;

import com.judgesystem.dao.MeetingTimeDao;
import com.judgesystem.entity.MeetingTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MeetingTimeService {
    @Autowired
    MeetingTimeDao meetingTimeDao;
    public List<MeetingTime> findAll(){
        return meetingTimeDao.findAll();
    }
    public List<MeetingTime> findCurrentMeetTime(){
        return meetingTimeDao.findCurrentMeetTime();
    }
    public int count(){
        return meetingTimeDao.count();
    }
    public MeetingTime findById(int id){
        return meetingTimeDao.findById(id);
    }
    public void save(MeetingTime meetingInfo){
        meetingTimeDao.save(meetingInfo);
    }
    public void update(MeetingTime meetingInfo){
        meetingTimeDao.update(meetingInfo);
    }
    public void updateVote(MeetingTime meetingInfo){
        meetingTimeDao.updateVote(meetingInfo);
    }
    public void deleteById(int id){
        meetingTimeDao.deleteById(id);
    }
    public void addMeetingTime(List<MeetingTime> list){
        meetingTimeDao.addMeetingTime(list);
    }
    public List<MeetingTime> findByInfoId(int id){
        return meetingTimeDao.findByInfoId(id);
    }
}
