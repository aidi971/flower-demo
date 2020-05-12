package com.example.demo.dao;

import com.example.demo.pojo.Article;
import com.example.demo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ArticleDao extends JpaRepository<Article, Integer> {


     void deleteById(int id);

    @Query(value = "select a from Article a where a.name like %?1%")
    List<Article> findByNameLike(String name);

    List<User>findByAuthor(User user);
}
