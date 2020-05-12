package com.example.demo.controller;

import com.example.demo.pojo.Article;
import com.example.demo.pojo.User;
import com.example.demo.respone.Response;
import com.example.demo.service.ArticleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;


    @CrossOrigin
    @GetMapping("/api/article")
    public List<Article> getArticleList(){
        return articleService.getAllArticle();
    }

    @CrossOrigin
    @GetMapping("/api/article/search")
    public List<Article> getArticleByName(@RequestBody String name){
        return articleService.getAricleByName(name);
    }

    @CrossOrigin
    @PostMapping("/api/article/add")
    public Response addArticle(@RequestBody Article requestArticle) {
        Article article = new Article();
        Subject subject = SecurityUtils.getSubject();
        article.setName(requestArticle.getName());
        article.setAbs(requestArticle.getAbs());
        article.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        article.setAuthor((User)subject.getPrincipal());
        article.setContentHtml(requestArticle.getContentHtml());
        article.setContentMd(requestArticle.getContentMd());
        articleService.addArticle(article);
        return new Response(200,"成功",article);
    }
    @CrossOrigin
    @PostMapping("/api/article/update/{id}")
    public Response updateArticle(@RequestBody Article requestArticle ,@PathVariable("id") int id) {
        Article article = articleService.getById(id);
        Subject subject = SecurityUtils.getSubject();
        article.setName(requestArticle.getName());
        article.setAbs(requestArticle.getAbs());
        article.setContentHtml(requestArticle.getContentHtml());
        article.setContentMd(requestArticle.getContentMd());
        article.setAuthor((User)subject.getPrincipal());
        article.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
        articleService.addArticle(article);
        System.out.println(article);
        return new Response(200, "成功", null);
    }

    @CrossOrigin
    @GetMapping("/api/article/delete/{id}")
    public Response deleteArticle(@PathVariable("id") int id){
        articleService.deleteById(id);
        return new Response(200,"成功",null);
    }

    @CrossOrigin
    @GetMapping("/api/article/{id}")
    public Response getArticleById(@PathVariable("id") int id){
        Article article = articleService.getById(id);
        return new Response(200,"成功",article);
    }

    final static String PIC_PATH = "static/pics/";
    @CrossOrigin
    @PostMapping("/api/pic")
//    上传图片
    public Response uploadPic(MultipartHttpServletRequest pic, HttpServletRequest req){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");
        String datePrefix = dateFormat.format(new Date());
        String savePath ="src/main/resources/"+PIC_PATH;

        File floder = new File(savePath+datePrefix);
        if (!floder.isDirectory()){
            floder.mkdirs();
        }

        String orignalName = pic.getFile("image").getOriginalFilename();
        String saveName = UUID.randomUUID().toString()+orignalName.substring(orignalName.lastIndexOf("."),orignalName.length());
        String absolutePath = floder.getAbsolutePath();

        try {
            File fileToSave = new File(absolutePath+ File.separator +saveName);
            pic.getFile("image").transferTo(fileToSave);
            String returnPath = req.getScheme()+"://"
                    +req.getServerName()+":"+req.getServerPort()
                    +"/article/image/"
                    + datePrefix +"/"+saveName;
            return new Response(200,"上传成功",returnPath);

        }catch (Exception e){
            e.printStackTrace();
        }
        return new Response(500,"上传失败",null);
    }
}
