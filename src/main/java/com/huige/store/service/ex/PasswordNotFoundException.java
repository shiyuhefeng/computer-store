package com.huige.store.service.ex;

/**
 * 密码校验异常
 */
public class PasswordNotFoundException extends ServiceException {
    public PasswordNotFoundException() {
        super();
    }

    public PasswordNotFoundException(String message) {
        super(message);
    }

    public PasswordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordNotFoundException(Throwable cause) {
        super(cause);
    }

    protected PasswordNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
