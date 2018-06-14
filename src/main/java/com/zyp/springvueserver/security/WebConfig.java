package com.zyp.springvueserver.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //添加拦截器
//        registry.addInterceptor(new JwtInterceptor()).excludePathPatterns("/user/login");
//    }




}
