package com.bankTransfer.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bankTransfer.pojo.Card;
import com.bankTransfer.pojo.Card_VO;
import com.bankTransfer.service.ISetupService;
import com.bankTransfer.util.JsonResult;
import com.bankTransfer.util.RequireLogin;
import com.bankTransfer.util.UserContext;

@Controller
@ResponseBody
public class SetupController {
	
	@Autowired
	ISetupService setupService;
	@PostMapping("/checkUserPassword")
	public Boolean checkPassword(String password) {
		System.out.println(UserContext.getCurrent().getId());
		return setupService.checkPassword(password, UserContext.getCurrent().getId());
	}
	
	@PostMapping("/setupPassword")
	public JsonResult setup(String password1) {
		JsonResult js = new JsonResult();
		Integer id = UserContext.getCurrent().getId();
		if(setupService.setup(password1,id)) {;
		return js;
		}
		js.setSuccess(false);
		js.setMsg("修改失败");
		return js;
	}
	@RequestMapping("/queryCardIdByUserId")
	@RequireLogin
	public ModelAndView queryCardIdByUserId() {		
		ModelAndView modelAndView = new ModelAndView();
		List<Card> cards = setupService.queryCardIdByUserId(UserContext.getCurrent().getId());
		modelAndView.addObject("cards", cards);
		modelAndView.setViewName("setup");
		return modelAndView;
	}
	
	@PostMapping("update")
	public JsonResult update(Card_VO card_vo) {
		System.err.println("===============================card_vo:"+card_vo);
		JsonResult js = new JsonResult();		
		if(setupService.update(card_vo)){
			return js;		
		};
			js.setSuccess(false);
			js.setMsg("修改失败");
		return js;
		
	}
}
