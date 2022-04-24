package com.huige.store.interceptor;

/**
 * @Author huige
 * Create on 2022/4/11 18:54
 */

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 定义一个拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 检测全局session对象中是否有uid数据，如果有则放行，如果没有则重定向到登录页面
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 处理器(url + Controller: 映射)
     * @return 如果返回值为true，表示放行当前的请求，如果返回值为false，表示拦截当前请求
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 根据session的值
        // HttpServletRequest对象来获取session对象
        Object obj = request.getSession().getAttribute("uid");
        if(obj == null) {
            response.sendRedirect("/web/login.html");
            // 结束后续的调用
            return false;
        }
        return true;
    }
}
