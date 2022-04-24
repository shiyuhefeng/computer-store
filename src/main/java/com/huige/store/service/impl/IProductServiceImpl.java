package com.huige.store.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huige.store.entity.Product;
import com.huige.store.mapper.ProductMapper;
import com.huige.store.service.IProductService;
import com.huige.store.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Product)表服务实现类
 *
 * @author makejava
 * @since 2022-04-14 22:26:41
 */
@Service("tProductService")
public class IProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findHostList() {
        List<Product> list = productMapper.findHostList();
        return list;
    }

    public Product findById(Integer id) {
        Product product = getById(id);
        if (product == null) {
            throw new ProductNotFoundException("商品未找到");
        }
        return product;
    }
}
