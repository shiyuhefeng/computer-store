package com.huige.store.util;

import lombok.Data;

import java.io.Serializable;

/**
 * JSON格式的数据进行响应
 */
@Data
public class JsonResult<E> implements Serializable {

    // 状态码
    private Integer state;

    // 描述信息
    private String message;

    // 数据类型
    private E data;

    public JsonResult() {
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }

    public JsonResult(Integer state, String message) {
        this.message = message;
        this.state = state;
    }

    public JsonResult(Integer state, String message, E data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }
}
