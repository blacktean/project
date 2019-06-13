package com.bankTransfer.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bankTransfer.util.RequireLogin;
import com.bankTransfer.util.UserContext;

public class LoginCheckInterceptor extends  HandlerInterceptorAdapter {

/*	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}*/

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//判断handler使用时Controller中一个处理请求的方法O
		if(handler instanceof HandlerMethod){
			HandlerMethod handlerMethod = (HandlerMethod)handler;
			//获取处理请求方法上的注解
			RequireLogin rl = handlerMethod.getMethodAnnotation(RequireLogin.class);
			//判断是否有该注解
			if (rl == null) {
                rl = handlerMethod.getMethod().getDeclaringClass().getAnnotation(RequireLogin.class);
            }
			if(rl != null && UserContext.getCurrent() == null ){ //说明该方法需要访问控制, 如果未登录, 跳转到login.html
				response.sendRedirect("/login");
				return false;
			}
		}
		return super.preHandle(request, response, handler);
	}
}
