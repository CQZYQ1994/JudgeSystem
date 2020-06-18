package com.judgesystem.service;

import com.judgesystem.dao.RoleDao;
import com.judgesystem.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleService {
    @Autowired
    RoleDao roleDao;
    public List<Role> findAll(){
        return roleDao.findAll();
    }
    public Role findById(int id){
        return roleDao.findById(id);
    }
    public void updateRole(Role role){
        roleDao.updateRole(role);
    }
    public void insertRole(Role role){
        roleDao.insertRole(role);
    }
    public void deleteRole(int id){
        roleDao.deleteByPrimaryKey(id);
    }
}
