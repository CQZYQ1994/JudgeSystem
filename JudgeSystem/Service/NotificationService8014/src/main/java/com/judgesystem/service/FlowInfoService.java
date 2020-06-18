package com.judgesystem.service;

import com.judgesystem.dao.FlowInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowInfoService {
    @Autowired
    private FlowInfoDao flowInfoDao;
    public List<FlowInfo> finAll(){
        return flowInfoDao.findAll();
    }
    public FlowInfo findById(int id){
        return flowInfoDao.findById(id);
    }
    public void save(FlowInfo flowInfo){
        flowInfoDao.save(flowInfo);
    }
    public void deleteById(int id){
        flowInfoDao.deleteById(id);
    }
    public void update(FlowInfo flowInfo){
        flowInfoDao.update(flowInfo);
    }
}
