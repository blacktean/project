package com.bankTransfer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bankTransfer.mapper.UserMapper;
import com.bankTransfer.pojo.User;
import com.bankTransfer.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User queryUserByUser_name(int user_id) {
		return userMapper.queryUserByUser_name(user_id);
	}

	@Override
	public void updateUserByUser_id(User user) {
		userMapper.updateUserByUser_id(user);
	}
	
}
