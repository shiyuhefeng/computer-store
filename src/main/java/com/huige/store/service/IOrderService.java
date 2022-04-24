package com.huige.store.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huige.store.entity.Order;

/**
 * @Author huige
 * Create on 2022/4/16 13:14
 */
public interface IOrderService extends IService<Order> {

    Order create(Integer aid, Integer uid, String username, Integer[] cids);
}
