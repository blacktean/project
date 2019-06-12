package com.bankTransfer.util;

import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.bankTransfer.pojo.Card;
import com.bankTransfer.pojo.Logininfo;
import com.bankTransfer.pojo.User;
import com.bankTransfer.pojo.VerifyCodeVo;

/***
 * 封装获取session,以及往HttpSession存放数据
 * @author 康志龙
 *@version  创建时间   2019年6月2日  上午11:16:14
 */
public class UserContext {
	private static String USER_IN_SESSION = "logininfo";
	private static String VERIFYCODE_IN_SESSION = "verifycodeInSession";
	private static String USER_SESSION = "user";
//	private static String Card_SESSION = "cards";
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
	/**
	 * 存放用户对象
	 */
	public static User getUser(){
		return (User) getHttpSession().getAttribute(USER_SESSION);
	}
	
	
	public static void setUser(User user){
		getHttpSession().setAttribute(USER_SESSION, user);
	}
	
	/**
//	 * 存放银行卡对象
//	 */
//	public static List<Card> getCard(){
//		return (List<Card>) getHttpSession().getAttribute(Card_SESSION);
//	}
//	
//	
//	public static void setCards(List<Card> cards){
//		getHttpSession().setAttribute(Card_SESSION, cards);
//	}
}
