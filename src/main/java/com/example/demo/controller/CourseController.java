package com.example.demo.controller;

import com.example.demo.pojo.Course;
import com.example.demo.pojo.CourseList;
import com.example.demo.pojo.User;
import com.example.demo.respone.Response;
import com.example.demo.service.CourseListService;
import com.example.demo.service.CourseService;
import com.example.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {

    @Autowired
    CourseService courseService;
    @Autowired
    UserService userService;
    @Autowired
    CourseListService courseListService;

    @CrossOrigin
    @GetMapping("/api/findall/course/")
    public List<Course> getCousreList() {
        return courseService.getAllCourse();
    }

    @CrossOrigin
    @PostMapping("/api/update/course/{id}")
    public Response updateCourseInfo(@RequestBody Course requestCourse, @PathVariable("id") int id) {
        Course course = courseService.findById(id);
        course.setName(requestCourse.getName());
        course.setNum(requestCourse.getNum());
        course.setContentHtml(requestCourse.getContentHtml());
        course.setContentMd(requestCourse.getContentMd());
        course.setPrice(requestCourse.getPrice());
        course.setDescription(requestCourse.getDescription());
        course.setPic(requestCourse.getPic());
        courseService.updateCourse(course);
        return new Response(200, "成功", course);
    }

    @CrossOrigin
    @PostMapping("/api/add/course/")
    public Response addNote(@RequestBody Course requestCourse) {
        Course course = new Course();
        course.setName(requestCourse.getName());
        course.setNum(requestCourse.getNum());
        course.setContentHtml(requestCourse.getContentHtml());
        course.setContentMd(requestCourse.getContentMd());
        course.setPrice(requestCourse.getPrice());
        course.setDescription(requestCourse.getDescription());
        course.setPic(requestCourse.getPic());
        courseService.updateCourse(course);
        return new Response(200, "成功", course);
    }

    @CrossOrigin
    @PostMapping("/api/delete/course/{id}")
    public Response deleteCourse(@PathVariable("id") int id) {
        courseService.deleteCourse(id);
        return new Response(200, "成功", null);
    }

    @CrossOrigin
    @PostMapping("/api/find/course/{id}")
    public Response findCourseById(@PathVariable("id") int id) {
        Course course = courseService.findById(id);
        return new Response(200, "成功", course);
    }

    @CrossOrigin
    @PostMapping("/api/buy/course/{id}/{username}")
    public Response buyCourse(@PathVariable("id") int id, @PathVariable("username") String username) {
        Course course = courseService.findById(id);
        CourseList courseList = new CourseList();
        User user = userService.getUserByUsername(username);
        Subject subject = SecurityUtils.getSubject();
        boolean isBuy = courseListService.isBuy(id, user.getId());
        System.out.println(username);
        System.out.println(course.getNum());
        System.out.println(user);
        int coin = userService.getCoin(username);
        int price = courseService.getPrice(id);
        if (price > coin) {
            return new Response(500, "余额不足", null);
        } else if (isBuy) {
            return new Response(501, "你已购买该课程！臭弟弟！", null);
        } else if (course.getNum().equals(0)) {
            return new Response(502, "没库存了", null);
        } else {
            courseList.setCourseId(id);
            courseList.setUserId(user.getId());
            user.setCoin(coin - price);
            course.setNum(course.getNum() - 1);
            courseListService.save(courseList);
            userService.save(user);
            courseService.save(course);
            return new Response(200, "购买成功", courseList);
        }
    }

    @CrossOrigin
    @PostMapping("/api/cancel/course/{id}/{username}")
    public Response cancelCourse(@PathVariable("id") int id, @PathVariable("username") String username) {
        Course course = courseService.findById(id);
        CourseList courseList = new CourseList();

//        Subject subject = SecurityUtils.getSubject();
//        String username=subject.getPrincipal().toString();
        User user = userService.getUserByUsername(username);
       int courseListId = courseListService.findId(id,user.getId());
        System.out.println(user);
        int coin = userService.getCoin(username);
        int price = courseService.getPrice(id);
        user.setCoin(coin + price);
        course.setNum(course.getNum() + 1);
        courseListService.delete(courseListId);
        userService.save(user);
        courseService.save(course);
        return new Response(200, "退款成功", null);
    }

    @CrossOrigin
    @PostMapping("/api/find/buy/course/{username}")
    public Response findBuyCourse(@PathVariable("username") String username) {
        User user = userService.getUserByUsername(username);
        List<Integer> courseId = courseListService.getCourseId(user.getId());
        List<Course> list = new ArrayList<Course>();
        for (int cid : courseId) {
            list.add(courseService.findById(cid));
        }
        return new Response(200, "查询成功", list);
    }


//        User user = userService.getUserByUsername(username);
//    List<Integer> courseId = courseListService.getCourseId(user.getId());
//    int[] arr1 = courseId.stream().mapToInt(Integer::valueOf).toArray();
//    List<Object[]> list = new ArrayList<Object[]>();
//    Object[]objects = new Object[arr1.length];
//        for (int i=0;i<arr1.length;i++){
//        objects[i] = courseService.findById(arr1[i]);
//        list.add(objects);
//
//    }
//        System.out.println(list);
//        return new Response(200, "查询成功",list );
//}
}

