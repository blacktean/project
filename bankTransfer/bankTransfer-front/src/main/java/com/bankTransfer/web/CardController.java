package com.bankTransfer.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bankTransfer.pojo.Card;
import com.bankTransfer.service.ICardService;

@Controller
public class CardController {
	@Autowired
	private ICardService iCardService;
	
	@RequestMapping("/queryCardOne")
	public String queryCardOne(String user_id,HttpSession session) {
		System.err.println(user_id);
		Card card = iCardService.queryCardByUser_Id(user_id);
		session.setAttribute("card", card);
		System.out.println(card);
		return "test";
	}
}
