package com.bankTransfer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bankTransfer.service.ISetupService;
import com.bankTransfer.util.JsonResult;
import com.bankTransfer.util.UserContext;

@Controller
public class SetupController {
	
	@Autowired
	ISetupService setupService;
	@RequestMapping("/checkPassword")
	public JsonResult checkPassword(String password) {
		JsonResult js = new JsonResult();
		Integer id = UserContext.getCurrent().getId();
		if(setupService.checkPassword(password,id)) {;
		return js;
		}
		js.setSuccess(false);
		js.setMsg("密码错误");
		return js;
	}
	
	
	public JsonResult setup(String password,Double max) {
		JsonResult js = new JsonResult();
		Integer id = UserContext.getCurrent().getId();
		if(setupService.setup(password,max,id)) {;
		return js;
		}
		js.setSuccess(false);
		js.setMsg("修改失败");
		return js;
	}
}
