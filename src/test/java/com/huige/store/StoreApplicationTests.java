package com.huige.store;

import com.huige.store.entity.User;
import com.huige.store.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.List;

@SpringBootTest
class StoreApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private IUserService userService;

    @Test
    void contextLoads() {

    }

    @Test
    public void getContext() {
        List<User> list = userService.list();
        for(User u : list) {
            System.out.println(u);
        }
    }



}
