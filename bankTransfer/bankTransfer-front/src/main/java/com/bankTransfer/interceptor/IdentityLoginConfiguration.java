package com.bankTransfer.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class IdentityLoginConfiguration implements WebMvcConfigurer {
	@Bean
	IdentityCheckInterceptor getMyInterceptor() {
		return new IdentityCheckInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 注册拦截器
		IdentityCheckInterceptor identityCheckInterceptor = new IdentityCheckInterceptor();
		InterceptorRegistration loginRegistry = registry.addInterceptor(identityCheckInterceptor);
		// 拦截路径
		loginRegistry.addPathPatterns("/**");
		// 排除路径
		loginRegistry.excludePathPatterns("/");
		loginRegistry.excludePathPatterns("/login");
		loginRegistry.excludePathPatterns("/loginout");
		loginRegistry.excludePathPatterns("/logininfo");
		loginRegistry.excludePathPatterns("/toHeader");
		loginRegistry.excludePathPatterns("/checkUsername");
		loginRegistry.excludePathPatterns("/register");
		loginRegistry.excludePathPatterns("/accountapplication.html");
		// 排除资源请求
		loginRegistry.excludePathPatterns("/css/*.css");
		loginRegistry.excludePathPatterns("/js/*.js");
		loginRegistry.excludePathPatterns("/fonts/*");
		loginRegistry.excludePathPatterns("/images/*");

	}
}
