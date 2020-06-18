package com.judgesystem.controller;

import com.judgesystem.service.StepInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/step")
public class StepInfoController {
    @Autowired
    private StepInfoService stepInfoService;
    @GetMapping("/findAll")
    public List<StepInfo> findAll() {
        return stepInfoService.findAll();
    }
    @GetMapping("/findById/{id}")
    public StepInfo findById(@PathVariable("id") int id){
        return stepInfoService.findById(id);
    }
    @PostMapping("/save")
    public void save(@RequestBody StepInfo StepInfo){
        stepInfoService.save(StepInfo);
    }
    @PutMapping("/update")
    public void update(@RequestBody StepInfo StepInfo){
        stepInfoService.update(StepInfo);
    }
    @PutMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") int id){
        stepInfoService.deleteById(id);
    }
    @PostMapping("/addSteps")
    public void addSteps(@RequestBody List<StepInfo> list){
        stepInfoService.addSteps(list);
    }

}
