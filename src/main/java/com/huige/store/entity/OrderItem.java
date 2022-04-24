package com.huige.store.entity;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * (OrderItem)表实体类
 *
 * @author makejava
 * @since 2022-04-16 12:03:14
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_order_item")
public class OrderItem extends BaseEntity implements Serializable{
    //订单中的商品记录的id
    @TableId(type = IdType.AUTO)
    private Integer id;
    //所归属的订单的id
    private Integer oid;
    //商品的id
    private Integer pid;
    //商品标题
    private String title;
    //商品图片
    private String image;
    //商品价格
    private Long price;
    //购买数量
    private Integer num;
    //创建人
    private String createdUser;
    //创建时间
    private Date createdTime;
    //修改人
    private String modifiedUser;
    //修改时间
    private Date modifiedTime;

}

