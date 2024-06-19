package com.itheima.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.itheima.entity.Result;
import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service

public class LoginServiceImpl implements LoginService{
    @Autowired
    UserMapper userMapper;

    @Override
    public Result login(String username, String password) {
        if (username == null || password == null){
            return new Result(false,"用户名或密码不能为空");
        }

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>();
        queryWrapper.eq(User::getUsername,username);

        User user = userMapper.selectOne(queryWrapper);

        if (user == null){
            return new Result(false,"用户名不存在");
        }
        String digest = DigestUtils.md5DigestAsHex(password.getBytes());
        String userPassword = user.getPassword();
        if (!digest.equals(userPassword)){
            return new Result(false, "密码不正确");
        }
        return new Result(true,"登录成功",user);
    }
}
