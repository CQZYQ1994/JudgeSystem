package com.judgesystem.dao;

import com.judgesystem.entity.UserRole;
import org.apache.ibatis.annotations.Param;

public interface UserRoleDao {
    int deleteByPrimaryKey(Integer id);
    int insert(UserRole record);
    int insertSelective(UserRole record);
    UserRole selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(UserRole record);
    int updateByPrimaryKey(UserRole record);
    void deleteByUserId(Integer hrid);
    Integer addRole(@Param("user_id") Integer user_id, @Param("role_id") Integer[] role_ids);
}
