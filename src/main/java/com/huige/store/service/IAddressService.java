package com.huige.store.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huige.store.entity.Address;

import java.util.List;

/**
 * @Author huige
 * Create on 2022/4/13 19:38
 * 收货地址业务层接口
 */
public interface IAddressService extends IService<Address> {

    public void addNewAddress(Integer uid, String username, Address address);

    public List<Address> getByUid(Integer uid);

    public void updateDefaultByAid(Integer aid, Integer uid, String username);

    public void deleteAddress(Integer aid, Integer uid, String username);

    public Address getByAid(Integer aid, Integer uid);
}
