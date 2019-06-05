package com.bankTransfer.mapper;

import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Param;

import com.bankTransfer.pojo.Logininfo;
/***
 * 注册
 * @author 康志龙
 *@version  创建时间   2019年6月5日  下午3:17:59
 */
@Mapper
public interface RegisterMapper {
/**
 * 检验用户名是否已经被注册
 * @param username 用户名
 * @param usertype 
 * @return
 */
	Logininfo checkUsername(@Param("username")String username, @Param("usertype")int usertype);

	/**
	 * 注册 
	 * @param username 用户名
	 * @param password 密码
	 * @param telphone 电话
	 * @param state 状态值
	 * @param usertype 用户类别
	 * @return
	 */
	int register(@Param("username")String username,@Param("password") String password,@Param("telphone") String telphone, int state, int usertype);

}
