package com.huige.store.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huige.store.entity.OrderItem;
import com.huige.store.mapper.OrderItemMapper;
import com.huige.store.service.IOrderItemService;
import org.springframework.stereotype.Service;

/**
 * @Author huige
 * Create on 2022/4/16 13:24
 */
@Service
public class IOrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements IOrderItemService {
}
