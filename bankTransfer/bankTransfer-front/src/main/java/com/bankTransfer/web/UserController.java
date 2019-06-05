package com.bankTransfer.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bankTransfer.pojo.User;
import com.bankTransfer.service.IUserService;


@Controller
public class UserController {
	@Autowired
	private IUserService iUserService;
	
	@RequestMapping("/queryUserOne")
	public String queryUserOne(String user_name,HttpSession session) {
		System.err.println(user_name);
		User user = iUserService.queryUserByUser_name(user_name);
		session.setAttribute("user", user);
		System.out.println(user);
		return "test";
	}
}
