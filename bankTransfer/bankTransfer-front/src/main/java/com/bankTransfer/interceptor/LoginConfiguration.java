package com.bankTransfer.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class LoginConfiguration implements WebMvcConfigurer {
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
		LoginCheckInterceptor loginInterceptor = new LoginCheckInterceptor();
        InterceptorRegistration loginRegistry = registry.addInterceptor(loginInterceptor);
        // 拦截路径
        loginRegistry.addPathPatterns("/**");
        // 排除路径
        loginRegistry.excludePathPatterns("/");
        loginRegistry.excludePathPatterns("/login");
        loginRegistry.excludePathPatterns("/loginout");
        // 排除资源请求
        loginRegistry.excludePathPatterns("/css/*.css");
        loginRegistry.excludePathPatterns("/js/*.js");
        loginRegistry.excludePathPatterns("/fonts/*");
        loginRegistry.excludePathPatterns("/images/*");
       
    }
}
