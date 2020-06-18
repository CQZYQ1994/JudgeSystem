package com.judgesystem.dao;

import java.util.List;

public interface StepInfoDao {
    public List<StepInfo> findAll();
    public StepInfo findById(int id);
    public void save(StepInfo stepInfo);
    public void update(StepInfo stepInfo);
    public void deleteById(int id);
    public void addSteps(List<StepInfo> stepInfos);
}
