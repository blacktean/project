package com.bankTransfer.service;
/**
 * 注册service层
 * @author 康志龙
 *@version  创建时间   2019年6月5日  下午3:20:26
 */
public interface IRegisterService {

	/**
	 * 检验用户名是否已存在
	 * @param regist_account
	 * @param usertype 
	 * @return
	 */
	boolean checkUsername(String regist_account, int usertype);

	
	/**
	 * 注册
	 * @param username 用户名
	 * @param password 密码
	 * @param telphone 电话
	 * @param state 状态值
	 * @param usertype 用户类别
	 * @return
	 */
	boolean register(String username, String password, String telphone, int state, int usertype);

}
