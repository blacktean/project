package com.bankTransfer.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bankTransfer.pojo.Card;
import com.bankTransfer.pojo.User;
import com.bankTransfer.service.ICardService;

@Controller
public class CardController {  
	@Autowired
	private ICardService iCardService;
	
	@RequestMapping("/queryCardOne")
	public String queryCardOne(String user_id,HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<Card> cards = iCardService.queryCardByUserId(user.getId());
		session.setAttribute("cards", cards);
		return "test";
	}
}
