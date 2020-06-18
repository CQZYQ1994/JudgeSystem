package com.judgesystem.controller;

import com.judgesystem.entity.MeetingTime;
import com.judgesystem.service.MeetingTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meetTime")
public class MeetingTimeController {
    @Autowired
    MeetingTimeService meetingTimeService;
    @GetMapping("/findAll")
    public List<MeetingTime> findAll(){
        return meetingTimeService.findAll();
    }
    @GetMapping("/findCurrentMeetTime")
    public List<MeetingTime> findCurrentMeetTime(){
        return meetingTimeService.findCurrentMeetTime();
    }
    @GetMapping("/count")
    public int count(){
        return meetingTimeService.count();
    }
    @GetMapping("/findById/{id}")
    public MeetingTime findById(@PathVariable("id") int id){
        return meetingTimeService.findById(id);
    }
    @PostMapping("/save")
    public void save(@RequestBody MeetingTime meetingInfo){
        meetingTimeService.save(meetingInfo);
    }
    @PutMapping("/update")
    public void update(@RequestBody MeetingTime meetingInfo){
        meetingTimeService.update(meetingInfo);
    }
    @PutMapping("/updateVote")
    public void updateVote(@RequestBody MeetingTime meetingInfo){
        meetingTimeService.updateVote(meetingInfo);
    }
    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") int id){
        meetingTimeService.deleteById(id);
    }
    @PostMapping("/addMeetingTime")
    public void addMeetingTime(@RequestBody List<MeetingTime> list){
        meetingTimeService.addMeetingTime(list);
    }
    @GetMapping("/findByInfoId/{id}")
    public List<MeetingTime> findByInfoId(@PathVariable("id") int id){
        return meetingTimeService.findByInfoId(id);
    }

}
