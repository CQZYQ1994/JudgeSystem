package com.judgesystem.dao;

import com.judgesystem.entity.Job;

import java.util.List;

public interface JobDao {
    public List<Job> findAll(int index, int limit);
    public int count();
    public Job findById(int id);
    public void save(Job job);
    public void update(Job job);
    public void deleteById(int id);
}
