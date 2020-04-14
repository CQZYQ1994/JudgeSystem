package com.judgesystem.controller;

import com.judgesystem.dao.RoleDao;
import com.judgesystem.dao.UserDao;
import com.judgesystem.entity.Role;
import com.judgesystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserServiceController {
    @Value("${server.port}")
    private String port;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @GetMapping("/index")
    public String index() {
        return "hello user"+this.port;
    }

    @GetMapping("/findAll/{index}/{limit}")
    public List<User> findAll(@PathVariable("index") int index,@PathVariable("limit") int limit) {
        return userDao.findAll(index,limit);
    }

    public List<Role> findAll(){
        return roleDao.findAll();
    }
    @GetMapping("/count")
    public int count(){
        return userDao.count();
    }

    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") int id){
        return userDao.findById(id);
    }

    @PostMapping("/save")
    public int save(@RequestBody User user){
             userDao.save(user);
        return 1;
    }

    @PutMapping("/update")
    public int update(@RequestBody User user){
        userDao.update(user);
        return 1;
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") int id){
        userDao.deleteById(id);
    }
}
