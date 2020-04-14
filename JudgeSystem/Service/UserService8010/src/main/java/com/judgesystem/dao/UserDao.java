package com.judgesystem.dao;

import com.judgesystem.entity.Role;
import com.judgesystem.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    public List<User> findAll(int index,int limit);
    public int count();
    public User findById(int id);
    public void save(User user);
    public void update(User user);
    public void deleteById(int id);
    public User login(String username,String password);
    public User loadUserByUsername(String username);
    public List<Role> getUserRolesByUid(int id);

 /*   int deleteByPrimaryKey(int id);
    int insert(User record);
    int insertSelective(User record);
    User selectByPrimaryKey(int id);
    int updateByPrimaryKeySelective(User record);
    int updateByPrimaryKey(User record);
    List<Role> getUserRolesById(int id);
    List<User> getAllUser(@Param("user_id") int urid, @Param("keywords") String keywords);
    List<User> getAllUserExceptCurrentHr(int id);
    Integer updatePasswd(@Param("hrid") Integer hrid, @Param("encodePass") String encodePass);
    Integer updateUserface(@Param("url") String url, @Param("id") Integer id);*/

}
