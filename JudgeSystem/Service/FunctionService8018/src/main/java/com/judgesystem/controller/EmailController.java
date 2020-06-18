package com.judgesystem.controller;

import com.judgesystem.entity.EmailContent;
import com.judgesystem.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("mail")
public class EmailController {
    @Autowired
    MailService mailService;
    @GetMapping("/hello")
    public String printHello(){
        return "hello email";
    }
    @PostMapping("/send")
    public void sendEmail(@RequestBody EmailContent em){
        EmailContent emailContent=em;
        mailService.sendSimpleMail(emailContent.getFrom(), emailContent.getTo(),emailContent.getCc(),
                emailContent.getSubject(), emailContent.getContent());
    }
}
