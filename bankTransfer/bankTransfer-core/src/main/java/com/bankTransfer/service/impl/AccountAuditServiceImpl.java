package com.bankTransfer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankTransfer.mapper.AccountAuditMapper;
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

}
