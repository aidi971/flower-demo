package com.example.demo.dao;

import com.example.demo.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface RoleDao extends JpaRepository<Role,Integer> {
}
