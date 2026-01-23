package com.sp001.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;

public class UserSession implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        // 获取Session
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");// 存键
        if (loginUser == null) {
            request.setAttribute("msg000", "没有权限，请先登录"); // 使用 request 存储 msg
            request.getRequestDispatcher("/login").forward(request, response); // 转发到登录页
            return false; // 拦截请求
        } else {
            return true;
        }
    }
}
