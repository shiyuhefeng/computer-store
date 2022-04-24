package com.huige.store.entity;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * (Order)表实体类
 *
 * @author makejava
 * @since 2022-04-16 12:03:05
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_order")
public class Order extends BaseEntity implements Serializable{
    //订单id
    @TableId(type = IdType.AUTO)
    private Integer oid;
    //用户id
    private Integer uid;
    //收货人姓名
    private String recvName;
    //收货人电话
    private String recvPhone;
    //收货人所在省
    private String recvProvince;
    //收货人所在市
    private String recvCity;
    //收货人所在区
    private String recvArea;
    //收货详细地址
    private String recvAddress;
    //总价
    private Long totalPrice;
    //状态：0-未支付，1-已支付，2-已取消，3-已关闭，4-已完成
    private Integer status;
    //下单时间
    private Date orderTime;
    //支付时间
    private Date payTime;
    //创建人
    private String createdUser;
    //创建时间
    private Date createdTime;
    //修改人
    private String modifiedUser;
    //修改时间
    private Date modifiedTime;

}

