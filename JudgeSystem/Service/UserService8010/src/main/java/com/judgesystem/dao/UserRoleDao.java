package com.judgesystem.dao;

import com.judgesystem.entity.UserRole;


public interface UserRoleDao {
    public void deleteByUserId(int id);
    public void save(UserRole userRole);
    public UserRole selectByUserId(int id);
    public void updateByUserId(UserRole userRole);
    public void updateByUserRoleId(UserRole userRole);

}
