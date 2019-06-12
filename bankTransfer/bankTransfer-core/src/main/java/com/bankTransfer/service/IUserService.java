package com.bankTransfer.service;

import org.springframework.transaction.annotation.Transactional;
import com.bankTransfer.pojo.User;

@Transactional
public interface IUserService {
	/**
	  * 通过user_name查当前用户
	 * @param user_name
	 * @return
	 */
	public User queryUserByUser_name(int user_id);
	//修改客户的部分信息
	public void updateUserByUser_id(User user);
}
