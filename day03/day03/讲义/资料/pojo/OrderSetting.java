package com.itheima.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 预约设置
 */
@TableName("t_ordersetting")
public class OrderSetting implements Serializable{
    @TableId(type = IdType.AUTO)
    private Integer id ;
    @Excel(name = "日期")
    @TableField("order_date")
    private Date orderDate;//预约设置日期
    @Excel(name = "可预约数量")
    private int number;//可预约人数
    private int reservations ;//已预约人数

    public OrderSetting() {
    }

    public OrderSetting(Date orderDate, int number) {
        this.orderDate = orderDate;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getReservations() {
        return reservations;
    }

    public void setReservations(int reservations) {
        this.reservations = reservations;
    }
}
