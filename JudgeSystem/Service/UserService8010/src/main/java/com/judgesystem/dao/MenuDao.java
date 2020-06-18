package com.judgesystem.dao;

import com.judgesystem.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MenuDao {
    public List<Menu> getMenusByUserId(int UserId);
    public Menu getMenuById(int id);
    void deleteByPrimaryKey(int id);
    void insert(Menu record);
    void insertSelective(Menu record);
    Menu selectByPrimaryKey(int id);
    void updateByPrimaryKeySelective(Menu record);
    public void updateByPrimaryKey(Menu record);
    List<Menu> getAllMenusWithRole(String role);
    List<Menu> getAllMenus();
    List<Menu> getAll();
    List<Integer> getMidsByRid(int roleId);
}
