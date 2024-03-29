package com.bankTransfer.mapper;

import com.bankTransfer.pojo.User;

public interface AccountApplicationMapper {
	/**往数据插入一条用户数据*/
	void addUser(User user);
	
	/**
	 * 查询是否开户成功
	 * @param id
	 * @return
	 */
	int queryCardById(Integer id);
	
	/**
	 * 查询是否提交开户申请
	 * @param id
	 * @return
	 */
	int queryUserById(Integer id);
}
