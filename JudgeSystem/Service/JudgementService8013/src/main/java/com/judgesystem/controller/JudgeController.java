package com.judgesystem.controller;

import com.judgesystem.dao.JudgementDao;
import com.judgesystem.entity.Judgement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/judge")
public class JudgeController {
    @Value("${server.port}")
    private String port;
    @Autowired
    private JudgementDao judgementDao;
    @GetMapping("/index")
    public String getPort() {
        return "JudgementService port: "+this.port;
    }
    @GetMapping("/findAll/{index}/{limit}")
    public List<Judgement> findAll(@PathVariable("index") int index, @PathVariable("limit") int limit) {
        return judgementDao.findAll(index,limit);
    }
    @GetMapping("/findById/{id}")
    public Judgement findById(@PathVariable("id") int id){
        return judgementDao.findById(id);
    }
    @GetMapping("/count")
    public int count(){
        return judgementDao.count();
    }
    @PostMapping("/save")
    public void save(@RequestBody Judgement judgement){
        judgementDao.save(judgement);
    }
    @PutMapping("/update")
    public void update(@RequestBody Judgement judgement){
        judgementDao.update(judgement);
    }
    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") int id){
        judgementDao.deleteById(id);
    }
}
