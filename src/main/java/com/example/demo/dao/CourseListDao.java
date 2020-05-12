package com.example.demo.dao;

import com.example.demo.pojo.CourseList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CourseListDao extends JpaRepository<CourseList,Integer> {

  @Query("update CourseList set status = 0 where id=?1")
  @Modifying
  void cancel(int id);

  CourseList findByCourseIdAndUserId(int courseId, int userId);

//  @Query("select coursename from CourseList where username=?1")
//   CourseList findCourseListByUsername(String username);
@Query("select id from CourseList where courseId=?1 and userId =?2")
int find(int courseId,int userId);


  @Query("select courseId from CourseList where userId = ?1")
  List<Integer> findCourseListByUserId(int userId);
}
