package com.bankTransfer.mapper;

import org.springframework.stereotype.Repository;

import com.bankTransfer.pojo.User;

@Repository
public interface UserMapper {
	//根据用户名查找用户
	public User queryUserByUser_name(String user_name);
}
