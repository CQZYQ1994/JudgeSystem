package com.judgesystem.dao;

import com.judgesystem.entity.MenuRole;
import org.apache.ibatis.annotations.Param;

public interface MenuRoleDao {
    int deleteByPrimaryKey(int id);
    int insert(MenuRole record);
    int insertSelective(MenuRole record);
    MenuRole selectByPrimaryKey(int id);
    int updateByPrimaryKeySelective(MenuRole record);
    int updateByPrimaryKey(MenuRole record);
    void deleteByRid(int rid);
    Integer insertRecord(@Param("role_id") int role_id, @Param("menu_ids") int[] menu_ids);
}
