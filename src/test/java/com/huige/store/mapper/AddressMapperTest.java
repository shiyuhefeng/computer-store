package com.huige.store.mapper;

import com.huige.store.entity.Address;
import com.huige.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

// @SpringBoot：表示标注当前的类是一个测试类，不会随同项目一起打包发送
@SpringBootTest
// RunWith： 表示启动这个单元测试类（单元测试类是不能够运行的）需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class AddressMapperTest {

    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insertAddressTest() {
        Address address = new Address();
        address.setUid(11);
        address.setPhone("12345");
        address.setName("npy");
        addressMapper.insertAddress(address);
    }

    @Test
    public void countByUid() {
        Integer integer = addressMapper.countByUid(11);
        System.out.println(integer);
    }

}
