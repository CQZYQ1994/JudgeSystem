package com.judgesystem.controller;

import com.judgesystem.entity.User;
import com.judgesystem.feign.UserServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/UI")
public class UIServiceController {
    @Value("${server.port}")
    private String port;
    @Autowired
    private UserServiceFeign userServiceFeign;

    @GetMapping("/index")
    public String printHello() {
        return "UIService的端口号:"+this.port;
    }

    @GetMapping("/findAll/{index}/{limit}")
    public List<User> findAll(@PathVariable("index") int index,@PathVariable("limit") int limit){
        return userServiceFeign.findAll(index, limit);
    }
    @GetMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") int id){
        userServiceFeign.deleteById(id);
    }
    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") int id){
        return userServiceFeign.findById(id);
    }
    @GetMapping("/userFindAll")
    public ModelAndView userFindAll(){
        List<User> users=userServiceFeign.findAll(0,4);
        ModelAndView mv=new ModelAndView();
        mv.addObject("users",users);
        mv.setViewName("userFindAll");
        return mv;
    }
}
