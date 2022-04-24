package com.huige.store.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huige.store.entity.District;

import java.util.List;

/**
 * @Author huige
 * Create on 2022/4/13 20:47
 */
public interface IDistrictService extends IService<District> {

    /**
     * 根据父代号来查询区域信息(省、市、区)
     * @param parent 父区域代码
     * @return 多个区域的信息
     */
    public List<District> getByParent(String parent);

    public String findNameByCode(String code);
}
