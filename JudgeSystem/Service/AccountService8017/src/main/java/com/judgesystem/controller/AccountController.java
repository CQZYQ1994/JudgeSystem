package com.judgesystem.controller;

import com.judgesystem.dao.ApplicantDao;
import com.judgesystem.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ApplicantDao applicantDao;
    @GetMapping("/login/{account}/{password}/{type}")
    public Object login(@PathVariable("account") String account, @PathVariable("password") String password, @PathVariable("type") String type){
        Object object=null;
        switch (type){
            case "user":
                object=userDao.login(account, password);
                break;
            case "applicant":
                object=applicantDao.login(account, password);
                break;
        }
        return object;
    }


}
