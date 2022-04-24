package com.huige.store.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huige.store.controller.ex.AddressCountLimitException;
import com.huige.store.entity.Address;
import com.huige.store.mapper.AddressMapper;
import com.huige.store.mapper.DistrictMapper;
import com.huige.store.service.IAddressService;
import com.huige.store.service.IDistrictService;
import com.huige.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author huige
 * Create on 2022/4/13 19:44
 */
@Service
public class IAddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;

    // 再添加用户的收货地址的业务依赖于districtMapper持久层接口
    @Autowired
    private DistrictMapper districtMapper;

    // 读取配置文件的数据
    @Value("${user.address.max-count}")
    private Integer maxCount;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        // 判断是否达到了用户数据的上限
        Integer count = addressMapper.countByUid(uid);
        if (count >= maxCount) {
            throw new AddressCountLimitException("用户收货地址超出上限");
        }

        // 对address对象中的数据进行补全，省市区
        String provinceName = districtMapper.findNameByCode(address.getProvinceCode());
        String cityName = districtMapper.findNameByCode(address.getCityCode());
        String areaName = districtMapper.findNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

        // uid、isDelete
        address.setUid(uid);
        // 1表示默认 0表示不是默认
        Integer isDefault = count == 0 ? 1 : 0;

        address.setIsDefault(isDefault);
        // 补全日志
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());

        // 插入收货地址的方法
        Integer rows = addressMapper.insertAddress(address);
        if (rows != 1) {
            throw new InsertException("插入用户的收货地址产生未知异常");
        }
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        for (Address address : list) {
            address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setTel(null);
            address.setIsDefault(null);
            address.setCreatedUser(null);
            address.setCreatedTime(null);
            address.setModifiedTime(null);
            address.setModifiedUser(null);
        }
        return list;
    }

    @Override
    public void updateDefaultByAid(Integer aid, Integer uid, String username) {
        Address address = addressMapper.findByAid(aid);
        if (address == null) {
            throw new AddressNotFoundException("收货地址不存在");
        }
        // 检测当前获取的到收货地址归属
        if (!address.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }
        // 先将所有的收获地址设置为非默认
        Integer row = addressMapper.updateNonDefault(uid);
        if (row < 1) {
            throw new UpdateException("数据产生未知的异常");
        }
        row = addressMapper.updateDefaultByAid(aid, username, new Date());
        if (row != 1) {
            throw new UpdateException("更新数据产生未知的异常");
        }
    }

    /**
     * 删除用户的收货地址数据
     *
     * @param aid      收货的id
     * @param uid      用户的id
     * @param username 用户名
     */
    @Override
    public void deleteAddress(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if (result == null) {
            throw new AddressNotFoundException("收货地址不存在");
        }
        // 检测当前获取到的收货地址数据的归属
        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("非法数据访问");
        }
        Integer rows = addressMapper.deleteByAid(aid);
        if (rows != 1) {
            throw new DeleteException("删除数据产生未知的异常");
        }

        Integer count = addressMapper.countByUid(uid);
        if (count == 0) {
            // 直接终止
            return;
        }
        // 删除的是默认地址
        if(result.getIsDefault() == 0) {
            return;
        }
        // 将这条数据中的is_default字符的值设置为 1
        Address address = addressMapper.findListModified(uid);
        rows = addressMapper.updateDefaultByAid(address.getAid(), username, new Date());
        if (rows != 1) {
            throw new UpdateException("删除时产生未知异常");
        }

    }

    @Override
    public Address getByAid(Integer aid, Integer uid) {
        Address address = addressMapper.findByAid(aid);
        if(address == null) {
            throw new AddressNotFoundException("收货地址未找到");
        }
        if(!address.getUid().equals(uid)) {
            throw new AccessDeniedException("非法数据方法");
        }
        return address;
    }
}
