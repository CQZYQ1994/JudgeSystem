package com.judgesystem.service;

import com.judgesystem.dao.MenuDao;
import com.judgesystem.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    MenuDao menuDao;
    public List<Menu> gerMenusByUserId(int UserId){
        return menuDao.getMenusByUserId(UserId);
    }
}
