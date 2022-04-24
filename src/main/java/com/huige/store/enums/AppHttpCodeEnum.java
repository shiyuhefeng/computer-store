package com.huige.store.enums;

/**
 * 参数枚举
 */
public class AppHttpCodeEnum {

    public final static String USERNAME_DUPLICATE_MSG = "用户名被占用";

    public final static Integer USERNAME_DUPLICATE_CODE = 4000;

    public final static String INSERT_DUPLICATE_MSG = "注册时产生的异常";

    public final static Integer INSERT_DUPLICATE_CODE = 5000;

    public final static String USERNAME_NOTFOUND_MSG = "用户数据不存在";

    public final static Integer USERNAME_NOTFOUND_CODE = 5001;

    public final static String PASSWORD_NOTFOUND_MSG = "用户名或者密码错误";

    public final static Integer PASSWORD_NOTFOUND_CODE = 5002;

    public final static String UPDATE_EXCEPTION_MSG = "更新数据产生的异常";

    public final static Integer UPDATE_EXCEPTION_CODE = 5003;

    public final static String FILE_EMPTY_MSG = "文件为空";

    public final static Integer FILE_EMPTY_CODE = 6000;

    public final static String FILE_SIZE_MSG = "文件大小超出限制";

    public final static Integer FILE_SIZE_CODE = 6001;

    public final static String FILE_TYPE_MSG = "文件类型错误";

    public final static Integer FILE_TYPE_CODE = 6002;

    public final static String FILE_STATE_MSG = "文件状态错误";

    public final static Integer FILE_STATE_CODE = 6003;

    public final static String FILE_UPLOAD_IO_MSG = "文件读写错误";

    public final static Integer FILE_UPLOAD_IO_CODE = 6004;
}
