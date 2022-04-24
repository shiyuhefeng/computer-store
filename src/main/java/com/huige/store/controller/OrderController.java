package com.huige.store.controller;

import com.huige.store.entity.Order;
import com.huige.store.service.IOrderService;
import com.huige.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Author huige
 * Create on 2022/4/16 14:55
 */
@RequestMapping("/orders")
@RestController
public class OrderController extends BaseController{

    @Autowired
    private IOrderService orderService;

    @RequestMapping("/create")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        Order order = orderService.create(aid, uid, username, cids);
        return new JsonResult<>(OK, "插入成功", order);
    }
}
