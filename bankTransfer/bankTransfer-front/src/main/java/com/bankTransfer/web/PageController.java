package com.bankTransfer.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bankTransfer.pojo.Card;
import com.bankTransfer.pojo.CardType;
import com.bankTransfer.pojo.Contacts;
import com.bankTransfer.pojo.Currency;
import com.bankTransfer.pojo.Document;
import com.bankTransfer.pojo.JsonCountry;
import com.bankTransfer.pojo.JsonWeather;
import com.bankTransfer.service.IBaseService;
import com.bankTransfer.util.APIUtils;
import com.bankTransfer.util.RequireIdentity;
import com.bankTransfer.util.RequireLogin;
import com.bankTransfer.util.UserContext;


/**
 * 跳转页面
 * @author Administrator
 *
 */
@Controller
public class PageController { 
	@Autowired
	private IBaseService baseService;
	
	@GetMapping("toBatchTransfer")
	@RequireLogin
	@RequireIdentity
	public String toBatchTransfer(Model model) {
		Integer id = UserContext.getCurrent().getId();
		List<Card> cards = baseService.queryCardByUserId(id);
		model.addAttribute("cards", cards);
		return "batchTransfer";
	}
	@GetMapping("toCrossBorderTransfer")
	@RequireLogin
	@RequireIdentity
	public String toCrossBorderTransfer(Model model) {
		List<Currency> currencys = baseService.queryCurrency();
		List<Card> cards = baseService.queryCardByUserId(UserContext.getCurrent().getId());
		List<CardType> cardTypes = baseService.queryCardType();
		List<Document> documents = baseService.queryDocument();
		JsonCountry jsonCountry = APIUtils.getJsonCountry();
		model.addAttribute("myCountry", jsonCountry);
		model.addAttribute("currencys", currencys);
		model.addAttribute("cards", cards);
		model.addAttribute("cardTypes", cardTypes);
		model.addAttribute("documents", documents);
		return "crossBorderTransfer";
	} 
	 
	@GetMapping("toRegisterAccountTransfer")
	@RequireLogin
	@RequireIdentity
	public String toRegisterAccountTransfer(Model model) {
		List<Card> cards = baseService.queryCardByUserId(UserContext.getCurrent().getId());
		List<Document> documents = baseService.queryDocument();
		model.addAttribute("cards", cards);
		model.addAttribute("documents", documents);
		return "registerAccountTransfer";
	}
	
	@GetMapping("toSingleTransfer")
	@RequireLogin
	@RequireIdentity
	public String toSingleTransfer(Model model) {
		List<Card> cards = baseService.queryCardByUserId(UserContext.getCurrent().getId());
		List<CardType> cardTypes = baseService.queryCardType();
		model.addAttribute("cardTypes", cardTypes);
		model.addAttribute("cards", cards);
		Contacts contacts = baseService.queryContacts(UserContext.getCurrent().getId());
		model.addAttribute("contacts", contacts);
		return "singleTransfer";
	}
	
	@RequestMapping("toHeader")
	public String toHeader(String value,Model model,HttpSession session) {
		model.addAttribute("name", value);
		if(session.getAttribute("Weathernow") == null) {
			session.setAttribute("Weathernow", APIUtils.getWeather());
		}
		if(session.getAttribute("Countrynow") == null) {
			session.setAttribute("Countrynow", APIUtils.getJsonCountry());
		}
		return "common/header";
	}
	
	@RequestMapping("login")
	public String toLogin() {
		return "login";
	}
	
	
	//注销
	@RequestMapping("/LoginOut")
	public String LoginOut(HttpSession session){
		System.err.println(session.getAttribute("logininfo"));
		//销毁session
		session.invalidate();
		
		return "redirect:/login";
		
	}
}
