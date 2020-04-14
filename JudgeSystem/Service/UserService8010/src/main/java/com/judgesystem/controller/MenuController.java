package com.judgesystem.controller;

import com.judgesystem.entity.Menu;
import com.judgesystem.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    MenuService menuService;
    @GetMapping("/getMenu/{id}")
    public List<Menu> getUserMenu(@PathVariable("id") int id){
        return menuService.gerMenusByUserId(id);
    }
}
