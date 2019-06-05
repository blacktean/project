package com.bankTransfer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankTransfer.mapper.CardMapper;
import com.bankTransfer.pojo.Card;
import com.bankTransfer.service.ICardService;
@Service
public class CardServiceImpl implements ICardService {
	
	@Autowired
	private CardMapper cardMapper;
	@Override
	public List<Card> queryCardByUserIdAndMajorCard(String user_id, String major_card) {
		return cardMapper.queryCardByUserIdAndMajorCard(user_id, major_card);
	}
	@Override
	public String queryUserName(Integer id) {
		return cardMapper.queryUserName(id);
	}
	@Override
	public boolean getCountByIdCard(String id_card) {
		
		return cardMapper.getCountByIdCard(id_card)>0;
	}

}
