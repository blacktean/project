package com.bankTransfer.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bankTransfer.pojo.Logininfo;
import com.bankTransfer.pojo.User;
import com.bankTransfer.service.IUserService;


@Controller
public class UserController {
	@Autowired
	private IUserService iUserService;
	
	@RequestMapping("/queryUserOne")
	public String queryUserOne(String user_name,HttpSession session) {
		Logininfo logini = (Logininfo) session.getAttribute("logininfo");
		User user = iUserService.queryUserByUser_name(logini.getUsername());
		session.setAttribute("user", user);
		return "test";
	}
}
