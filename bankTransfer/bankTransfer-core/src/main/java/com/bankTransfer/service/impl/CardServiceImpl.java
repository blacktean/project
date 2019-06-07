package com.bankTransfer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankTransfer.mapper.CardMapper;
import com.bankTransfer.pojo.Card;
import com.bankTransfer.service.ICardService;
import com.bankTransfer.util.MD5;
@Service
public class CardServiceImpl implements ICardService {
	
	@Autowired
	private CardMapper cardMapper;
	@Override
	public List<Card> queryCardByUserIdAndMajorCard(String user_id, String major_card) {
		return cardMapper.queryCardByUserIdAndMajorCard(user_id, major_card);
	}
	@Override
	public boolean queryCard(String user_id, String major_card) {
		if(cardMapper.queryCard(user_id, major_card)>0) {
			return true;
		}
		return false;
	}
	@Override
	public String queryUserName(Integer id) {
		return cardMapper.queryUserName(id);
	}
	@Override
	public boolean getCountByIdCard(String id_card) {
		
		return cardMapper.getCountByIdCard(id_card)>0;
	}
	@Override
	public boolean checkPailPWD(String user_id, String pailPWD) {
		if(cardMapper.checkPailPWD(user_id, MD5.encode(pailPWD))>0) {
			return true;
		}
		return false;
	}
	

}
