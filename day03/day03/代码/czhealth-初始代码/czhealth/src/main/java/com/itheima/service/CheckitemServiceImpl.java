package com.itheima.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.injector.methods.Insert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
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
        if (queryString !=null){
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

    @Override
    public Result add(CheckItem checkItem) {
        int insert = checkitemMapper.insert(checkItem);
        if (insert <=0){
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
        return new Result(true,MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    @Override
    public Result delete(Integer id) {

        return null;
    }

    @Override
    public Result findById(Integer id) {
        CheckItem checkItem = checkitemMapper.selectById(id);
        if (checkItem == null){
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }
        return  new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS);
    }

    @Override
    public Result edit(CheckItem checkItem) {
        int update = checkitemMapper.updateById(checkItem);
        if(update<=0){
            return new Result(false,MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
        return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }
}
