package com.huige.store.service;

import com.huige.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CartServiceTest {

    @Autowired
    private ICartService cartService;

    @Test
    public void addToCart() {
        cartService.insertCart(11, 10000013, 10, "zm");
    }
}
