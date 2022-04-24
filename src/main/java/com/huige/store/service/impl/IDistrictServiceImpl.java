package com.huige.store.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huige.store.entity.District;
import com.huige.store.mapper.DistrictMapper;
import com.huige.store.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author huige
 * Create on 2022/4/13 20:52
 */
@Service
public class IDistrictServiceImpl extends ServiceImpl<DistrictMapper, District> implements IDistrictService {

    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public List<District> getByParent(String parent) {
        List<District> list = districtMapper.findByParent(parent);
        /**
         * 在进行网络数据传输时，为了尽量避免无效数据的传递，可以将无效数据设置为null，可以节省流量，另一方面提升了效率
         */
        for(District d : list) {
            d.setId(null);
            d.setParent(null);
        }
        return list;
    }

    @Override
    public String findNameByCode(String code) {
        return districtMapper.findNameByCode(code);
    }
}
