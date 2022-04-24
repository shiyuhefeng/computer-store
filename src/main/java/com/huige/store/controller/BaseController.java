package com.huige.store.controller;

import com.huige.store.controller.ex.*;
import com.huige.store.enums.AppHttpCodeEnum;
import com.huige.store.service.ex.*;
import com.huige.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * 控制层类的基类
 */
public class BaseController {
    /**
     * 操作成功的状态码
     */
    public static final int OK = 200;

    // 请求处理方法。这个方法的返回值就是需要传递给前端数据
    // 自动将异常对象传递给此方法的参数列表上
    // 当前项目中产生了异常，被统一拦截到此方法之中，这个方法此时就充当的是请求处理方法，方法的返回值直接给前端！
    @ExceptionHandler(ServiceException.class)   // 用于统一处理抛出的异常
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);
        if(e instanceof UsernameDuplicateException) {
            // 用户名被占用异常
            result.setState(AppHttpCodeEnum.USERNAME_DUPLICATE_CODE);
            result.setMessage(AppHttpCodeEnum.USERNAME_DUPLICATE_MSG);
        } else if(e instanceof InsertException) {
            // 插入异常
            result.setState(AppHttpCodeEnum.INSERT_DUPLICATE_CODE);
            result.setMessage(AppHttpCodeEnum.INSERT_DUPLICATE_MSG);
        } else if(e instanceof UsernameNotFoundException) {
            // 用户未找到异常
            result.setState(AppHttpCodeEnum.USERNAME_NOTFOUND_CODE);
            result.setMessage(AppHttpCodeEnum.USERNAME_NOTFOUND_MSG);
        } else if(e instanceof PasswordNotFoundException) {
            // 密码错误异常
            result.setState(AppHttpCodeEnum.PASSWORD_NOTFOUND_CODE);
            result.setMessage(AppHttpCodeEnum.PASSWORD_NOTFOUND_MSG);
        } else if(e instanceof FileUploadIOException) {
            result.setState(AppHttpCodeEnum.FILE_UPLOAD_IO_CODE);
            result.setMessage(AppHttpCodeEnum.FILE_UPLOAD_IO_MSG);
        } else if(e instanceof FileEmptyException) {
            result.setState(AppHttpCodeEnum.FILE_EMPTY_CODE);
            result.setMessage(AppHttpCodeEnum.FILE_EMPTY_MSG);
        } else if(e instanceof FileSizeException) {
            result.setState(AppHttpCodeEnum.FILE_SIZE_CODE);
            result.setMessage(AppHttpCodeEnum.FILE_SIZE_MSG);
        } else if(e instanceof FileStateException) {
            result.setState(AppHttpCodeEnum.FILE_STATE_CODE);
            result.setMessage(AppHttpCodeEnum.FILE_STATE_MSG);
        } else if(e instanceof FileTypeException) {
            result.setState(AppHttpCodeEnum.FILE_TYPE_CODE);
            result.setMessage(AppHttpCodeEnum.FILE_TYPE_MSG);
        } else if(e instanceof AddressCountLimitException) {
            result.setState(4003);
            result.setMessage("用户的收货地址超出上限的异常");
        } else if(e instanceof AddressNotFoundException) {
            result.setState(4004);
            result.setMessage("用户的收货地址数据不存在异常");
        } else if(e instanceof AccessDeniedException) {
            result.setState(4005);
            result.setMessage("用户的收货地址超出上限的异常");
        } else if(e instanceof CartNotFoundException) {
            result.setState(4007);
            result.setMessage("购物车数据不存在异常");
        }

        return result;
    }

    /**
     * 获取session对象中的uid
     * @param session session对象
     * @return 当前登录的用户uid的值
     */
    protected final Integer getuidFromSession(HttpSession session) {
        return Integer.parseInt(session.getAttribute("uid").toString());
    }

    /**
     * 获取当前登录用户的username
     * @param session session对象
     * @return 当前登录的用户名
     */
    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }
}
