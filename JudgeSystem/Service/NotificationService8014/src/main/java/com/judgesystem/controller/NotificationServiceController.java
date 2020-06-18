package com.judgesystem.controller;


import com.judgesystem.entity.Notification;
import com.judgesystem.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notify")
public class NotificationServiceController {

    @Autowired
    private NotificationService notificationService;

      @GetMapping("/findAll")
    public List<Notification> findAll() {
        return notificationService.findAll();
    }
    @GetMapping("/findById/{id}")
    public Notification findById(@PathVariable("id") int id){
        return notificationService.findById(id);
    }
    @PostMapping("/save")
    public void save(@RequestBody Notification notification){
        notificationService.save(notification);
    }
    @PutMapping("/update")
    public void update(@RequestBody Notification notification){
        notificationService.update(notification);
    }
    @PutMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") int id){
        notificationService.deleteById(id);
    }


}
