package com.bankTransfer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bankTransfer.pojo.User;
import com.bankTransfer.service.IAccountApplicationService;
import com.bankTransfer.util.JsonResult;
import com.bankTransfer.util.RequireLogin;

@RestController
public class AccountApplicationController {
	@Autowired
	private IAccountApplicationService accountApplicationService;
	
	@RequestMapping("/accountApplicationHtml")
	@RequireLogin
	public ModelAndView accountApplicationHtml() {
		ModelAndView model = new ModelAndView();
		model.setViewName("accountapplication");
		return model;
	}
	
	@RequestMapping("/accountApplication")
	@ResponseBody
	@RequireLogin
	public JsonResult accountApplication(User user) {
		JsonResult jsonResult = new JsonResult();
		//往数据插入一条用户数据
		try {
			accountApplicationService.addUser(user);
		} catch (Exception e) {
			jsonResult.setMsg(e.getMessage());
			jsonResult.setSuccess(false);
		}
		return jsonResult;
	}
}
