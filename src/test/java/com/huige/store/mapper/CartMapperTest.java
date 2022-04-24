package com.huige.store.mapper;

import com.huige.store.entity.Address;
import com.huige.store.vo.CartVO;
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
public class CartMapperTest {

    @Autowired
    private CartMapper cartMapper;

    @Test
    public void findVoByUid() {
        List<CartVO> list = cartMapper.findVOByUid(11);
        for(CartVO c : list) {
            System.out.println(c);
        }
    }

}
