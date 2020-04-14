package com.judgesystem.dao;

import com.judgesystem.entity.Role;

import java.util.List;

public interface RoleDao {
    public List<Role> findById(int id);
    public List<Role> findAll();
}
