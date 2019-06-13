package com.bankTransfer.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bankTransfer.service.IBaseService;
import com.bankTransfer.util.RequireIdentity;
import com.bankTransfer.util.UserContext;
@Component
public class IdentityCheckInterceptor extends  HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//判断handler使用时Controller中一个处理请求的方法O
		if(handler instanceof HandlerMethod){
			HandlerMethod handlerMethod = (HandlerMethod)handler;
			//获取处理请求方法上的注解
			RequireIdentity rl = handlerMethod.getMethodAnnotation(RequireIdentity.class);
			
			 if (rl == null) {
	                rl = handlerMethod.getMethod().getDeclaringClass().getAnnotation(RequireIdentity.class);
	            }
			 IBaseService baseService = WebApplicationContextUtils
	          .getRequiredWebApplicationContext(request.getServletContext())
	          .getBean(IBaseService.class);
			//判断是否有该注解
			if(rl != null && UserContext.getCurrent() != null && baseService.checkUser(UserContext.getCurrent().getId()) == null){ 
				response.sendRedirect("/accountapplication.html");
				return false;
			}
		}
		return super.preHandle(request, response, handler);
	}
}
