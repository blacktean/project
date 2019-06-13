package com.bankTransfer.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankTransfer.mapper.CardMapper;
import com.bankTransfer.mapper.LogininfoMapper;
import com.bankTransfer.pojo.Card;
import com.bankTransfer.pojo.Card_VO;
import com.bankTransfer.service.ISetupService;
import com.bankTransfer.util.MD5;
@Service
public class SetupServiceImpl implements ISetupService {
	
	@Autowired
	LogininfoMapper logininfoMapper;
	@Autowired
	CardMapper cardMapper;
	
	@Override
	public boolean setup(String password,Integer id) {
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
		return logininfoMapper.checkPassword(MD5.encode(password),id)!=null;
	}

	@Override
	public List<Card> queryCardIdByUserId(Integer user_id) {
		// TODO Auto-generated method stub
		return cardMapper.queryCardIdByUserId(user_id);
	}

	@Override
	public boolean update(Card_VO card_vo) {
		boolean rs = false;
		if(card_vo == null) {
			return false;
		}
		String id_card =card_vo.getId_card();
		BigDecimal  max_price= card_vo.getMax_price();
		int row = cardMapper.update(id_card,max_price);
		System.err.println("===============受影响行数为:"+row);
		rs = row > 0;
		return rs;
	}

}
