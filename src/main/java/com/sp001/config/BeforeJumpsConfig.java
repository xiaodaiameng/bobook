package com.sp001.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration      // 扩展 springmvc
public class BeforeJumpsConfig implements WebMvcConfigurer {
    @Override // 视图跳转
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("login");
    }
    @Override // 拦截器
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new UserSession()).addPathPatterns("/**")
                .excludePathPatterns("/", "/login", "/register", "/css/**", "/js/**", "/images/**");
    }
}
