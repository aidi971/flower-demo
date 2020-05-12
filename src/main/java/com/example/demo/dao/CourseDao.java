package com.example.demo.dao;

import com.example.demo.pojo.Course;
import com.example.demo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
public interface CourseDao extends JpaRepository<Course, String> {
    void deleteById(int id);

    Course findById(int id);


    @Query(nativeQuery = true,value = "select c.id,c.name, GROUP_CONCAT(u.username)as userName from course c,user u,course_list cl"
            +"where c.id = cl.course_id and u.id = cl.uesr_id and c.name like ?1 group by c.id,c.name")

    List<Object[]>findByNameContaining(String name);

    @Query("select price from Course where id=?1")
    int findPriceById(int id);

    @Query(nativeQuery = true, value = "select c.name,c.price,GROUP_CONCAT(u.username)as userName from course c,user u,course_list cl"
            + "where u.id = cl.user_id and c.id = cl.course_id where u.id =?1")
    List<Object[]> getByUserId(int id);

}
