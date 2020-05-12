package com.example.demo.dao;

import com.example.demo.pojo.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface PlantDao extends JpaRepository<Plant, String> {
    void deleteById(int id);

    Plant findById(int id);
}
