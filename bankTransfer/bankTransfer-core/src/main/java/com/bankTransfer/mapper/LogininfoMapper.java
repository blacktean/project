package com.bankTransfer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bankTransfer.pojo.Logininfo;

@Mapper
public interface LogininfoMapper {
	/**
	 * 查询所有信息
	 * @return
	 */
	List<Logininfo> queryAll();

	Logininfo queryByUsernameOrPhoneAndPassword(@Param("login_number")String login_number,@Param("login_password") String login_password,@Param("usertype") int usertype);
}
