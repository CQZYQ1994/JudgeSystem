package com.judgesystem.service;

import com.judgesystem.dao.MeetingInfoDao;
import com.judgesystem.entity.MeetingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MeetingInfoService {
    @Autowired
    MeetingInfoDao meetingInfoDao;

    public List<MeetingInfo> findAll(){
        return meetingInfoDao.findAll();
    }
    public int count(){
        return meetingInfoDao.count();
    }
    public MeetingInfo findById(int id){
        return meetingInfoDao.findById(id);
    }
    public void save(MeetingInfo meetingInfo){
        meetingInfoDao.save(meetingInfo);
    }
    public void update(MeetingInfo meetingInfo){
        meetingInfoDao.update(meetingInfo);
    }
    public void deleteById(int id){
        meetingInfoDao.deleteById(id);
    }
    public MeetingInfo findByTopic(String topic){
        return meetingInfoDao.findByTopic(topic);
    }
    public List<MeetingInfo> findCurrentMeet(){
        return meetingInfoDao.findCurrentMeet();
    }
}
