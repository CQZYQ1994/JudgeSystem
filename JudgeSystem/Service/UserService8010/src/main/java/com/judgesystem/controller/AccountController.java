package com.judgesystem.controller;

import com.judgesystem.dao.ApplicantDao;
import com.judgesystem.dao.UserDao;
import com.judgesystem.entity.User;
import com.judgesystem.service.TokenService;
import com.judgesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;


    @GetMapping("/login/{username}/{password}")
    public Object login(@PathVariable("username") String username, @PathVariable("password") String password){
        Object token=userService.login(username, password);
        if (token!=null){
            //String token=tokenService.getToken(user);
            return token;
        }

       return token;
}


}
