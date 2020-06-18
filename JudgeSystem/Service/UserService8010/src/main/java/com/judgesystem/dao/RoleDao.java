package com.judgesystem.dao;

import com.judgesystem.entity.Role;

import java.util.List;

public interface RoleDao {
    public Role findById(int id);
    public List<Role> findAll();
    public void deleteByPrimaryKey(Integer id);
    public void updateRole(Role role);
    public void insertRole(Role role);

   /* int insert(Role record);
    int insertSelective(Role record);
    Role selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(Role record);
    int updateByPrimaryKey(Role record);
    List<Role> getAllRoles();*/
}
