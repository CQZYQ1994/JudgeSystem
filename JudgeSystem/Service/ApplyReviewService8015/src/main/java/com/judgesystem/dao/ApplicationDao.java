package com.judgesystem.dao;

import com.judgesystem.entity.Application;

import java.util.List;

public interface ApplicationDao {
    public List<Application> findAll();
    public List<Application> findCurrentApp();
    public List<Application> findByUserId(int id);
    public Application findById(int id);
    public void insert(Application application);
    public void update(Application application);
    public void deleteById(int id);
    public void updateStatusById(Application application);
    public void updateVoteByName(Application application);
}
