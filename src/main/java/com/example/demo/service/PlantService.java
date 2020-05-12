package com.example.demo.service;

import com.example.demo.dao.PlantDao;
import com.example.demo.pojo.Plant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;

@Service
public class PlantService {
    @Autowired
    PlantDao plantDao;

    public void addPlant(Plant plant){
        plantDao.save(plant);
    }

    public void deletePlant(int id){
        plantDao.deleteById(id);
    }
    public List<Plant>getAllPlant(){
       return plantDao.findAll();
    }

    public void updatePlant(Plant plant){
        plantDao.save(plant);
    }
    public Plant findById(int id){
       return plantDao.findById(id);
    }
}
