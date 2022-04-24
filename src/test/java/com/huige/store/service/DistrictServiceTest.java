package com.huige.store.service;

import com.huige.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictServiceTest {

    @Autowired
    private IDistrictService districtService;

    @Test
    public void getByParent() {
        List<District> byParent = districtService.getByParent("86");
        byParent.forEach(System.out::println);
    }

}
