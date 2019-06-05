package com.bankTransfer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankTransfer.pojo.Card;
import com.bankTransfer.pojo.JsonRate;
import com.bankTransfer.service.IBaseService;
import com.bankTransfer.util.APIUtils;
import com.bankTransfer.util.JsonResult;

/**
 * 显示指定的内容
 * 
 * @author Administrator
 *
 */
@RestController
public class ShowInfoController {

	@Autowired
	private IBaseService baseService;

	@GetMapping("showBalance")
	public Card showBalance(String id_card) {
		return baseService.queryCardByCardNumber(id_card);
	}

	@GetMapping("showRate")
	public JsonRate showRate(JsonRate rate) {
		return APIUtils.getRate(rate.getCamount(), rate.getFrom(), rate.getTo());
	}
	
	@GetMapping("checkPassword")
	public JsonResult checkPassowrd(String id_card,String password) {
		System.err.println(id_card+"       "+password);
		JsonResult jsonResult = new JsonResult();
		try {
			baseService.queryCardByIdCardAndPassword(id_card,password);
		}catch(Exception e) {
			jsonResult.setSuccess(false);
			jsonResult.setMsg(e.getMessage());
		}
		return jsonResult;
	}
		
	

}
