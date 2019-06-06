package com.bankTransfer.util;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bankTransfer.pojo.Logininfo;
import com.bankTransfer.pojo.VerifyCodeVo;

/***
 * 封装获取session,以及往HttpSession存放数据
 * @author 康志龙
 *@version  创建时间   2019年6月2日  上午11:16:14
 */
public class UserContext {
	private static String USER_IN_SESSION = "logininfo";
	private static String VERIFYCODE_IN_SESSION = "verifycodeInSession";

	/**
	 * 获取HttpSession的方法
	 */
	public  static HttpSession  getHttpSession(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
	}
	
	public static  Logininfo  getCurrent(){
		return (Logininfo) getHttpSession().getAttribute(USER_IN_SESSION);
	}
	
	
	public static void setCurrent(Logininfo logininfo){
		getHttpSession().setAttribute(USER_IN_SESSION, logininfo);
	}
	
	/**
	 * 存放短信验证码
	 */
	public static  VerifyCodeVo  getCurrentVerifyCodeVo(){
		return (VerifyCodeVo) getHttpSession().getAttribute(VERIFYCODE_IN_SESSION);
	}
	
	
	public static void setCurrentVerifyCodeVo(VerifyCodeVo verifyCodeVo){
		getHttpSession().setAttribute(VERIFYCODE_IN_SESSION, verifyCodeVo);
	}
	
	
}
