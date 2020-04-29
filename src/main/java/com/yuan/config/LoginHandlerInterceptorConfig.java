package com.yuan.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptorConfig implements HandlerInterceptor {
    //自定义拦截器
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object  user =  request.getSession().getAttribute("user");

        if(user!=null){
            return true;
        }else {
            request.setAttribute("msg","没有权限请先登录");
            request.getRequestDispatcher("/templates/login.html").forward(request,response);
            return false;
        }
    }
}
