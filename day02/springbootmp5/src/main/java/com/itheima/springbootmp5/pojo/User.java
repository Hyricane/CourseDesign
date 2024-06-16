package com.itheima.springbootmp5.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("tb_user")
@Data
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("user_name")
    private String userName;
    private String password;
    private String name;
    private Integer age;
    private String email;

    //不希望该值存入数据库

    // @TableField(exist = false)

    // private String info;

}