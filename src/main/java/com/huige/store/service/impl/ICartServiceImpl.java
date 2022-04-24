package com.huige.store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huige.store.entity.Cart;
import com.huige.store.entity.Product;
import com.huige.store.mapper.CartMapper;
import com.huige.store.mapper.ProductMapper;
import com.huige.store.service.ICartService;
import com.huige.store.service.ex.AccessDeniedException;
import com.huige.store.service.ex.AddCartException;
import com.huige.store.service.ex.CartNotFoundException;
import com.huige.store.service.ex.UpdateException;
import com.huige.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @Author huige
 * Create on 2022/4/15 11:14
 */
@Service
public class ICartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void insertCart(Integer uid, Integer pid, Integer num, String username) {
        LambdaQueryWrapper<Cart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Cart::getPid, pid);
        queryWrapper.eq(Cart::getUid, uid);
        Cart cartObj = cartMapper.selectOne(queryWrapper);
        Date date = new Date();
        if (cartObj != null) {
            // 如果购物车对象存在（不为空）需要进行添加操作
            LambdaUpdateWrapper<Cart> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(Cart::getCid, cartObj.getCid());
            wrapper.set(Cart::getNum, cartObj.getNum() + num);
            wrapper.set(Cart::getModifiedUser, username);
            wrapper.set(Cart::getModifiedTime, date);
            int rows = cartMapper.update(null, wrapper);
            if(rows != 1) {
                throw new UpdateException("更新时产生异常");
            }
        } else {
            // 表示当前商品在购物车中不存在  则插入
            Cart cart = new Cart();
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(num);
            Product product = productMapper.selectById(pid);
            cart.setPrice(product.getPrice());
            cart.setCreatedUser(username);
            cart.setCreatedTime(date);
            cart.setModifiedTime(date);
            cart.setModifiedUser(username);
            int rows = cartMapper.insert(cart);
            if (rows < 1) {
                throw new AddCartException("添加购物车时出现异常");
            }
        }
    }

    @Override
    public List<CartVO> getVoByUid(Integer uid) {
        return cartMapper.findVOByUid(uid);
    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String username, Integer num) {
        Cart cart = cartMapper.selectById(cid);
        if(cart == null) {
            throw new CartNotFoundException("数据不存在");
        }
        if(!cart.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }
        num = cart.getNum() + num;
        if(num < 0) {
            throw new UpdateException("更新数据异常");
        }
        LambdaUpdateWrapper<Cart> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Cart::getCid, cid);
        wrapper.set(Cart::getNum, num);
        wrapper.set(Cart::getModifiedUser, username);
        wrapper.set(Cart::getModifiedTime, new Date());
        int update = cartMapper.update(null, wrapper);
        if(update != 1) {
            throw new UpdateException("更新数据失败");
        }
        return num;
    }

    @Override
    public List<CartVO> getVOCid(Integer uid, Integer[] cids) {
        List<CartVO> list = cartMapper.findVOByCid(cids);
        Iterator<CartVO> it = list.iterator();
        while(it.hasNext()) {
            CartVO cartVO = it.next();
            // 当前数据不属于当前用户
            if(!cartVO.getUid().equals(uid)) {
                list.remove(cartVO);
            }
        }
        return list;
    }
}
