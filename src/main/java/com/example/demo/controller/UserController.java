package com.example.demo.controller;

import com.example.demo.pojo.Article;
import com.example.demo.pojo.Role;
import com.example.demo.pojo.User;
import com.example.demo.respone.Response;
import com.example.demo.service.CourseService;
import com.example.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;




//    @Autowired
//    CourseService courseService;

    @CrossOrigin
    @PostMapping("api/register")
    public String Register(@RequestBody User user) {
        String username = user.getUsername();
        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);
        String password = user.getPassword();

        boolean isExist = userService.isExist(username);
        if (isExist) {
            return "用户已存在";
        }
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String algorithm = "md5";
        String pwdAfterHash = new SimpleHash(algorithm, password, salt, times).toString();
        user.setSalt(salt);
        user.setPassword(pwdAfterHash);
        user.setRoleId(1);
        userService.addUser(user);
        return "成功";
    }

    @CrossOrigin
    @PostMapping("api/login")
    public Response login(@RequestBody User user) {
        String username = user.getUsername();
        User user1 = userService.getUserByUsername(username);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, user.getPassword());
        try {
            subject.login(usernamePasswordToken);
            return new Response(200, "success", user1);
        } catch (AuthenticationException e) {
            return new Response(500, "fail", null);
        }
    }
    @CrossOrigin
    @PostMapping("api/admin/login")
    public Response adminLogin(@RequestBody User user) {
        String username = user.getUsername();
        User user1 =userService.getUserByUsername(username);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, user.getPassword());
        if (user1.getRoleId().equals(2)) {
            try {
                subject.login(usernamePasswordToken);
                System.out.println(user1);
                return new Response(200, "success", user1);
            } catch (AuthenticationException e) {
                return new Response(500, "fail", null);
            }
        } else {
            return new Response(500, "权限不足", null);

        }
    }

    @CrossOrigin
    @GetMapping("/api/logout")
    public Response logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new Response(200, "登出", null);
    }

    @CrossOrigin
    @PostMapping("/api/user/update/by/{username}")
    public Response updateByUsername(@RequestBody User requestUser, @PathVariable String username) {
        User user = userService.getUserByUsername(username);
        System.out.println(username);
        System.out.println(user);
        user.setUsername(requestUser.getUsername());
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String algorithm = "md5";
        String pwdAfterHash = new SimpleHash(algorithm, requestUser.getPassword(), salt, times).toString();
        user.setSalt(salt);
        user.setPassword(pwdAfterHash);
        user.setPhone(requestUser.getPhone());
        boolean isExist = userService.isExist(requestUser.getUsername());
        if (!isExist) {
            userService.addUser(user);
            return new Response(200, "修改成功", "null");
        } else {

            return new Response(500, "修改失败", "null");
        }
    }
    @CrossOrigin
    @PostMapping("/api/user/update/{id}")
    public Response updateById(@RequestBody User requestUser, @PathVariable int id) {
        User user = userService.getById(id);
        user.setUsername(requestUser.getUsername());
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String algorithm = "md5";
        String pwdAfterHash = new SimpleHash(algorithm, requestUser.getPassword(), salt, times).toString();
        user.setSalt(salt);
        user.setPassword(pwdAfterHash);
        user.setPhone(requestUser.getPhone());
        user.setBirthday(requestUser.getBirthday());
        user.setRoleId(requestUser.getRoleId());
        boolean isExist = userService.isExist(requestUser.getUsername());
        if (!isExist) {
            userService.addUser(user);
            return new Response(200, "修改成功", "null");
        } else {

            return new Response(500, "修改失败", "null");
        }
    }


    @CrossOrigin
    @GetMapping("/api/delete/{id}")
    public Response delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return new Response(200, "删除成功", null);
    }

    @CrossOrigin
    @GetMapping("/api/user")
    public List<User> getArticleList() {
        return userService.getAllUser();
    }

    @CrossOrigin
    @PostMapping("api/user/add")
    public Response add(@RequestBody User requestUser) {
        User user = new User();
        boolean isExist = userService.isExist(requestUser.getUsername());
        if (isExist) {
            return new Response(500, "添加失败", "null");
        } else {

            user.setUsername(requestUser.getUsername());
            String salt = new SecureRandomNumberGenerator().nextBytes().toString();
            int times = 2;
            String algorithm = "md5";
            String pwdAfterHash = new SimpleHash(algorithm, requestUser.getPassword(), salt, times).toString();
            user.setSalt(salt);
            user.setPassword(pwdAfterHash);
            user.setPhone(requestUser.getPhone());
            user.setBirthday(requestUser.getBirthday());
            user.setRoleId(requestUser.getRoleId());
            userService.addUser(user);
            return new Response(200, "添加成功", "null");
        }
    }

}
