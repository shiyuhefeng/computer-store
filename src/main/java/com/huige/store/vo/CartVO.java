package com.huige.store.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.io.Serializable;

/**
 * @Author huige
 * Create on 2022/4/15 14:33
 * 购物车数据的VO类(value Object)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartVO implements Serializable {

    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;
    private String title;
    private String image;
    private Long realPrice;

}
