
package com.judgesystem.service;


import com.judgesystem.dao.UserDao;
import com.judgesystem.dao.UserRoleDao;
import com.judgesystem.entity.Role;
import com.judgesystem.entity.User;
import com.judgesystem.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserService /*implements UserDetailsService*/ {
 /*   @Autowired
    UserDao userDao;
    @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user=userDao.loadUserByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException("账号不存在");
        }
        user.setRoles(userDao.getUserRolesByUid(user.getId()));
        return user;   }*/
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;

    public List<User> findAllUser(){
         return userDao.findAll();
     }

    public int count(){
        return userDao.count();
    }


    public User findById(int id){
        return userDao.findById(id);
    }
    public List<User> findUserByName(String name){
        return userDao.findUserByName(name);
    }
    public List<User> findUserByRole(String role){
        return userDao.findUserByRole(role);
    }

    public int addUser(List<User> list){
        return userDao.addUser(list);
    }
    public void save(User user){
        userDao.save(user);
        UserRole userRole=new UserRole();
        Role role=user.getRole();
        User user1=userDao.loadUserByUsername(user.getUsername());
        userRole.setRole_id(role.getId());
        userRole.setUser_id(user1.getId());
        userRoleDao.save(userRole);
    }


    public void update(User user){
        userDao.update(user);
    }
    public void updateInformation(User user){
        userDao.updateUserInformation(user);
    }

    public void deleteById(int id){
        userDao.deleteById(id);
    }

    public void saveUserRole(UserRole userRole){
        userRoleDao.save(userRole);
    }

    public void deleteUserRoleByUserId(int id){
        userRoleDao.deleteByUserId(id);
    }

    public void updateUserRoleByUserId( UserRole userRole){
        userRoleDao.updateByUserId(userRole);
    }

    public List<User> getAllUserExceptCurrentUser(int id){
        return userDao.getAllUserExceptCurrentUser(id);
    }
    public User login(String username,String password){
        return userDao.login(username,password);
    }

}


