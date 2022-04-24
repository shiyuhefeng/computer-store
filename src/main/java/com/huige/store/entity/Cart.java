package com.huige.store.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author huige
 * Create on 2022/4/15 11:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_cart")
public class Cart extends BaseEntity {

    // 商品的id
    @TableId(type = IdType.AUTO)
    private Integer cid;
    // 用户的id
    private Integer uid;
    // 商品的编号
    private Integer pid;
    // 商品价格
    private Long price;
    // 商品数量
    private Integer num;
}
