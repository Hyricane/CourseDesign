package com.itheima.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_checkgroup_checkitem")
public class CheckgroupCheckitem {
    public CheckgroupCheckitem() {
    }

    public CheckgroupCheckitem(Integer checkgroupId, Integer checkitemId) {
        this.checkgroupId = checkgroupId;
        this.checkitemId = checkitemId;
    }

    @TableField("checkgroup_id")
    private Integer checkgroupId;
    @TableField("checkitem_id")
    private Integer checkitemId;
}
