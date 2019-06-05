package com.bankTransfer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bankTransfer.pojo.Logininfo;
import com.bankTransfer.service.IRegisterService;
import com.bankTransfer.service.IVerifyCodeService;
import com.bankTransfer.util.JsonResult;
import com.bankTransfer.util.UserContext;

/**
 * 注册
 * @author 康志龙
 *@version  创建时间   2019年6月4日  下午8:38:33
 */
@Controller
public class RegisterController {
	
	@Autowired
	IRegisterService registerService;
	@Autowired
	private IVerifyCodeService verifyCodeService;
	
	/**
	 * 检查用户名是否已经被注册
	 * @param regist_account   用户名
	 * @return
	 */
	@RequestMapping("/checkUsername")
	@ResponseBody
	public boolean checkUsername(String regist_account) {
		return registerService.checkUsername(regist_account);
	}

	/**
	 * 发送手机验证码
	 * @param phoneNumber 电话号码
	 * @return
	 */
	@PostMapping("/sendVerifyCode")
	@ResponseBody
	public JsonResult sendVerifyCode(String regist_phone){
		JsonResult js = new JsonResult();
		try{
			verifyCodeService.sendVerifyCode(regist_phone);
			js.setMsg(UserContext.getCurrentVerifyCodeVo().getVerifyCode());;
		}catch(RuntimeException e){
			js.setSuccess(false);
			js.setMsg(e.getMessage());
		}
		
		return js;		
	}
	/**
	 * 验证手机验证码并注册账号
	 * @param logininfo 注册信息
	 * @return
	 */
	@RequestMapping("/register")
	@ResponseBody
	public JsonResult register(String regist_account, String regist_password1, String regist_phone,String regist_vcode) {
		JsonResult js = new JsonResult();
//		String tel = logininfo.getTelphone();
//		
//		registerService.register(logininfo);
	if(verifyCodeService.checkVerifyCode(regist_phone, regist_vcode)) {
		if(registerService.register(regist_account,regist_password1,regist_phone,Logininfo.STATE_NORMAL,Logininfo.USERTYPE_NORMAL)) {
			
			return js;
		}
		js.setSuccess(false);
		js.setMsg("未知注册失败请联系客服");
	}
		js.setSuccess(false);
		js.setMsg("验证码错误注册失败");
		return js;
	}
}
