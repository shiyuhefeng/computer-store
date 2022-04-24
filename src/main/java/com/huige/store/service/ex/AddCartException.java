package com.huige.store.service.ex;

/**
 * @Author huige
 * Create on 2022/4/15 11:24
 */
public class AddCartException extends ServiceException{

    public AddCartException() {
        super();
    }

    public AddCartException(String message) {
        super(message);
    }

    public AddCartException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddCartException(Throwable cause) {
        super(cause);
    }

    protected AddCartException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
