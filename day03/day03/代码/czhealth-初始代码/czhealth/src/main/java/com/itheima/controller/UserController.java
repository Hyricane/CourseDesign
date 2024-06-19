package com.itheima.controller;

import com.itheima.entity.Result;
import com.itheima.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    HttpServletRequest request;

    @GetMapping("/getUsername.do")
    public Result getUsername(){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null){
            return new Result(false,"请先登录");
        }
        return new Result(true,"获取用户名成功",user.getUsername());
    }
}
