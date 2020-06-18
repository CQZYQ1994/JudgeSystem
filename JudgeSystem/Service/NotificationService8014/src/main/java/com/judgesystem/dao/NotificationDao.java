package com.judgesystem.dao;

import com.judgesystem.entity.Notification;

import java.util.List;

public interface NotificationDao {
    public List<Notification> findAll();
    public Notification findById(int id);
    public void save(Notification notification);
    public void update(Notification notification);
    public void deleteById(int id);
}
