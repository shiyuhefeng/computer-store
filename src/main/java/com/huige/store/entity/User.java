package com.huige.store.entity;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2022-04-10 21:36:41
 */
@SuppressWarnings("serial")
// SpringBoot约定大于配置
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_user")
public class User extends BaseEntity implements Serializable {
    //用户id
    private Integer uid;
    //用户名
    private String username;
    //密码
    private String password;
    //盐值
    private String salt;
    //电话号码
    private String phone;
    //电子邮箱
    private String email;
    //性别:0-女，1-男
    private Integer gender;
    //头像
    private String avatar;
    //是否删除：0-未删除，1-已删除
    private Integer isDelete;

}

