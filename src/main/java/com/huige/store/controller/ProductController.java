package com.huige.store.controller;

import com.huige.store.entity.Product;
import com.huige.store.service.IProductService;
import com.huige.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @Author huige
 * Create on 2022/4/15 10:44
 */
@RestController
@RequestMapping("/products")
public class ProductController extends BaseController {

    @Autowired
    private IProductService productService;

    @RequestMapping("/hot_list")
    public JsonResult<List<Product>> getHostList() {
        List<Product> data = productService.findHostList();
        return new JsonResult<>(OK, "查询成功", data);
    }

    @RequestMapping("/{id}/details")
    public JsonResult<Product> findById(@PathVariable("id") Integer id) {
        return new JsonResult<>(OK, "查询成功", productService.findById(id));
    }
}


class TestDemo1 {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("张三", 12);
        map.put("李四", 30);
        map.put("王五", 15);
        Stream<Map.Entry<String, Integer>> stream = map.entrySet().stream();
        stream.filter(k -> k.getValue() > 15)
                .forEach(System.out::println);
    }

}