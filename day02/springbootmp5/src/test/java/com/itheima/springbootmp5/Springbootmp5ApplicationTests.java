package com.itheima.springbootmp5;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.injector.methods.SelectPage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.springbootmp5.mapper.UserMapper;
import com.itheima.springbootmp5.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class Springbootmp5ApplicationTests {

    @Autowired
    private UserMapper userMapper;
    /**
     * 根据id查询
     */

    @Test
    public void testSelectById() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    @Test
    public void test2(){
            User user = new User();
            user.setUserName("zhangsan");
            user.setPassword("123456");
            user.setAge(18);
            user.setName("蔡徐坤");
            int insert = userMapper.insert(user);
            System.out.println(insert);

    }

    @Test
    public void test3(){
        int delete = userMapper.deleteById(8);
        System.out.println(delete);

    }
    @Test
    public void test4(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(6);
        list.add(7);
        int batchIds = userMapper.deleteBatchIds(list);
        System.out.println(batchIds);
    }

    @Test
    public void test5(){
        User user = new User();
        user.setId(5L);
        user.setUserName("zhangsan");
        user.setName("蔡徐坤");
        int update = userMapper.updateById(user);
        System.out.println(update);
    }

    @Test
    public void test6(){
        int current = 1;
        int size = 3;
        // 用IPage对象
        IPage<User> page = new Page<>(current,size);
        // ctrl +　alt + v 补全前面类型代码
        // selectPage 方法 是分页查询的方法
        IPage<User> selectPage = userMapper.selectPage(page, null);
        // 包含几条数据
        System.out.println(selectPage.getRecords()); //当前页码包含的数据有哪些
        // 总记录数
        System.out.println(selectPage.getTotal());

    }
//or条件构造器 构造器案例
    @Test
    public void test7(){
        //条件构造器对象
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //select * from tb_user where user_name = "lisi" and age >18;
        wrapper.eq("user_name","lisi").gt("age",18);
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }

    @Test
    public void test8(){
        //select * from tb_user user_name = "lisi or age >18;
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name","lisi")
                .or().gt("age",18)
                .or()
                .in("name","蔡徐坤","张三");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }

//    查询以张开头的所有user信息
    @Test
    public void test9(){
        //select * from tb_user where name like "张%";
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.likeRight("name","张");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);

    }

//    查询 user_name="lisi" and age <23 按照年龄进行降序
    @Test
    public void test10(){
//        条件构造器对象
        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        select * from tb_user where user_name="lisi" or age <23 order by age desc
        wrapper.eq("user_name","lisi")
                .or()
                .lt("age",23)
                .orderByDesc("age");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }

//返回指定列信息
    @Test
    public void test11(){
        //创建 wrapper条件构造器对象
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name","lisi")
                .or()
                .lt("age",23)
                .orderByDesc("age")
                .select("id","name","user_name");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }

//    按照某种条件进行分页查询
    @Test
    public void test12(){
        int current =1;
        int size=3;
//        创建page对象
        IPage<User> page = new Page<>(current,size);
//        构建查询条件
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lt("age",23);
        IPage<User> selectPage = userMapper.selectPage(page,wrapper);
        System.out.println(selectPage.getRecords());
        System.out.println(selectPage.getTotal());
    }

//    使用lamdaQueryWrapper 消除硬编码
    @Test
    public void test13(){
        int current =1;
        int size=3;
//        创建page对象
        IPage<User> page = new Page<>(current,size);
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.lt(User::getAge,23);//User :: 代表方法的引用,方法引用代替了 age字段硬编码的问题
        IPage<User> selectPage = userMapper.selectPage(page, lambdaQueryWrapper);
        System.out.println(selectPage.getRecords());
        System.out.println(selectPage.getTotal());
    }

//    删除
    @Test
    public void test14(){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getName,"蔡徐坤");
//        delete from tb_user where (name=?)
        int delete = userMapper.delete(queryWrapper);
        System.out.println(delete);
    }

//    修改lisi密码为222222
    @Test
    public void test15(){
//        更新条件构造器
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
//        eq指where后面的条件
//        update tb_user set password = "22222222" where user_name ="lisi";
        wrapper.eq("user_name","lisi")
                .set("password","22222222");
        int update = userMapper.update(wrapper);
        System.out.println(update);
    }
}

