package com.judgesystem.controller;

import com.judgesystem.dao.ApplicationDao;
import com.judgesystem.entity.Application;
import com.judgesystem.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apply")
public class ApplyServiceController {
    @Value("${server.port}")
    private String port;
    @Autowired
    private ApplicationService applicationService;
    @GetMapping("/findAll")
    public List<Application> findAll() {
        return applicationService.findAll();
    }
    @GetMapping("/findById/{id}")
    public Application findById(@PathVariable("id") int id){
        return applicationService.findById(id);
    }
    @GetMapping("/findByUserId/{id}")
    public List<Application> findByUserId(@PathVariable("id") int id){
        return applicationService.findByUserId(id);
    }

    @GetMapping("/findCurrentApp")
    public List<Application> findCurrentApp(){
        return applicationService.findCurrentApp();
    }

    @PutMapping("/update")
    public void update(@RequestBody Application application){
        applicationService.update(application);
    }
    @PutMapping("/updateStatusById")
    public void updateStatusById(@RequestBody Application application){
        applicationService.updateStatusById(application);
    }
    @PutMapping("/updateVoteByName")
    public void updateVoteByName(@RequestBody Application application){
        applicationService.updateVoteByName(application);
    }
    @PostMapping("/insert")
    public void save(@RequestBody Application application){
        applicationService.insert(application);
    }
    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") int id){
        applicationService.deleteById(id);
    }


}
