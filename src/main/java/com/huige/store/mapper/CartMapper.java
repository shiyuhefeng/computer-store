package com.huige.store.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huige.store.entity.Cart;
import com.huige.store.vo.CartVO;

import java.util.List;

/**
 * @Author huige
 * Create on 2022/4/15 11:12
 */
public interface CartMapper extends BaseMapper<Cart> {


    public List<CartVO> findVOByUid(Integer uid);

    public List<CartVO> findVOByCid(Integer[] cids);
}
