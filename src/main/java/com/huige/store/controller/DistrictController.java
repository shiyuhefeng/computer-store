package com.huige.store.controller;

import com.huige.store.entity.District;
import com.huige.store.service.IDistrictService;
import com.huige.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author huige
 * Create on 2022/4/13 20:59
 */
@RestController
@RequestMapping("/districts")
public class DistrictController extends BaseController {

    @Autowired
    private IDistrictService districtService;

    // districts开头的请求都被拦截到getByParent()方法
    @RequestMapping({"/", ""})
    public JsonResult<List<District>> getByParent(String parent) {
        List<District> data = districtService.getByParent(parent);
        return new JsonResult<>(OK, "查询成功", data);
    }
    //
    // @RequestMapping("")
    // public JsonResult<String> getNameByCode()
}
