package com.huige.store.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huige.store.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;


/**
 * (TUser)表服务接口
 *
 * @author makejava
 * @since 2022-04-10 21:49:38
 */
public interface IUserService extends IService<User> {

    /**
     * 用户的注册方法
     *
     * @param user 用户的数据对象
     */
    void reg(User user);

    /**
     * 用户登录功能
     *
     * @param username 用户名
     * @param password 用户的密码
     * @return 当前匹配的用户数据，如果没有则返回null值
     */
    public User login(String username, String password);

    public void changePassword(Integer uid,
                               String username,
                               String oldPassword,
                               String newPassword);

    /**
     * 根据用户的Id查询用户的数据
     *
     * @param uid 用户的id
     * @return 用户的数据
     */
    public User getByUid(Integer uid);

    /**
     * 更新用户的数据操作
     *
     * @param uid      用户的id
     * @param username 用户的名称
     * @param user     用户对象的数据
     */
    public void changeInfo(Integer uid, String username, User user);

    /**
     * 更新用户的头像
     * @param uid 用户的id
     * @param avatar 用户的头像路径
     */
    public void updateAvatarByUid(Integer uid, String avatar, String username);
}
