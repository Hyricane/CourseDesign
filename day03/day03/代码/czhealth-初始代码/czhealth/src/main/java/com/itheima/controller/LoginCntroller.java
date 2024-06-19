package com.itheima.controller;


import com.itheima.entity.Result;
import com.itheima.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginCntroller {

    @Autowired
    LoginService loginService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @PostMapping("/login.do")
    public void login(String username,String password) throws IOException {
//        处理业务逻辑 service层 controller调用service层
        Result result = loginService.login(username,password);
        if(result.isFlag()){
            HttpSession session = request.getSession();
            session.setAttribute("user",result.getData());
        }else{
            response.sendRedirect("login.html");
        }
        response.sendRedirect("pages/main.html");
    }
}
