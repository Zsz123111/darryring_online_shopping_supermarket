package com.zsz.darryring.configration;

import com.zsz.darryring.intercepter.MemberIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置器
 */
@Configuration
public class IntercepterConfigration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(new MemberIntercepter()).addPathPatterns("/member**").addPathPatterns("/mycenter");
    }
}
