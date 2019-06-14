package com.bankTransfer.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
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
	public ModelAndView accountApplicationHtml(HttpSession session,HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		//查询是否开户
		boolean isOK = accountApplicationService.queryCardById(UserContext.getCurrent().getId());
		session.setAttribute("isHaveAccount", isOK);
		//查询是否提交申请
		boolean isHaveUser = accountApplicationService.queryUserById(UserContext.getCurrent().getId());
		int rs = 1;
		if(!isOK && isHaveUser) {
			rs = 2;
		}else if(isOK && !isHaveUser) {
			rs = 3;
		}
		System.err.println(rs);
		request.setAttribute("rs", rs);
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
