package com.judgesystem.service;

import com.judgesystem.dao.MenuDao;
import com.judgesystem.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuDao menuDao;
    public List<Menu> getMenusByUserId(int UserId){
        return menuDao.getMenusByUserId(UserId);
    }
    public List<Menu> getAllMenus(){
        return menuDao.getAllMenus();
    }
    public List<Menu> getAll(){
        return menuDao.getAll();
    }
    public Menu getMenuById(int id){
        return menuDao.getMenuById(id);
    }
    public void deleteById(int id){
        menuDao.deleteByPrimaryKey(id);
    }
    public void save(Menu menu){
        menuDao.insert(menu);
    }
    public void updateMenus(Menu menu){
        menuDao.updateByPrimaryKey(menu);
    }
}
