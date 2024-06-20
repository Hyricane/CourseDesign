package com.itheima.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.mapper.CheckitemMapper;
import com.itheima.pojo.CheckItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckitemServiceImpl implements CheckitemService{
//    controller调用service调用mapper

    @Autowired
    CheckitemMapper checkitemMapper;

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        int currentPage = queryPageBean.getCurrentPage();
        int pageSize = queryPageBean.getPageSize();
//        创建页码对象
        IPage<CheckItem> page = new Page<>(currentPage,pageSize);
//        创建条件对象
        LambdaQueryWrapper<CheckItem> wrapper = new LambdaQueryWrapper<>();
        String queryString = queryPageBean.getQueryString();
        if (queryPageBean !=null){
//        若前端条件不为空 构建对应的 wrapper 对象
            wrapper.eq(CheckItem::getCode,queryString)
                    .or()
                    .like(CheckItem::getName,queryString);

        }else{
            wrapper = null;
        }
//          调用selectPage方法
        IPage<CheckItem> selectPage = checkitemMapper.selectPage(page, wrapper);
//            返回封装数据即可


        return new PageResult(selectPage.getTotal(),selectPage.getRecords());
    }
}
