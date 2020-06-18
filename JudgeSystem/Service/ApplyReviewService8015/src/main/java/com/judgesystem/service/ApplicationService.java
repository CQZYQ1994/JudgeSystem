package com.judgesystem.service;

import com.judgesystem.dao.ApplicationDao;
import com.judgesystem.entity.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ApplicationService {
    @Autowired
    ApplicationDao applicationDao;
    public List<Application> findAll(){
        return applicationDao.findAll();
    }
    public List<Application> findByUserId(int uid){
        return applicationDao.findByUserId(uid);
    }
    public List<Application> findCurrentApp(){
        return applicationDao.findCurrentApp();
    }
    public Application findById(int id){
        return applicationDao.findById(id);
    }
    public void insert(Application application){
        applicationDao.insert(application);
    }
    public void update(Application application){
        applicationDao.update(application);
    }
    public void deleteById(int id){
        applicationDao.deleteById(id);
    }
    public void updateStatusById(Application application){
        applicationDao.updateStatusById(application);
    }
    public void updateVoteByName(Application application){
        applicationDao.updateVoteByName(application);
    }
}
