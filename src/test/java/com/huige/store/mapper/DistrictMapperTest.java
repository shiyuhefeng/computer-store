package com.huige.store.mapper;

import com.huige.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

// @SpringBoot：表示标注当前的类是一个测试类，不会随同项目一起打包发送
@SpringBootTest
// RunWith： 表示启动这个单元测试类（单元测试类是不能够运行的）需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class DistrictMapperTest {

    @Autowired
    private DistrictMapper districtMapper;

    @Test
    public void findByParent() {
        List<District> list = districtMapper.findByParent("110100");
        for (District d : list) {
            System.out.println(d);
        }
    }

    @Test
    public void findNameByCode() {
        String name = districtMapper.findNameByCode("610000");
        System.out.println(name);
    }
}
