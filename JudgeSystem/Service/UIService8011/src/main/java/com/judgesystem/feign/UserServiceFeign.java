package com.judgesystem.feign;

import com.judgesystem.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(value = "UserService")
public interface UserServiceFeign {
    @GetMapping("/user/findAll/{index}/{limit}")
    public List<User> findAll(@PathVariable("index") int index, @PathVariable("limit") int limit);
    @DeleteMapping("/user/deleteById/{id}")
    public void deleteById(@PathVariable("id") int id);
    @PutMapping("/user/save")
    public void save(User user);
    @GetMapping("/count")
    public int count();
    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") int id);
    @PutMapping("/update")
    public void update(User user);
}
