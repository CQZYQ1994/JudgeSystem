package com.judgesystem.controller;

import com.judgesystem.entity.Menu;
import com.judgesystem.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    MenuService menuService;
    @GetMapping("/getMenu/{id}")
    public List<Menu> getUserMenu(@PathVariable("id") int id){
        return menuService.getMenusByUserId(id);
    }
    @GetMapping("/getMenuById/{id}")
    public Menu getMenuById(@PathVariable("id") int id){
        return menuService.getMenuById(id);
    }
    @GetMapping("/getAllMenu")
    public List<Menu> getAllMenu(){
        return menuService.getAllMenus();
    }
    @GetMapping("/getAll")
    public List<Menu> getAll(){
        return menuService.getAll();
    }
    @PostMapping("/save")
    public void save(@RequestBody Menu menu){
        menuService.save(menu);
    }
    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") int id){
        menuService.deleteById(id);
    }
    @PutMapping("/update")
    public void update(@RequestBody Menu menu){
        menuService.updateMenus(menu);
    }
}
