package com.example.demo.service;

import com.example.demo.dao.CourseDao;
import com.example.demo.dao.CourseListDao;
import com.example.demo.dao.UserDao;
import com.example.demo.pojo.CourseList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseListService {
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CourseListDao courseListDao;

    public void findAllById(){
        courseListDao.findAll();
    }


    public void cancel(int id) {
        courseListDao.cancel(id);
    }

    public boolean isBuy(int courseId,int userId){
        CourseList courseList = courseListDao.findByCourseIdAndUserId(courseId,userId);
        return courseList !=null;
    }

//    public CourseList findByUsername(String username){
//        return courseListDao.findCourseListByUsername(username);
//    }

//    public CourseList findByUserId(int userId) {
//        return courseListDao.findCourseListByUserId(userId);
//    }

    public List<Integer> getCourseId(Integer id) {
        return courseListDao.findCourseListByUserId(id);
    }

    public void save(CourseList courseList) {
        courseListDao.save(courseList);
    }
    public int findId(int courseId,int userId){
        return courseListDao.find(courseId,userId);
    }

    public void delete(int id) {
        courseListDao.deleteById(id);
    }
}
