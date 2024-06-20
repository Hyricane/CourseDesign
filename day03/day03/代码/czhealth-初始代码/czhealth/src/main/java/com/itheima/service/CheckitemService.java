package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckItem;

public interface CheckitemService {
    PageResult findPage(QueryPageBean queryPageBean);

    Result add(CheckItem checkItem);

    Result delete(Integer id);

    Result findById(Integer id);

    Result edit(CheckItem checkItem);
}
