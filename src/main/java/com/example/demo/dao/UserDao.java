package com.example.demo.dao;

import com.example.demo.pojo.Course;
import com.example.demo.pojo.Role;
import com.example.demo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UserDao extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    void deleteById(int id);

    @Query("select coin from User where username=?1")
    int findCoinByName(String username);

    @Query("update User set coin = coin+i where username=?1")
    @Modifying
    int addCoin(int i,String username);
}