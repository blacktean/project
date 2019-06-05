package com.bankTransfer.service;

import org.springframework.transaction.annotation.Transactional;

import com.bankTransfer.pojo.User;

@Transactional
public interface IUserService {
	public User queryUserByUser_name(String user_name);
}
