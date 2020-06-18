package com.judgesystem.controller;


import com.judgesystem.entity.User;
import com.judgesystem.entity.UserRole;
import com.judgesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserServiceController {

    @Autowired
    private UserService userService;


    @GetMapping("/findAll")
    public List<User> findAllUser(){
        return userService.findAllUser();
    }

    @GetMapping("/count")
    public int count(){
        return userService.count();
    }

    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") int id){
        return userService.findById(id);
    }
    @GetMapping("/findByName/{name}")
    public List<User> findUserByName(@PathVariable("name") String name){
        return userService.findUserByName(name);
    }
    @GetMapping("/findByRole/{role}")
    public List<User> findUserByRole(@PathVariable("role") String role){
        return userService.findUserByRole(role);
    }


    @PostMapping("/save")
    public void save(@RequestBody User user){
             userService.save(user);
    }

    @PutMapping("/update")
    public void update(@RequestBody User user){
        userService.update(user);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") int id){
        userService.deleteById(id);
    }
    @PostMapping("/saveUserRole")
    public void saveUserRole(@RequestBody UserRole userRole){
        userService.saveUserRole(userRole);
    }
    @GetMapping("/deleteByUserId/{id}")
    public void deleteUserRoleByUserId(@PathVariable("id") int id){
        userService.deleteUserRoleByUserId(id);
    }
    @PutMapping("/updateUserRoleByUserId")
    public void updateUserRoleByUserId(@RequestBody UserRole userRole){
        userService.updateUserRoleByUserId(userRole);
    }
    @PutMapping("/updateUser")
    public void updateUserInformation(@RequestBody User user){
        userService.updateInformation(user);
    }
    @GetMapping("/getAllUserExceptCurrentUser/{id}")
    public List<User> getAllUserExceptCurrentUser(@PathVariable("id") int id){
        return userService.getAllUserExceptCurrentUser(id);
    }
}
