/*
package com.judgesystem.service;

import com.judgesystem.dao.RoleDao;
import com.judgesystem.dao.UserDao;
import com.judgesystem.entity.Role;
import com.judgesystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserDao userDao;
    @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user=userDao.loadUserByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException("账号不存在");
        }
        user.setRoles(userDao.getUserRolesByUid(user.getId()));
        return user;
   }

}

*/
