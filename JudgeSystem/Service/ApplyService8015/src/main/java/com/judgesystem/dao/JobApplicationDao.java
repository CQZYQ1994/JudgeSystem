package com.judgesystem.dao;

import com.judgesystem.entity.JobApplication;

import java.util.List;

public interface JobApplicationDao {
    public List<JobApplication> findAll(int index, int limit);
    public int count();
    public JobApplication findById(int id);
    public void save(JobApplication jobApplication);
    public void update(JobApplication jobApplication);
    public void deleteById(int id);
}
