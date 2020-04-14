package com.judgesystem.controller;

import com.judgesystem.dao.ApplicantDao;
import com.judgesystem.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ApplicantDao applicantDao;
    @GetMapping("/login/{username}/{password}/{role}")
    public Object login(@PathVariable("username") String username, @PathVariable("password") String password, @PathVariable("role") String role){
        Object token=null;
        switch (role){
            case "user":
                token=userDao.login(username, password);
                break;
            case "applicant":
                token=applicantDao.login(username, password);
                break;
        }
        return token;
    }


}
