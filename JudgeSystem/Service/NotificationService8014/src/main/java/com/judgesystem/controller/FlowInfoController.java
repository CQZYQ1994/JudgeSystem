package com.judgesystem.controller;

import com.judgesystem.service.FlowInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flow")
public class FlowInfoController {
    @Autowired
    private FlowInfoService flowInfoService;
    @GetMapping("/findAll")
    public List<FlowInfo> findAll() {
        return flowInfoService.finAll();
    }
    @GetMapping("/findById/{id}")
    public FlowInfo findById(@PathVariable("id") int id){
        return flowInfoService.findById(id);
    }
    @PostMapping("/save")
    public void save(@RequestBody FlowInfo flowInfo){
        flowInfoService.save(flowInfo);
    }
    @PutMapping("/update")
    public void update(@RequestBody FlowInfo flowInfo){
        flowInfoService.update(flowInfo);
    }
    @PutMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") int id){
        flowInfoService.deleteById(id);
    }


}
