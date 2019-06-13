package com.bankTransfer.service;

import com.bankTransfer.pojo.User;

public interface IAccountApplicationService {
	/**往数据插入一条用户数据*/
	void addUser(User user);
	
	/**
	 * 查询是否开户成功
	 * @param id
	 * @return
	 */
	boolean queryCardById(Integer id);
}
