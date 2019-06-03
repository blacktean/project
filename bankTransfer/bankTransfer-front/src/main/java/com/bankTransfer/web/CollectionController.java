package com.bankTransfer.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bankTransfer.util.JsonResult;
import com.bankTransfer.util.MsgUtil;
import com.bankTransfer.util.RequireLogin;

@Controller
public class CollectionController {
	
	//跳转到新建归集
	@RequestMapping("/newcollection")
	//@RequireLogin
	public String NewCollection() {
		
		
		return "newcollection";
		
	}
	//跳转到我的归集
		@RequestMapping("/mycollection")
		//@RequireLogin
		public String MyCollection() {
			
			
			return "mycollection";
			
		}
	//验证短信是否发送成功
		@RequestMapping("/checkphoneNumberById")
		@ResponseBody
		//@RequireLogin
		public JsonResult checkphoneNumberById(String debit_tel,HttpSession session) {
			JsonResult json = new JsonResult();
			System.err.println(debit_tel);	
			//调用发送手机短信的接口
			if(MsgUtil.mobileQuery(debit_tel)) {
				//true:发送成功  
				json.setSuccess(true);
				return json;
			}				  
				json.setSuccess(false);
				json.setMsg("手机号错误 发送失败");	
			return json;	
		}
		
		//验证验证码是否正确
		@RequestMapping("/checkverifyCode")
		@ResponseBody
		//@RequireLogin
		public JsonResult checkverifyCode(String verifyCode,HttpSession session) {
			//如果发送验证码成功 从域中拿到随机发送的验证码
			String mathCode = (String) session.getAttribute("mathCode");
			JsonResult json = new JsonResult();
			System.err.println("传过来的验证码"+verifyCode);
			System.err.println("随机生成的验证码"+mathCode);
			if(verifyCode.equals(mathCode)) {
				//如果验证码相等 返回true
				json.setSuccess(true);
				json.setMsg("验证码正确");
				return json;
			}	
			json.setSuccess(false);
			json.setMsg("验证码错误,请输入正确的验证码");
			return json;		
		}
	
}
