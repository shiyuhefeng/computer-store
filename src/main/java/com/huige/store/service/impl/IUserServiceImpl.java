package com.huige.store.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huige.store.entity.User;
import com.huige.store.enums.AppHttpCodeEnum;
import com.huige.store.mapper.UserMapper;
import com.huige.store.service.IUserService;
import com.huige.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/**
 * (TUser)表服务实现类
 *
 * @author makejava
 * @since 2022-04-10 21:49:39
 */
@Service("UserService")
public class IUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        // 通过user参数来获取传递过来的username
        String username = user.getUsername();
        // 调用findByUsername(username)判断用户是否被注册过
        User result = userMapper.findByUsername(username);
        // 判断结果集是都不为null则抛出用户名被占用异常
        if (result != null) {
            throw new UsernameDuplicateException("用户名被占用");
        }

        // 密码加密处理的实现:
        //              md5算法的形式:
        // 串 + password + 串 ------ md5算法加密
        // 盐值 + password + 盐值 ------ 盐值就是一个随机的字符串
        String oldPassword = user.getPassword();

        // 获取盐值(随机生成一个盐值)
        String salt = UUID.randomUUID().toString().toUpperCase();

        // 将密码和盐值作为一个整体进行加密处理
        String md5Password = getMD5Password(oldPassword, salt);

        // 将加密之后的密码重新补全到user对象中
        user.setPassword(md5Password);

        // 补全数据: 盐值的记录
        user.setSalt(salt);

        // 补全数据: is_delete设置为0
        user.setIsDelete(0);

        // 补全数据: 4个日志字段信息
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        // 执行业务功能的实现(row = 1)
        Integer row = userMapper.insertUser(user);
        if (row != 1) {
            throw new InsertException("在用户注册过程中产生了未知的异常");
        }
    }

    @Override
    public User login(String username, String password) {
        // 根据用户名称来查询用户的数据是否存在 如果不存在则抛出异常
        User result = userMapper.findByUsername(username);
        if(result  == null) {
            throw new UsernameNotFoundException("用户数据不存在");
        }
        // 检测用户的密码是否匹配
        // 1.先获取到数据库库中的加密之后的密码

        // 2.和用户传递过来的密码进行比较
        // 2.1将用户的密码按照相同的md5算法的规则进行加密
        // 2.2将用户的密码按照相同的md5加密算法进行加密
        String salt = result.getSalt();
        String newMd5Password = getMD5Password(password, salt);
        // 3.将密码进行比价
        if(!newMd5Password.equals(result.getPassword())) {
            throw new PasswordNotFoundException("用户名密码错误");
        }
        // 判断is_delete字段的值是否为1 表示标记为删除
        if(result.getIsDelete() == 1) {
            throw new UsernameNotFoundException("用户数据不存在");
        }
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete() == 1) {
            throw new UsernameNotFoundException(AppHttpCodeEnum.USERNAME_NOTFOUND_MSG);
        }
        // 原始密码和数据库密码进行比较
        String md5Password = getMD5Password(oldPassword, result.getSalt());
        if(!md5Password.equals(result.getPassword())) {
            throw new PasswordNotFoundException(AppHttpCodeEnum.PASSWORD_NOTFOUND_MSG);
        }
        // 将新的密码设置到数据库中，将新的密码进行加密再去更新
        String newMd5Password = getMD5Password(newPassword, result.getSalt());
        Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());
        if(rows != 1) {
            throw new UpdateException(AppHttpCodeEnum.UPDATE_EXCEPTION_MSG);
        }
    }

    @Override
    public User getByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete() == 1) {
            throw new UsernameDuplicateException(AppHttpCodeEnum.USERNAME_NOTFOUND_MSG);
        }
        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());

        return user;
    }

    /**
     * user对象中的数据phone/email/gender, 手动将uid/username封装成
     * user对象
     */
    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete() == 1) {
            throw new UsernameDuplicateException(AppHttpCodeEnum.USERNAME_NOTFOUND_MSG);
        }
        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());

        Integer row = userMapper.updateInfoByUid(user);
        if(row != 1) {
            throw new UpdateException(AppHttpCodeEnum.UPDATE_EXCEPTION_MSG);
        }
    }

    @Override
    public void updateAvatarByUid(Integer uid, String avatar, String username) {
        // 查询当前的用户数据是否存在
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete() == 1) {
            throw new UsernameDuplicateException(AppHttpCodeEnum.USERNAME_DUPLICATE_MSG);
        }
        Integer rows = userMapper.updateAvatarByUid(uid, avatar, result.getUsername(), new Date());
        if(rows != 1) {
            throw new UpdateException(AppHttpCodeEnum.UPDATE_EXCEPTION_MSG);
        }
    }

    /**
     * md5加密
     * @param password 需要加密密码
     * @param salt     盐值
     * @return 加密后的密码
     */
    private String getMD5Password(String password, String salt) {
        // md5加密算法方法的调用 进行三次加密
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        // 返回加密之后的密码
        return password;
    }
}
