package com.huige.store.controller;

import com.huige.store.entity.Address;
import com.huige.store.service.IAddressService;
import com.huige.store.service.IDistrictService;
import com.huige.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author huige
 * Create on 2022/4/13 20:03
 */
@RestController
@RequestMapping("/addresses")
public class AddressController extends BaseController {

    @Autowired
    private IAddressService addressService;

    @RequestMapping("/add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.addNewAddress(uid, username, address);
        return new JsonResult<>(OK, "添加成功！");
    }

    @RequestMapping({"/", ""})
    public JsonResult<List<Address>> getAddressByUid(HttpSession session) {
        Integer uid = getuidFromSession(session);
        List<Address> list = addressService.getByUid(uid);
        return new JsonResult<>(OK, "查询成功", list);
    }

    /**
     * RestFul风格的请求
     */
    @RequestMapping("/{aid}/set_default")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid, HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.updateDefaultByAid(aid, uid, username);
        return new JsonResult<>(OK);
    }

    @RequestMapping("/{aid}/delete")
    public JsonResult<Void> delete(@PathVariable("aid") Integer aid, HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.deleteAddress(aid, uid, username);
        return new JsonResult<>(OK);
    }

}
