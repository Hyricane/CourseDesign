package com.itheima.controller;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        @PostMapping("/add.do")
        public Result add(@RequestBody CheckItem checkItem){
            return checkitemService.add(checkItem);
        }
        @GetMapping("/delete.do")
        public Result delete(Integer id){
            return checkitemService.delete(id);
        }

        @GetMapping("/findById.do")
        public Result findById(Integer id){
            return checkitemService.findById(id);
        }

        @PostMapping("/edit.do")
        public Result edit(@RequestBody CheckItem checkItem){
            return checkitemService.edit(checkItem);
        }
}
