package com.bankTransfer.mapper;

import org.springframework.stereotype.Repository;

import com.bankTransfer.pojo.User;

@Repository
public interface UserMapper {
	//根据用户id查找用户
	public User queryUserByUser_name(int user_id);
	//修改客户的部分信息
	public void updateUserByUser_id(User user);
}
