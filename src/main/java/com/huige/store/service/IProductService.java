package com.huige.store.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huige.store.entity.Product;

import java.util.List;


/**
 * (Product)表服务接口
 *
 * @author makejava
 * @since 2022-04-14 22:26:41
 */
public interface IProductService extends IService<Product> {
    public List<Product> findHostList();

    public Product findById(Integer id);
}
