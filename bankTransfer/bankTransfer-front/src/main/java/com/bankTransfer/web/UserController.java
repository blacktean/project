package com.bankTransfer.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.bankTransfer.pojo.Card;
import com.bankTransfer.pojo.Currency;
import com.bankTransfer.pojo.User;
import com.bankTransfer.service.IBaseService;
import com.bankTransfer.service.ICardService;
import com.bankTransfer.service.IUserService;
import com.bankTransfer.util.JsonResult;
import com.bankTransfer.util.RequireIdentity;
import com.bankTransfer.util.RequireLogin;
import com.bankTransfer.util.UserContext;


@Controller
public class UserController {
	@Autowired
	private IUserService iUserService;
	@Autowired
	private ICardService iCardService;
	@Autowired
	private IBaseService baseService;
	
	/*
	 * @RequestMapping("/queryUserOne")
	 * 
	 * @RequireLogin public ModelAndView queryUserOne() { ModelAndView model = new
	 * ModelAndView(); int user_id = UserContext.getCurrent().getId(); User user =
	 * iUserService.queryUserByUser_name(user_id); List<Currency> currencys =
	 * baseService.queryCurrency(); //System.out.println(user); Integer userid =
	 * UserContext.getCurrent().getId(); List<Card> cards =
	 * iCardService.queryCardByUserId(userid); //System.out.println(cards);
	 * model.addObject("user", user); model.addObject("cards", cards);
	 * model.addObject("currencys", currencys); model.setViewName("myaccount");
	 * return model; }
	 */
	
	@RequestMapping("/queryUserOne")
	@RequireLogin
	@RequireIdentity
	public String queryUserOne(HttpSession session) {
		int user_id = UserContext.getCurrent().getId();
		User user = iUserService.queryUserByUser_name(user_id);
		List<Currency> currencys = baseService.queryCurrency();
		//System.out.println(user);
		Integer userid = UserContext.getCurrent().getId();
		List<Card> cards = iCardService.queryCardByUserId(userid);
		//System.out.println(cards);
		session.setAttribute("user", user);
		session.setAttribute("cards", cards);
		session.setAttribute("currencys", currencys); 
		return "myaccount";
	}
	
	@RequestMapping("/updateUserOne")
	public String updateUserOne(HttpSession session) {
		
		return "updateUserOne";
	}
	
	@RequestMapping("/UpdateUserDetails")
	@ResponseBody
	public JsonResult UpdateUserDetails(User user) {
		JsonResult json = new JsonResult();
		System.err.println("当前修改用户"+user);
		iUserService.updateUserByUser_id(user);
		
		return json;
	}
	
}