package com.judgesystem.controller;


import com.judgesystem.entity.MeetingInfo;
import com.judgesystem.service.MeetingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meetInfo")
public class MeetingInfoController {

    @Autowired
    private MeetingInfoService meetingInfoService;


    @GetMapping("/findAll")
    public List<MeetingInfo> findAll() {
        return meetingInfoService.findAll();
    }
    @GetMapping("/findById/{id}")
    public MeetingInfo findById(@PathVariable("id") int id){
        return meetingInfoService.findById(id);
    }
    @GetMapping("/findByTopic/{topic}")
    public MeetingInfo findByTopic(@PathVariable("topic") String  topic){
        return meetingInfoService.findByTopic(topic);
    }
    @GetMapping("/count")
    public int count(){
        return meetingInfoService.count();
    }
    @PostMapping("/save")
    public void save(@RequestBody MeetingInfo meetingInfo){
        meetingInfoService.save(meetingInfo);
    }
    @PutMapping("/update")
    public void update(@RequestBody MeetingInfo meetingInfo){
        meetingInfoService.update(meetingInfo);
    }
    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") int id){
        meetingInfoService.deleteById(id);
    }
    @GetMapping("/findCurrentMeet")
    public List<MeetingInfo> findCurrentMeet() {
        return meetingInfoService.findCurrentMeet();
    }

}
