package com.itheima.controller;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.service.CheckitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkitem")
public class CheckitemController {
    @Autowired
    CheckitemService checkitemService;

        @PostMapping("/findPage.do")
        public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
//            alt+enter
            return checkitemService.findPage(queryPageBean);

    }
}
