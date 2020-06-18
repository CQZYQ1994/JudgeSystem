package com.judgesystem.controller;

import com.judgesystem.entity.Role;
import com.judgesystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @GetMapping("/findAll")
    public List<Role> finAll(){
        return roleService.findAll();
    }
    @GetMapping("/findById/{id}")
    public Role findById(@PathVariable("id") int id){
        return roleService.findById(id);
    }
    @PostMapping("/insert")
    public void insertRole(@RequestBody Role role){
        roleService.insertRole(role);
    }
    @PutMapping("/update")
    public void updateRole(@RequestBody Role role){
        roleService.updateRole(role);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteRole(@PathVariable("id") int id){
        roleService.deleteRole(id);
    }
}
