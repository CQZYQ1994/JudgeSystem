package com.judgesystem.service;

import com.judgesystem.dao.StepInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StepInfoService {
    @Autowired
    private StepInfoDao stepInfoDao;
    public StepInfo findById(int id){
        return stepInfoDao.findById(id);
    }
    public List<StepInfo> findAll(){
        return stepInfoDao.findAll();
    }
    public void update(StepInfo stepInfo){
        stepInfoDao.update(stepInfo);
    }
    public void save(StepInfo stepInfo){
        stepInfoDao.save(stepInfo);
    }
    public void addSteps(List<StepInfo> stepInfos){
        stepInfoDao.addSteps(stepInfos);
    }
    public void deleteById(int id){
        stepInfoDao.deleteById(id);
    }
}
