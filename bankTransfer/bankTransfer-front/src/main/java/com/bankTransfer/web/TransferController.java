package com.bankTransfer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankTransfer.service.ITransferService;
import com.bankTransfer.util.JsonResult;

@RestController
public class TransferController {
	
	@Autowired 
	private ITransferService transferService;
	
	@PostMapping("singleTransfer")
	public JsonResult singleTransfer() {
		JsonResult jsonResult = new JsonResult();
		
		return jsonResult;
	}
}
