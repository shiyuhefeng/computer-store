package com.huige.store.service;

import com.huige.store.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTest {

    @Autowired
    private IOrderService orderService;

    @Test
    public void orderServiceTest() {
        Order order = orderService.create(3, 11, "女盆友", new Integer[]{2, 3});
        System.out.println(order);
    }
}
