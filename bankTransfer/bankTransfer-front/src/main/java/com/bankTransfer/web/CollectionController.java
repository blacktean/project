package com.bankTransfer.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bankTransfer.util.RequireLogin;

@Controller
public class CollectionController {
	
	@RequestMapping("newcollection")
	//@RequireLogin
	public String NewCollection() {
		
		
		return "newcollection";
		
	}
}
