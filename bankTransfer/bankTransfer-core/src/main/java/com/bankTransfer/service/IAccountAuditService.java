package com.bankTransfer.service;

import java.util.List;

import com.bankTransfer.pojo.User;
import com.bankTransfer.pojo.UserCondition;

public interface IAccountAuditService {
	
	/**
	 * 带条件分页查询所有开户申请
	 * @param condition
	 * @return
	 */
	List<User> queryAllByCondition(UserCondition condition);
	
}
