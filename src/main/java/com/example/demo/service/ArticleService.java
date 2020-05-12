package com.example.demo.service;


import com.example.demo.dao.ArticleDao;
import com.example.demo.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleDao articleDao;

    public List<Article>getAllArticle(){
        return articleDao.findAll();
    }
    public List<Article>getAricleByName(String name){
        return articleDao.findByNameLike(name);
    }

    public void addArticle(Article article){
        articleDao.save(article);
    }

    public Article getById(int id){
       return articleDao.findById(id).get();
    }

    public void deleteById(int id){
        articleDao.deleteById(id);
    }
}
