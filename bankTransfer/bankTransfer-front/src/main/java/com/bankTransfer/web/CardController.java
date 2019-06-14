package com.bankTransfer.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bankTransfer.pojo.Card;
import com.bankTransfer.pojo.User;
import com.bankTransfer.service.ICardService;
import com.bankTransfer.util.JsonResult;
import com.bankTransfer.util.MD5;
import com.bankTransfer.util.UserContext;

@Controller
public class CardController {  
	@Autowired
	private ICardService iCardService;


	//@RequestMapping("/queryCardAll")
	//public String queryCardAll() {
	//	return "myaccount";
	//}
	
	//增加银行卡
	@ResponseBody
	@RequestMapping("/addCard")
	public JsonResult addCard(Card card,HttpServletRequest request) {
		JsonResult json = new JsonResult();
		//随机生成卡号
		String mathcard = String.valueOf((int)((Math.random()*9+1)*10000));
		card.setId_card("45092319960628"+mathcard);
		card.setUser_id(String.valueOf(UserContext.getCurrent().getId()));
		card.setPassword(MD5.encode(card.getPassword()));
		System.err.println(card);
		//UserContext.getCurrent().getId();
		iCardService.addCard(card);
		return json;
	}
	
	//根据id_card查对应银行
//	@RequestMapping("/queryCardByIdCard")
//	public ModelAndView queryCardByIdCard(String id_card) {
//		ModelAndView model = new ModelAndView();
//		Card card = iCardService.queryCardByIdCard(id_card);
//		model.addObject("card", card);
//		model.setViewName("mingxi");
//		return model;
//	}
	@RequestMapping("/queryCardByIdCard")
	public String queryCardByIdCard(String id_card,HttpSession session) {
		Card card = iCardService.queryCardByIdCard(id_card);
		session.setAttribute("card", card);
		return "mingxi";
	}
	
	@RequestMapping("/queryCardByIdCardAll")
	public String queryCardByIdCardAll(String id_card,HttpServletRequest request) {
		Card card = iCardService.queryCardByIdCard(id_card);
		System.err.println("卡片详细信息"+card);
		request.setAttribute("card", card);
		return "gengduo";
	}
	
	// 判断该银行卡是否存在
		@RequestMapping("/checkid_card2")
		@ResponseBody
		public boolean checkid_card(String id_card2) {
			return iCardService.getCountByIdCard(id_card2);

		}
}
