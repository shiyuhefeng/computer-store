package com.huige.store.service;

import com.huige.store.entity.Address;
import com.huige.store.entity.User;
import com.huige.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTest {

    @Autowired
    private IAddressService addressService;

    @Test
    public void addNewAddress() {
        Address address = new Address();
        address.setPhone("100100000");
        address.setName("女盆友");
        addressService.addNewAddress(11, "root", address);
    }

    @Test
    public void updateDefaultByAid() {
        addressService.updateDefaultByAid(3, 11, "cat");
    }

    @Test
    public void deleteAddress() {
        addressService.deleteAddress(2, 11, "root");
    }
}
