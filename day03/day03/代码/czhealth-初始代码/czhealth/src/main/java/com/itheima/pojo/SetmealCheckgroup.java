package com.itheima.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_setmeal_checkgroup")
public class SetmealCheckgroup implements Serializable {
    public SetmealCheckgroup() {
    }

    public SetmealCheckgroup(Integer setmealId, Integer checkgroupId) {
        this.setmealId = setmealId;
        this.checkgroupId = checkgroupId;
    }

    @TableField("setmeal_id")
    private Integer setmealId;

    @TableField("checkgroup_id")
    private Integer checkgroupId;
}
