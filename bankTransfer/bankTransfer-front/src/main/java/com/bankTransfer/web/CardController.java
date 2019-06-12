package com.bankTransfer.web;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bankTransfer.pojo.Card;
import com.bankTransfer.pojo.Currency;
import com.bankTransfer.pojo.User;
import com.bankTransfer.service.IBaseService;
import com.bankTransfer.service.ICardService;
import com.bankTransfer.util.JsonResult;
import com.bankTransfer.util.RandomUtil;
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
	public JsonResult addCard(String id_card,String start_place,String currency,String password,HttpServletRequest request) {
		JsonResult json = new JsonResult();
		id_card = "45092319960628"+RandomUtil.getFixLenthString(5);
		User user = (User) request.getAttribute("user");
		iCardService.addCard(id_card, start_place, currency, password,user.getUser_id().toString());
		return json;
	}
	
	//根据id_card查对应银行
	@RequestMapping("/queryCardByIdCard")
	public ModelAndView queryCardByIdCard(String id_card) {
		ModelAndView model = new ModelAndView();
		Card card = iCardService.queryCardByIdCard(id_card);
		model.addObject("card", card);
		model.setViewName("mingxi");
		return model;
	}
	
	// 判断该银行卡是否存在
		@RequestMapping("/checkid_card2")
		@ResponseBody
		public boolean checkid_card(String id_card2) {
			return iCardService.getCountByIdCard(id_card2);

		}
}
