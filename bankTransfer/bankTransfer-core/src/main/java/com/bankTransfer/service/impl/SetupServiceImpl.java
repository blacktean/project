package com.bankTransfer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankTransfer.mapper.CardMapper;
import com.bankTransfer.mapper.LogininfoMapper;
import com.bankTransfer.service.ISetupService;
import com.bankTransfer.util.MD5;
@Service
public class SetupServiceImpl implements ISetupService {
	
	@Autowired
	LogininfoMapper logininfoMapper;
	@Autowired
	CardMapper cardMapper;
	
	@Override
	public boolean setup(String password, Double max,Integer id) {
		return logininfoMapper.setUpPassword(MD5.encode(password),id)==1;
//		boolean ok = true;
//		boolean ok2=true;
//		if(password!=null&&!password.equals("")) {
//			ok = logininfoMapper.setUpPassword(password,id)==1;
//		}
//		if(max!=null&&!max.equals("")) {
//			Integer max_price =cardMapper.queryMax_price(id);
//			if(max>=200000&&max>=max_price) {
//				throw new RuntimeException("最大额度不能大于200000和自己卡上的金额");
//			}
//			ok2 = cardMapper.setUpMax_price(max,id)==1;
//		}
//		
//		
//		return ok&&ok2;
		
	}

	@Override
	public boolean checkPassword(String password, Integer id) {
		// TODO Auto-generated method stub
		return logininfoMapper.checkPassword(password,id)==1;
	}

}
