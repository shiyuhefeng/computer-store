package com.huige.store.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huige.store.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface UserMapper extends BaseMapper<User> {

    /**
     * 插入用户的数据
     *
     * @param user 用户的数据
     * @return 受影响的行数 （在增 删 改 都受影响的行数作为返回值，可以根据返回值来判断是否执行操作）
     */
    public Integer insertUser(User user);

    /**
     * 根据用户名来查询用户的数据
     *
     * @param username 用户名
     * @return 如果找到对应的用户则返回这个用户的数据 如果没有找到返回null
     */
    public User findByUsername(String username);

    /**
     * 根据用户的uid来修改用户密码
     *
     * @param uid 用户的id
     * @return 返回值为受影响的行数
     */
    public Integer updatePasswordByUid(Integer uid,
                                       String password,
                                       String modifiedUser,
                                       Date modifiedTime);

    /**
     * 根据用户的id查询用户的数据
     *
     * @param uid 用户的id
     * @return 如果找到返回对象 反之返回null值
     */
    public User findByUid(Integer uid);

    /**
     * 更新用户的数据信息
     *
     * @param user 用户的数据
     * @return 返回值为受影响的行数
     */
    public Integer updateInfoByUid(User user);

    /**
     *  - @Param("SQL映射文件中#{}占位符的变量名")
     *          解决额问题是： 当SQL语句的占位符和映射的接口方法参数名不一致时，需要将某个参数强行注入到某个占位符上时，
     *                      需要使用@Param
     * 根据用户的uid值来修改用户的头像
     * @param uid 用户的uid
     * @param avatar 用户的头像路径
     * @param modifiedUser 修改的用户
     * @param modifiedTime 修改的时间
     * @return
     */
    public Integer updateAvatarByUid(@Param("uid") Integer uid,
                                     @Param("avatar") String avatar,
                                     @Param("modifiedUser") String modifiedUser,
                                     @Param("modifiedTime") Date modifiedTime);
}
