package com.bankTransfer.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bankTransfer.service.IBaseService;
import com.bankTransfer.service.ITransferService;

@Component
public class HandleTransfer {
	
	@Autowired
	private   ITransferService transferService;
	@Autowired
	private IBaseService baseService;
	
	public   boolean Handle() {
		try {
			transferService.handleTransfer();
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public boolean HandleIdentity() {
		return baseService.checkUser(UserContext.getCurrent().getId())!=null;
	}
}
