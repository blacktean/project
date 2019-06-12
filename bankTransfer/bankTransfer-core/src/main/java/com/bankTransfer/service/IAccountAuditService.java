package com.bankTransfer.service;

import java.util.List;

import com.bankTransfer.pojo.Card;
import com.bankTransfer.pojo.User;
import com.bankTransfer.pojo.UserCondition;

public interface IAccountAuditService {
	
	/**
	 * 带条件分页查询所有开户申请
	 * @param condition
	 * @return
	 */
	List<User> queryAllByCondition(UserCondition condition);
	
	/**
	 * 查询当前审核用户信息
	 * @param id
	 * @return
	 */
	User queryCurrentUserById(Integer id);
	
	/**
	 * 审核开户信息
	 * @param id
	 * @param user_id
	 * @param updateState
	 */
	void CheckeditAccount(Integer id,Integer user_id,Integer updateState);
	
	/**
	 * 开户成功
	 * @param card
	 */
	void addId_Card(Card card);
}
