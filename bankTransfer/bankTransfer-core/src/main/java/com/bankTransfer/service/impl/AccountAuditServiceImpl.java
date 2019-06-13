package com.bankTransfer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankTransfer.mapper.AccountAuditMapper;
import com.bankTransfer.pojo.Card;
import com.bankTransfer.pojo.CardCondition;
import com.bankTransfer.pojo.Card_VO;
import com.bankTransfer.pojo.User;
import com.bankTransfer.pojo.UserCondition;
import com.bankTransfer.service.IAccountAuditService;
@Service
public class AccountAuditServiceImpl implements IAccountAuditService {
	
	@Autowired
	private AccountAuditMapper accountAuditMapper;
	@Override
	public List<User> queryAllByCondition(UserCondition condition) {
		
		return accountAuditMapper.queryAllByCondition(condition);
	}
	@Override
	public User queryCurrentUserById(Integer id) {
		return accountAuditMapper.queryCurrentUserById(id);
	}
	@Override
	public void CheckeditAccount(Integer id, Integer user_id, Integer updateState) {
		accountAuditMapper.CheckeditAccount(id, user_id, updateState);
	}
	@Override
	public void addId_Card(Card card) {
		accountAuditMapper.addId_Card(card);
	}
	@Override
	public List<Card_VO> queryAllCardByCondition(CardCondition condition) {
		return accountAuditMapper.queryAllCardByCondition(condition);
	}
	@Override
	public void updateCardState(Integer id, String card_state) {
		accountAuditMapper.updateCardState(id, card_state);
	}

}
