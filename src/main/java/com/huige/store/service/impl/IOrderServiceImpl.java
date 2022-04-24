package com.huige.store.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huige.store.entity.Address;
import com.huige.store.entity.Order;
import com.huige.store.entity.OrderItem;
import com.huige.store.mapper.OrderItemMapper;
import com.huige.store.mapper.OrderMapper;
import com.huige.store.service.IAddressService;
import com.huige.store.service.ICartService;
import com.huige.store.service.IOrderService;
import com.huige.store.service.ex.InsertException;
import com.huige.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author huige
 * Create on 2022/4/16 13:14
 */
@Service
public class IOrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private IAddressService addressService;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private ICartService cartService;

    @Override
    public Order create(Integer aid, Integer uid, String username, Integer[] cids) {
        // 即将要下单的列表
        List<CartVO> cidList = cartService.getVOCid(uid, cids);
        // 计算商品的总价
        long totalPrice = 0;
        for (CartVO c : cidList) {
            totalPrice += c.getRealPrice() * c.getNum();
        }
        Address address = addressService.getByAid(aid, uid);
        Order order = new Order();
        order.setUid(uid);
        // 补全收货地址信息
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        order.setOrderTime(new Date());
        // 支付、总价、时间
        order.setStatus(0);
        order.setTotalPrice(totalPrice);
        // 日志
        order.setCreatedUser(username);
        order.setCreatedTime(new Date());
        order.setModifiedTime(new Date());
        order.setModifiedUser(username);
        // 插入数据
        int insert = orderMapper.insert(order);
        if (insert != 1) {
            throw new InsertException("插入数据失败");
        }

        for (CartVO c : cidList) {
            // 创建一个订单项数组
            OrderItem orderItem = new OrderItem();
            orderItem.setOid(order.getOid());
            orderItem.setPid(c.getPid());
            orderItem.setTitle(c.getTitle());
            orderItem.setImage(c.getImage());
            orderItem.setPrice(c.getRealPrice());
            orderItem.setNum(c.getNum());
            // 日志补全
            orderItem.setCreatedUser(username);
            orderItem.setCreatedTime(new Date());
            orderItem.setModifiedTime(new Date());
            orderItem.setModifiedUser(username);
            Integer rows = orderItemMapper.insert(orderItem);
            if(rows != 1) {
                throw new InsertException("插入数据失败");
            }
        }
        return order;
    }
}
