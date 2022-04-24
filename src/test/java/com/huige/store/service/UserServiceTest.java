package com.huige.store.service;

import com.huige.store.entity.User;
import com.huige.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    public void reg() {
        try {
            User user = new User();
            user.setUsername("cat");
            user.setPassword("123");
            userService.reg(user);
            System.out.println("OK");
        } catch (ServiceException e) {
            // 获取异常的类对象，再获取类的名称
            System.out.println(e.getClass().getSimpleName());
            // 获取异常的具体描述信息
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login() {
        User ahui = userService.login("huige", "123");
        System.out.println(ahui);
    }

    @Test
    public void changePassword() {
        userService.changePassword(11, "cat", "123", "321");
    }

    @Test
    public void getByUid() {
        User byUid = userService.getByUid(11);
        System.out.println(byUid);
    }

    @Test
    public void updateInfo() {
        User user = new User();
        user.setPhone("10010");
        user.setEmail("10086@163.com");
        user.setGender(0);
        userService.changeInfo(11, "root", user);
    }

    @Test
    public void changeAvatar() {
        userService.updateAvatarByUid(11, "upload/hello.png", "root");
    }
}
