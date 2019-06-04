package com.bankTransfer.web;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankTransfer.pojo.Card;
import com.bankTransfer.pojo.JsonRate;
import com.bankTransfer.service.IBaseService;
import com.bankTransfer.util.APIUtils;

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


}
