package com.bankTransfer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bankTransfer.service.ILogininfoService;
import com.bankTransfer.util.JsonResult;
import com.bankTransfer.util.LogininfoException;
/**
 * 登陆模块
 * @author 康志龙
 *@version  创建时间   2019年6月1日  下午2:32:44
 */
@Controller
public class LogininfoController {
	@Autowired
	private ILogininfoService logininfoService;
	/***
	 * 
	 * @param login_number 用户名/电话
	 * @param login_password 密
	 * 码
	 * @return
	 */
@RequestMapping("/logininfo")	
@ResponseBody
	public JsonResult Logininfo(String login_number,String login_password ) {
	System.err.println("进来了");
	System.err.println(login_number+""+login_password);
	JsonResult js = new JsonResult();
	boolean isOk;
	try {
		isOk = logininfoService.queryByUsernameOrPhoneAndPassword(login_number, login_password, 0);
		System.err.println(isOk);
		if(!isOk) {
			js.setSuccess(false);
			js.setMsg("用户名或密码错误");
			System.err.println(isOk);
		}
	} catch (LogininfoException e) {
		js.setSuccess(false);
		js.setMsg(e.getMessage());
	}
	
	return js;
}
}
