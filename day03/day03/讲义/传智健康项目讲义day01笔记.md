

# 传智健康day01



### 1.1）项目概述

#### 1.1)能让你收获什么？

![](C:\Users\zhang\Desktop\讲义\img\企业微信截图_20230525171758.png)



### 1.2)项目课程大纲

| **章节**                                         | **概述**                                                     |
| ------------------------------------------------ | ------------------------------------------------------------ |
| day01-环境搭建、登录功能的实现，查询获得登录用户 | 项目介绍、环境搭建、初始工程创建、接口工具的使用、并完成登录功能。 |
| day02-预约管理-检查项管理。                      | 对检查项的分页查询，检查项的新增，编辑、删除相关功能的实现。 |
| day03-预约管理-检查组管理                        | 对检查组的分页查询，检查组的新增，编辑、删除等 功能的实现。  |
| day04-预约管理-检查套餐的管理                    | 对检查套餐的分页查询，检查套餐的新增，编辑，删除等功能的实现。 |

### 1.3)项目概述

随着医疗科技的快速成长，人们更加习惯于通过手机来进行预约挂号看病，各种机构也开始为了推广自己的产品套餐，也逐步的转型做互联网预约业务，方便人们利用碎片时间来获取信息，并且进行预约登记来进行线上处理，因此，对于微信客户端和 web后台网站的需求也越来越高。因此传智健康这个项目正是在这样背景下开发出来。传智健康项目采用当下火热的springboot+Mybatis-plus技术架构实现。本项目主要着手web后台网站的开发，

### 1.4)环境准备

①：项目依赖环境（需提前安装好）

- JDK1.8
- Intellij Idea
- maven-3.6.1
- mysql 5.7
- postman

②：项目工程的初始代码见详细资料直接zip压缩包解压之后，然后使用idea打开如下:

![](C:\Users\zhang\Desktop\讲义\img\8.png)



③：IDEA开发工具配置

![image-20210412141601018](D:/itcast/%E5%B0%B1%E4%B8%9A%E7%8F%AD%E8%B5%84%E6%96%99/%E5%AE%9E%E6%88%983%E5%A4%A9%E8%B5%84%E6%96%99/%E7%AC%AC%E4%B8%80%E5%A4%A9/%E8%AE%B2%E4%B9%89/01-%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA%E3%80%81SpringCloud%E5%BE%AE%E6%9C%8D%E5%8A%A1(%E6%B3%A8%E5%86%8C%E5%8F%91%E7%8E%B0%E3%80%81%E6%9C%8D%E5%8A%A1%E8%B0%83%E7%94%A8%E3%80%81%E7%BD%91%E5%85%B3).assets/image-20210412141601018-16463811882978.png)

设置本地仓库，**建议使用资料中提供好的仓库**

④：设置项目编码格式

![](C:\Users\zhang\Desktop\讲义\img\企业微信截图_20230529161633.png)

### 2.1  环境搭建

### 2.1.1）环境搭建-配置文件-导入静态资源

如下所示：将静态资源放入static文件夹中。

![](C:\Users\zhang\Desktop\讲义\img\8.png)

### 2.1.2）环境搭建-配置文件-导入配置文件如下：

导入application.yml文件

```yaml
server:
  port: 9001
spring:
  application:
    name: czhealth
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/health?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
    username: root
    password: root
mybatis-plus: # 作用：可以看到sql语句得执行
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl #仅控制台输出的日志
imgpath:
  name: D:\img\
```

### 2.1.3）环境搭建-maven项目搭建：

pom.xml文件中的依赖 管理如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.9.RELEASE</version>
        <relativePath/>
    </parent>

    <groupId>com.itheima</groupId>
    <artifactId>czhealth</artifactId>
    <version>1.0-SNAPSHOT</version>
    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.4.2</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.20</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.71</version>
        </dependency>
        <dependency>
            <groupId>commons-lang</groupId>
            <artifactId>commons-lang</artifactId>
            <version>2.6</version>
        </dependency>

        <dependency>
            <groupId>cn.afterturn</groupId>
            <artifactId>easypoi-spring-boot-starter</artifactId>
            <version>4.1.0</version>
        </dependency>
        <!-- apache 对 java io 的封装工具库 -->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-io</artifactId>
            <version>1.3.2</version>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-compress</artifactId>
            <version>1.20</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-log4j12 -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>2.0.0-alpha1</version>
        </dependency>


        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.1.10</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.46</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>2.4.5</version>
            </plugin>
        </plugins>
    </build>
</project>
```

### 2.1.4）环境搭建-测试项目是否启动成功。

测试启动项目，没有报错，环境搭建成功。

![](C:\Users\zhang\Desktop\讲义\img\9.png)



## 3. 登录功能实现

①：接口定义  

## 3.1)后台登录功能实现

| 请求url    | http://localhost:9001/login.do         |            |
| ---------- | -------------------------------------- | ---------- |
| 请求方式   | POST                                   |            |
| 请求参数   | username    password                   | 非JSON数据 |
| 请求返回值 | 如果登录成功跳转到 /page/main.html页面 | 页面跳转   |

请求参数：

| 参数名称 | 参数类型 | 描述           | 是否必须 |
| -------- | -------- | -------------- | -------- |
| username | String   | 用户登录用户名 | M        |
| password | String   | 用户登录密码   | M        |

返回值：无

```java
package com.itheima.controller;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.User;
import com.itheima.service.LoginService;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/login.do")
    public void login( String username, String password) throws IOException {
        //跳转页面
        // 1、@Controller类注解
        // 2、返回值类型为String
        // 3、成功 return "redirect:pages/main/html" ; 失败跳转回登录页
        Result r =  loginService.login(username,password);
        //判断后端查询结果是否为空
        if (r.isFlag()){
            //如果登录成功 把user存入session
            HttpSession session = request.getSession();
            session.setAttribute("user",r.getData());
        }else{
            response.sendRedirect("login.html");
        }
       response.sendRedirect("pages/main.html");
    }

}

```

②：业务层service代码如下

因为这里 注入了 Service 所以 要编写service层 接口代码，和实现类代码

```java
package com.itheima.service;

import com.itheima.entity.Result;

public interface LoginService {
    Result login(String username, String password);
}

```

实现类代码如下所示：

```java
package com.itheima.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itheima.entity.Result;
import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import com.itheima.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result login(String username, String password) {
        //1、判断传入数据是否为空
        if (username==null||password==null){
            return new Result(false,"数据异常" );
        }
        //2、根据传入的username查询对象数据
        User userDb = userMapper.login(username);
        //3、判断数据库中是否有该对象
        if (userDb==null){
            return new Result(false,"该用户不存在" );
        }
        //4.1、把传入的密码加密
        String checkPwd = DigestUtils.md5DigestAsHex(password.getBytes());
        //4.2、匹配数据库信息 查询密码是否正确
        if (!userDb.getPassword().equals(checkPwd)){
            return new Result(false, "密码不正确");
        }
        return new Result(true,"登陆成功",userDb);
    }
}

```

③：持久层mapper

```java

@Mapper
public interface UserMapper {
    User login(String username);
}
```

以及对应的sql语句

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.itheima.mapper.UserMapper">
    <select id="login" resultType="com.itheima.pojo.User">
        select * from t_user where username = #{username}
    </select>
</mapper>
```



测试:

出现200 返回页面即可，代表正常，在web项目中访问登录成功也可以。



到此为止前端项目启动成功 ：在浏览器中输入http://localhost:9001/  即可出现下面这个页面 如果正常登录代表功能完成。

![](C:\Users\zhang\Desktop\讲义\img\12.png)

 

## 3.2)查询获得登录用户

| 请求url  | http://localhost:9001/user/getUsername.do                    |                  |
| -------- | ------------------------------------------------------------ | ---------------- |
| 请求方式 | GET                                                          |                  |
| 请求参数 | 无                                                           |                  |
| 返回值   | {"flag":true,"message":"获取当前登录用户名称成功","data":"admin"}x | 返回值对象Result |

登录成功之后 返回  用户名 ："admin"

![](C:\Users\zhang\Desktop\讲义\img\14.png)

1、定义controller 层代码：

```java
package com.itheima.controller;
import com.itheima.entity.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
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
    private HttpServletRequest request;

    @GetMapping("/getUsername.do")
    public Result findUser(){
        // 从request域里面获取到session对象， 
        HttpSession session = request.getSession();
        // 从session获取到user对象 
        User user = (User) session.getAttribute("user");
        // 判断user 对象是否为空 不为空则 获取用户的 用户名 返回给前端即可。
        if (user==null){
            return new Result(false,"请先登录" );
        }
        return new Result(true,"获取当前登录用户名称成功",user.getUsername() );
    }
}
```

