package com.judgesystem.dao;

import com.judgesystem.entity.Role;

import java.util.List;

public interface RoleDao {
    public List<Role> findById(int id);
    public List<Role> findAll();
    int deleteByPrimaryKey(Integer id);

   /* int insert(Role record);
    int insertSelective(Role record);
    Role selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(Role record);
    int updateByPrimaryKey(Role record);
    List<Role> getAllRoles();*/
}
