package com.huige.store.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huige.store.entity.District;

import java.util.List;

/**
 * @Author huige
 * Create on 2022/4/13 20:27
 */
public interface DistrictMapper extends BaseMapper<District> {

    /**
     * 根据父代号查询
     * @param parent 父代号
     * @return 某个区域下的所有区域列表
     */
    public List<District> findByParent(String parent);

    public String findNameByCode(String code);
}
