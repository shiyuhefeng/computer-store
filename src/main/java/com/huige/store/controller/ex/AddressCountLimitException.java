package com.huige.store.controller.ex;

import com.huige.store.service.ex.ServiceException;

/**
 * @Author huige
 * Create on 2022/4/13 19:37
 * 表示收货地址总数超出限制的异常（20次）
 */
public class AddressCountLimitException extends ServiceException {
    public AddressCountLimitException() {
        super();
    }

    public AddressCountLimitException(String message) {
        super(message);
    }

    public AddressCountLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressCountLimitException(Throwable cause) {
        super(cause);
    }

    protected AddressCountLimitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
