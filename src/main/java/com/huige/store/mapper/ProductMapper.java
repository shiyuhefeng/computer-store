package com.huige.store.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huige.store.entity.Product;

import java.util.List;


/**
 * (Product)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-14 22:26:41
 */
public interface ProductMapper extends BaseMapper<Product> {
    public List<Product> findHostList();
}

