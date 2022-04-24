package com.huige.store.controller;

import com.huige.store.entity.User;
import com.huige.store.service.IUserService;
import com.huige.store.util.FileUpload;
import com.huige.store.util.FileUploadUtil;
import com.huige.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/reg")
    public JsonResult<Void> reg(User user) {
        // 创建响应结果对象
        userService.reg(user);
        return new JsonResult<>(OK, "用户注册成功");
    }

    /**
     * 约定大于配置：开发思想来完成，省略大量的配置甚至注解的编写
     * 接口数据方式，请求处理方法的参数列表设置为非pojo类型
     * SpringBoot会直接将请求的参数名和方法的参数名直接进行比较   如果名称相同则自动完成值的依赖注入
     */
    @RequestMapping("/login")
    public JsonResult<User> login(String username, String password, HttpSession session) {
        User data = userService.login(username, password);
        // 向session对象中完成数据的绑定(session全局的)
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());
        return new JsonResult<User>(OK, "用户登录成功", data);
    }

    @RequestMapping("/change_password")
    public JsonResult<User> changePassword(String oldPassword, String newPassword, HttpSession session) {
        userService.changePassword(getuidFromSession(session), getUsernameFromSession(session), oldPassword, newPassword);
        return new JsonResult<User>(OK, "更改成功");
    }

    @RequestMapping("/get_by_uid")
    public JsonResult<User> getByUid(HttpSession session) {
        User data = userService.getByUid(getuidFromSession(session));
        return new JsonResult<>(OK, "操作成功", data);
    }

    @RequestMapping("/change_info")
    public JsonResult<User> changeInfo(HttpSession session, User user) {
        // user对象有四部分的数据: username, phone, email, gender
        // uid数据需要再次封装到user对象中
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(uid, username, user);
        return new JsonResult<>(OK);
    }

    /**
     * MultipartFile接口是SpringMVC提供的一个接口，这个接口为我们包装了
     * 获取文件类型的数据(任何类型的文件)
     * SpringBoot整合了SpringMVC，只需要在处理请求的方法参数列表上声明一个参数类型为MultipartFile的参数，SpringBoot自动将文件上的数据
     * 赋值给这个参数
     * <p>
     * -  @RequestParam 表示请求中的参数，将请求中的参数注入请求处理方法的某个参数上
     * 如果参数不一致，可以@RequestParam参数进行标记和映射
     */
    @PostMapping("/change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session, @RequestParam("file") MultipartFile file) {
        String avatar = FileUpload.upload(file);
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.updateAvatarByUid(uid, avatar, username);
        System.out.println(avatar);
        // 返回文件的字符串给前端，用于展示头像
        return new JsonResult<>(OK, "文件上传成功", avatar);
    }
}
