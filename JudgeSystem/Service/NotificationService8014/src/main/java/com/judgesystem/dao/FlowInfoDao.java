package com.judgesystem.dao;

import java.util.List;

public interface FlowInfoDao {
    public List<FlowInfo> findAll();
    public FlowInfo findById(int id);
    public void save(FlowInfo flowInfo);
    public void update(FlowInfo flowInfo);
    public void deleteById(int id);
}
