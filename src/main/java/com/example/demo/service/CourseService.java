package com.example.demo.service;


import com.example.demo.dao.CourseDao;
import com.example.demo.pojo.Course;
import com.example.demo.pojo.Plant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseDao courseDao;


    public void addCourse(Course course){
        courseDao.save(course);
    }

    public void deleteCourse(int id){
        courseDao.deleteById(id);
    }
    public List<Course> getAllCourse(){
        return courseDao.findAll();
    }

    public void updateCourse(Course course){
        courseDao.save(course);
    }
    public Course findById(int id){
       return courseDao.findById(id);
    }

    public List<Object[]> info(String name){
        return courseDao.findByNameContaining(name);
    }

    public int getPrice(int id){
        return courseDao.findPriceById(id);
    }

    public void save(Course course) {
    courseDao.save(course);
    }
}
