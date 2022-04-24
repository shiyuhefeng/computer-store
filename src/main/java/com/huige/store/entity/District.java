package com.huige.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author huige
 * Create on 2022/4/13 20:24
 * 省市区实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class District extends BaseEntity implements Serializable {

    private Integer id;
    private String parent;
    private String code;
    private String name;
}
