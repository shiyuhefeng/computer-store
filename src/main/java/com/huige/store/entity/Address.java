package com.huige.store.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * (Address)表实体类
 *
 * @author makejava
 * @since 2022-04-10 21:37:34
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseEntity implements Serializable{
    //收货地址id
    private Integer aid;
    //归属的用户id
    private Integer uid;
    //收货人姓名
    private String name;
    //省-名称
    private String provinceName;
    //省-行政代号
    private String provinceCode;
    //市-名称
    private String cityName;
    //市-行政代号
    private String cityCode;
    //区-名称
    private String areaName;
    //区-行政代号
    private String areaCode;
    //邮政编码
    private String zip;
    //详细地址
    private String address;
    //手机
    private String phone;
    //固话
    private String tel;
    //标签
    private String tag;
    //是否默认：0-不默认，1-默认
    private Integer isDefault;
}

