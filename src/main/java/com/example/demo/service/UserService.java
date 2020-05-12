package com.example.demo.service;

import com.example.demo.dao.RoleDao;
import com.example.demo.dao.UserDao;
import com.example.demo.pojo.Role;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.Subject;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    //    @Autowired
//    CourseDao courseDao;


    public User getUserByUsername(String userName) {
        return userDao.findByUsername(userName);
    }

    public boolean isExist(String username) {
        User user = getUserByUsername(username);
        return user != null;
    }


    public void addUser(User user) {
        userDao.save(user);
    }


    public void updateUser(User user) {
        userDao.save(user);
    }

    public void deleteUser(int id) {
        userDao.deleteById(id);

    }
    public int getCoin(String username){
       return userDao.findCoinByName(username);
    }

    public List<User> getAllUser() {
        return userDao.findAll();
    }

    public User getById(int id) {
        return userDao.findById(id).get();
    }

    public void addCoin(int i,String username) {

    }

    public void save(User user) {
        userDao.save(user);
    }
}
