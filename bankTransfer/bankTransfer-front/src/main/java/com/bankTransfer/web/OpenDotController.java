package com.bankTransfer.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bankTransfer.pojo.Card;
import com.bankTransfer.pojo.OpenDot;
import com.bankTransfer.service.ICardService;
import com.bankTransfer.service.IOpenDotService;

@Controller
public class OpenDotController {
	@Autowired
	private IOpenDotService iOpenDotService;
	@Autowired
	private ICardService iCardService;
	
	@RequestMapping("/showIOpenDot")
	public String showIOpenDot(HttpSession session,HttpServletRequest request,String id_card) {
		Card card = iCardService.queryCardByIdCard(id_card);
		System.err.println(card);
		request.setAttribute("card", card);
		int dot_number = Integer.parseInt(card.getStart_place_id());
		OpenDot openDot = iOpenDotService.queryOpenDotByDot_number(dot_number);
		request.setAttribute("openDot", openDot);
		return "kaihuwangdian";
	}
}
