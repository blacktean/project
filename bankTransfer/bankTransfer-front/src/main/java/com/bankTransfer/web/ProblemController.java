package com.bankTransfer.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bankTransfer.pojo.Currency;
import com.bankTransfer.pojo.JsonRate;
import com.bankTransfer.pojo.Problem;
import com.bankTransfer.service.IProblemService;
import com.bankTransfer.util.HuiLvData;

@Controller
public class ProblemController {
	@Autowired
	IProblemService problemService;

	@RequestMapping("/huilv")
	@ResponseBody
	public HuiLvData huilv(String from,String to ,Float money,HttpSession session) {
		HuiLvData js = new HuiLvData();
		JsonRate jr =problemService.huilv( from, to , money);
		System.err.println(">>>>>>>>>>>>>>"+jr);
		if(jr!=null) {
			js.setMsg(jr.getCamount().toString());
			js.setFrom(from);
			js.setTo(to);
			js.setMoney(money);
			System.err.println("<<<<<<<<<<<<<<<<<<<<<<"+js.getMsg());
			return js;
		}
		js.setSuccess(false);
		js.setMsg("转换出错");
		return js;
	}
	
	
	@RequestMapping("/problemList")
	public ModelAndView problemList() {		
		ModelAndView modelAndView = new ModelAndView();
		List<Problem> problems = problemService.queryAllProblem();
		List<Currency> currency = problemService.queryAllCurrency();
		modelAndView.addObject("problem", problems);
		modelAndView.addObject("currencys", currency);
		System.err.println("==============>"+problems);
		System.err.println("==============>"+currency);
		modelAndView.setViewName("index");
		return modelAndView;
	}

}
