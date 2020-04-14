package com.judgesystem.dao;

import com.judgesystem.entity.Menu;

import java.util.List;

public interface MenuDao {
   public List<Menu> getMenusByUserId(int UserId);
    int deleteByPrimaryKey(int id);
    int insert(Menu record);
    int insertSelective(Menu record);
    Menu selectByPrimaryKey(int id);
    int updateByPrimaryKeySelective(Menu record);
    int updateByPrimaryKey(Menu record);
    List<Menu> getAllMenusWithRole(String role);
    List<Menu> getAllMenus();
    List<Integer> getMidsByRid(int roleId);
}
