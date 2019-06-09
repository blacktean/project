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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}*/

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//判断handler使用时Controller中一个处理请求的方法O
		if(handler instanceof HandlerMethod){
			HandlerMethod handlerMethod = (HandlerMethod)handler;
			//获取处理请求方法上的注解
			RequireLogin rl = handlerMethod.getMethodAnnotation(RequireLogin.class);
			//RequireRealauth rr = handlerMethod.getMethodAnnotation(RequireRealauth.class);
			//判断是否有该注解
			if(rl != null && UserContext.getCurrent() == null ){ //说明该方法需要访问控制, 如果未登录, 跳转到login.html
				response.sendRedirect("/login.html");
				return false;
			}
//			//判断是否开户
//			if(rr != null && UserContext.getCurrent() == null) {//说明该方法需要访问控制, 如果未登录, 跳转到accountApplication.html
//				response.sendRedirect("/accountapplication.html");
//				return false;
//			}
		}
		return super.preHandle(request, response, handler);
	}
}
