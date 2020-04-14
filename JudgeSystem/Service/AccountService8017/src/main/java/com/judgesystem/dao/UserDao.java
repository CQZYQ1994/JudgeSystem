package com.judgesystem.dao;

import com.judgesystem.entity.User;

import java.util.List;

public interface UserDao {
    public List<User> findAll(int index, int limit);
    public int count();
    public User findById(int id);
    public void save(User user);
    public void update(User user);
    public void deleteById(int id);
    public User login(String account,String password);

}
