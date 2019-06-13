package com.bankTransfer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankTransfer.mapper.BaseMapper;
import com.bankTransfer.pojo.Card;
import com.bankTransfer.pojo.CardType;
import com.bankTransfer.pojo.Contacts;
import com.bankTransfer.pojo.Currency;
import com.bankTransfer.pojo.Document;
import com.bankTransfer.pojo.User;
import com.bankTransfer.service.IBaseService;
import com.bankTransfer.util.MD5;

@Service
public class BaseServiceImpl implements IBaseService {

	@Autowired
	private BaseMapper baseMapper;
	@Override
	public List<CardType> queryCardType() {
		return baseMapper.queryCardType();
	}

	@Override
	public List<Document> queryDocument() {
		return baseMapper.queryDocument();
	}

	@Override
	public List<Currency> queryCurrency() {
		return baseMapper.queryCurrency();
	}

	@Override
	public List<Card> queryCardByUserId(Integer id) {
		return baseMapper.queryCardByUserId(id);
	}

	@Override
	public Card queryCardByCardNumber(String id_card) {
		return baseMapper.queryCardByCardNumber(id_card);
	}

	@Override
	public void queryCardByIdCardAndPassword(String id_card, String password) {
		if(baseMapper.queryCardByIdCardAndPassword(id_card,MD5.encode(password))==null) {
			throw new RuntimeException("密码错误!!");
		}
	}

	@Override
	public User checkUser(Integer id) {
		return baseMapper.queryUserById(id);
	}

	@Override
	public Contacts queryContacts(Integer id) {
		return baseMapper.queryContacts(id);
	}


	

	
	
	
	
}
