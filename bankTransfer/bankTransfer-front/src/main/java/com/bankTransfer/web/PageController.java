package com.bankTransfer.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bankTransfer.pojo.Card;
import com.bankTransfer.pojo.CardType;
import com.bankTransfer.pojo.Currency;
import com.bankTransfer.pojo.Document;
import com.bankTransfer.service.IBaseService;
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
	public String toBatchTransfer(Model model) {
		List<Card> cards = baseService.queryCardByUserId(UserContext.getCurrent().getId());
		model.addAttribute("cards", cards);
		return "forward:/batchTransfer.html";
	}
	@GetMapping("toCrossBorderTransfer")
	public String toCrossBorderTransfer(Model model) {
		List<Currency> currencys = baseService.queryCurrency();
		List<Card> cards = baseService.queryCardByUserId(UserContext.getCurrent().getId());
		List<CardType> cardTypes = baseService.queryCardType();
		model.addAttribute("currencys", currencys);
		model.addAttribute("cards", cards);
		model.addAttribute("cardTypes", cardTypes);
		return "forward:/crossBorderTransfer.html";
	}
	
	@GetMapping("toRegisterAccountTransfer")
	public String toRegisterAccountTransfer(Model model) {
		List<Card> cards = baseService.queryCardByUserId(UserContext.getCurrent().getId());
		List<Document> documents = baseService.queryDocument();
		model.addAttribute("cards", cards);
		model.addAttribute("documents", documents);
		return "forward:/registerAccountTransfer.html";
	}
	
	@GetMapping("toSingleTransfer")
	public String toSingleTransfer(Model model) {
		List<Card> cards = baseService.queryCardByUserId(UserContext.getCurrent().getId());
		List<CardType> cardTypes = baseService.queryCardType();
		model.addAttribute("cardTypes", cardTypes);
		model.addAttribute("cards", cards);
		return "forward:/singleTransfer.html";
	}
	
}