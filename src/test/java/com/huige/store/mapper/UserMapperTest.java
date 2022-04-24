package com.huige.store.mapper;

import com.huige.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

// @SpringBoot：表示标注当前的类是一个测试类，不会随同项目一起打包发送
@SpringBootTest
// RunWith： 表示启动这个单元测试类（单元测试类是不能够运行的）需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class UserMapperTest {
    /**
     * 单元测试方法的特点, 就可以单独独立运行，不用启动整个项目，可以做单元测试，提升了代码效率
     *      1. 必须被@Test注解修饰
     *      2. 返回值必须是void
     *      3. 方法的参数列表不指定任何类型
     *      4. 方法的访问修饰符必须是public
     */

    // idea检测功能，接口是不能够直接创建Bean的(动态代理技术来实现的)
    @Autowired
    private UserMapper userMapper;

    @Test
    public void insertUser() {
        User user = new User();
        user.setUsername("cat");
        user.setPassword("123");
        System.out.println(user);
        Integer integer = userMapper.insertUser(user);
        System.out.println(integer);
    }

    @Test
    public void findByUsername() {
        User tom = userMapper.findByUsername("tom");
        System.out.println(tom);
    }

    @Test
    public void updatePasswordById() {
        Integer num = userMapper.updatePasswordByUid(9, "321", "root", new Date());
        System.out.println(num);
    }


    @Test
    public void findByUid() {
        User user = userMapper.findByUid(9);
        System.out.println(user);
    }

    @Test
    public void updateInfoById() {
        User user = new User();
        user.setUid(11);
        user.setPhone("10086");
        user.setEmail("10086@163.com");
        user.setGender(1);
        Integer integer = userMapper.updateInfoByUid(user);
        System.out.println(integer);
    }


    @Test
    public void updateAvatarByUid() {
        Integer res = userMapper.updateAvatarByUid(11, "/upload/avatar.png", "root", new Date());
        System.out.println(res);
    }


}
