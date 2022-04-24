package com.huige.store.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huige.store.entity.Cart;
import com.huige.store.vo.CartVO;

import java.util.List;

/**
 * @Author huige
 * Create on 2022/4/15 11:13
 */
public interface ICartService extends IService<Cart> {

    /**
     * 将商品添加到购物车中
     * @param uid 用户id
     * @param pid 商品id
     * @param num 新增的数量
     * @param username 用户名
     */
    public void insertCart(Integer uid, Integer pid, Integer num, String username);

    public List<CartVO> getVoByUid(Integer uid);

    /**
     * 更新用户的购物车数据的数量
     * @param cid 购物车id
     * @param uid 用户的id
     * @param username 用户名
     * @return 增加成功后
     */
    public Integer addNum(Integer cid, Integer uid, String username, Integer num);

    List<CartVO> getVOCid(Integer uid, Integer[] cids);
}
