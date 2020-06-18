package com.judgesystem.service;

import com.judgesystem.dao.NotificationDao;
import com.judgesystem.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    NotificationDao notificationDao;
    public List<Notification> findAll(){
        return notificationDao.findAll();
    }
    public Notification findById(int id){
        return notificationDao.findById(id);
    }
    public void update(Notification notification){
        notificationDao.update(notification);
    }
    public void save(Notification notification){
        notificationDao.save(notification);
    }
    public void deleteById(int id){
        notificationDao.deleteById(id);
    }
}
