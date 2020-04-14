package com.judgesystem.controller;

import com.judgesystem.dao.JobApplicationDao;
import com.judgesystem.entity.JobApplication;
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
    private JobApplicationDao jobApplicationDao;
    @GetMapping("/index")
    public String getPort() {
        return "ApplyService port: "+this.port;
    }
    @GetMapping("/findAll/{index}/{limit}")
    public List<JobApplication> findAll(@PathVariable("index") int index, @PathVariable("limit") int limit) {
        return jobApplicationDao.findAll(index,limit);
    }
    @GetMapping("/findById/{id}")
    public JobApplication findById(@PathVariable("id") int id){
        return jobApplicationDao.findById(id);
    }
    @GetMapping("/count")
    public int count(){
        return jobApplicationDao.count();
    }
    @PutMapping("/update")
    public void update(@RequestBody JobApplication jobApplication){
        jobApplicationDao.update(jobApplication);
    }
    @PostMapping("/save")
    public void save(@RequestBody JobApplication jobApplication){
        jobApplicationDao.save(jobApplication);
    }
    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") int id){
        jobApplicationDao.deleteById(id);
    }


}
