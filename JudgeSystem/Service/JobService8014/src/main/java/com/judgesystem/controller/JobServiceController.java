package com.judgesystem.controller;

import com.judgesystem.dao.JobDao;
import com.judgesystem.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobServiceController {
    @Value("${server.port}")
    private String port;
    @Autowired
    private JobDao jobDao;
    @GetMapping("/index")
    public String getPort() {
        return "JobService port: "+this.port;
    }
    @GetMapping("/findAll/{index}/{limit}")
    public List<Job> findAll(@PathVariable("index") int index, @PathVariable("limit") int limit) {
        return jobDao.findAll(index,limit);
    }
    @GetMapping("/findById/{id}")
    public Job findById(@PathVariable("id") int id){
        return jobDao.findById(id);
    }
    @GetMapping("/count")
    public int count(){
        return jobDao.count();
    }
    @PostMapping("/save")
    public void save(@RequestBody Job job){
        jobDao.save(job);
    }
    @PutMapping("/update")
    public void update(@RequestBody Job job){
        jobDao.update(job);
    }
    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") int id){
        jobDao.deleteById(id);
    }


}
