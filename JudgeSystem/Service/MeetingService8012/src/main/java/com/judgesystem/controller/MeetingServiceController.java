package com.judgesystem.controller;

import com.judgesystem.dao.MeetingDao;
import com.judgesystem.entity.Meeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meet")
public class MeetingServiceController {
    @Value("${server.port}")
    private String port;
    @Autowired
    private MeetingDao meetingDao;
    @GetMapping("/index")
    public String printHello() {
        return "MeetingService port: "+this.port;
    }

    @GetMapping("/findAll/{index}/{limit}")
    public List<Meeting> findAll(@PathVariable("index") int index, @PathVariable("limit") int limit) {
        return meetingDao.findAll(index,limit);
    }
    @GetMapping("/findById/{id}")
    public Meeting findById(@PathVariable("id") int id){
        return meetingDao.findById(id);
    }
    @GetMapping("/count")
    public int count(){
        return meetingDao.count();
    }
    @PostMapping("/save")
    public void save(@RequestBody Meeting meeting){
        meetingDao.save(meeting);
    }
    @PutMapping("/update")
    public void update(@RequestBody Meeting meeting){
        meetingDao.update(meeting);
    }
    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") int id){
        meetingDao.deleteById(id);
    }

}
