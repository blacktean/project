package com.bankTransfer.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bankTransfer.service.ITransferService;

@Component
public class HandleTransfer {
	
	@Autowired
	private   ITransferService transferService;
	
	
	public   boolean Handle() {
		try {
			transferService.handleTransfer();
			return true;
		}catch(Exception e) {
			return false;
		}
	}
}
