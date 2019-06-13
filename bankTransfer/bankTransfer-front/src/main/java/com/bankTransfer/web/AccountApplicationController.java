package com.bankTransfer.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bankTransfer.pojo.User;
import com.bankTransfer.service.IAccountApplicationService;
import com.bankTransfer.util.JsonResult;
import com.bankTransfer.util.RequireLogin;
import com.bankTransfer.util.UserContext;

@RestController
public class AccountApplicationController {
	@Autowired
	private IAccountApplicationService accountApplicationService;
	
	@RequestMapping("/accountApplicationHtml")
	@RequireLogin
	public ModelAndView accountApplicationHtml(HttpSession session) {
		ModelAndView model = new ModelAndView();
		//查询是否开户
		boolean isOK = accountApplicationService.queryCardById(UserContext.getCurrent().getId());
		session.setAttribute("isHaveAccount", isOK);
		
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
